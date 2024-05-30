package turnos

import attributes.{Attributes, Mage}
import enemigo.{Enemigo, EnemigoAttributes}
import entity.Entidad
import exceptions.turns.AlreadyInSchedulerException

import scala.collection.mutable.ArrayBuffer

/** A class that calculates the turns taken by each characters and enemy during a battle sequence
 *
 * @tparam players an Array with all the players participating
 * @tparam enemies an Array with all the enemies participating
 *
 * @example
 * var turnos = new ProgramadorDeTurnos(ArrayBuffer[magoblanco,ninja], ArrayBuffer[goblin,golem])
 * println(s"The name of the first enemy is ${enemies[1].name}"), should be "goblin"
 * println(s"The name of the second player is ${players[2].name}"), should be "ninja"
 *
 * @author Diego San Martin
 */

class ProgramadorDeTurnos (private val players: ArrayBuffer[Attributes],
                           private val enemies: ArrayBuffer[EnemigoAttributes]) extends IProgramadorDeTurnos {

  /** Getter for player parameter */
  override def getPlayers(): ArrayBuffer[Attributes] = {
    this.players
  }

  /** Getter for enemies parameter */
  override def getEnemies(): ArrayBuffer[EnemigoAttributes] = {
    this.enemies
  }
  /** A parameter that holds the values for everyone´s action bar during combat */
  var registro: ArrayBuffer[Int] = ArrayBuffer()

  /** A method for adding either players or enemies to the the turn calculator
   *
   * Will cal method in the entity that is being added, that will later tell the turn scheduler what
   * type of entity it is adding (enemies or characters)
   */

    override def addTo(added: Entidad): Unit = {
      added.addToTurns(this)
    }

  /**Method for adding a character into the turn scheduler
   *
   * @param added the character being added
   */
  override def addCharacter(added: Attributes): Unit = {
    if(players.contains(added)){
      throw new AlreadyInSchedulerException("Character")
    }
    players.addOne(added)
  }

  override def addEnemy(added: EnemigoAttributes): Unit = {
    if(enemies.contains(added)){
      throw new AlreadyInSchedulerException(("Enemy"))
    }
    enemies.addOne(added)
  }

    /** A method for removing either players or enemies to the the turn calculator
     *
     * The function remover receives a player or enemy and removes it from their corresponding array
     * It removes him from the array
     * If that character in not inside the array, the array stays the same
     *
     * @example
     * val paladin = new Paladin("Diego",90,80,70)
     * var turnos = new ProgramadorDeTurnos(ArrayBuffer(paladin))
     * turnos.sacar(paladin)
     * println(s"The players in the turn scheduler are ${turnos.players}") ---> Should print an empty Array Buffer
     */

    override def sacar(removed: Entidad): Unit = {
      if (!(removed.isInstanceOf[Attributes]) && !(removed.isInstanceOf[Enemigo])) {
        return
      }
      if (removed.isInstanceOf[Attributes]) {
        if ((players.contains(removed))) {
          val index = players.indexOf(removed)
          players.remove(index)
          this.registro.remove(index)
        }
      }
      else {
        if ((enemies.contains(removed))) {
          val index = enemies.indexOf(removed)
          enemies.remove(index)
          this.registro.remove(index)
        }
      }
    }


    /** A method that calculates the action bar for each character in combat
     *
     * The function returns a buffer array with the tuple of the character and their max action bar
     *
     * The action bar is calculated differently for enemies and players
     * For players it is equal to their weight added with half their weapon´s weight
     * For enemies, it is equal to their weight
     *
     */


    override def Barra():ArrayBuffer[(Entidad,Double)] ={
        var barraMaxima: ArrayBuffer[(Entidad,Double)] = ArrayBuffer()
        for(i <- 0 until players.length){
          if(players(i).getActiveWeapon().isDefined) {
            var barra: Double = players(i).getWeight() + 0.5*players(i).getActiveWeapon().get.getWeaponWeight()
            barraMaxima += ((players(i), barra): (Entidad, Double))
          }
          else{
            var barra: Double = players(i).getWeight()
            barraMaxima += ((players(i), barra): (Entidad, Double))
          }
        }
        for(i <- 0 until enemies.length)  {
          var barra: Double = enemies(i).getWeight()
          barraMaxima += ((enemies(i), barra): (Entidad, Double))
      }
      return barraMaxima
    }

    /** A method that adds a constant value to each character currently in combat
     * The function takes an Integer k as a parameter, that is added to each action bar
     *
     */
     override def continuar(k:Int): Unit = {
       for(i<-0 until registro.length){
          this.registro(i) = this.registro(i) + k
       }
     }

    /** A parameter that holds the turns in which the combat shall be played out */
    var turnos: ArrayBuffer[Entidad] = ArrayBuffer()

    /** A method that checks whether someone has completed their action bar,
     * signals that the character has done by adding him/her/it to the turn array
     * and finally restarts their action bar
     *
     * If no action bar was completed, then nothing happens
     */

    override def revisionTurno():Unit ={
      var pasados: ArrayBuffer[(Entidad,Double)] = ArrayBuffer()
      var barraMax: ArrayBuffer[(Entidad,Double)] = Barra()
      for(i<-0 until this.registro.length){
        val dif: Double = this.registro(i)-barraMax(i)._2
        if (dif>=0){
          this.registro(i)= 0
          pasados.addOne((barraMax(i)._1,dif))
        }
      }
      var desempate: ArrayBuffer[(Entidad,Double)] = pasados.sortBy(_._2).reverse
      for((element,dif)<-desempate){
        turnos.addOne(element)
      }
    }

    /**A method that returns whose turn it is during a combat sequence
     * Also removes entity from the turn array, so next time this function is called,
     * will return next entity in line
     *
     * If no one has completed their action bar and the turnos array is empty, the function returns None
     *
     */

    override def next_turn():Option[Entidad]={
      if(this.turnos.isEmpty){
        return None
      }
      else{
        var my_turn: Entidad = turnos(0)
        this.turnos.remove(0)
        return(Some(my_turn))
      }
    }
}
