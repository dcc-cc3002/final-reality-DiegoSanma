package turnos

import attributes.{Attributes, Mage}
import enemigo.Enemigo
import entity.Entidad

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

class ProgramadorDeTurnos (val players: ArrayBuffer[Attributes], val enemies: ArrayBuffer[Enemigo]) {
  /** A paramater that holds the values for everyone´s action bar during combat */
  var registro: ArrayBuffer[Int] = ArrayBuffer()

  /** A method for adding either players or enemies to the the turn calculator
   *
   * The function agregar receives a player or enemy and adds it to its corresponding array
   * It adds the corresponding player to the end of their array
   * If that character is already inside the array, no one is added adn the array stays the same
   */

    def agregar(added: Entidad): Unit = {
    if (!(added.isInstanceOf[Attributes]) && !(added.isInstanceOf[Enemigo])) {
      return
    }
    if (added.isInstanceOf[Attributes]) {
      if (!(players.contains (added))) {
        players.addOne(added.asInstanceOf[Attributes])
        this.registro.addOne(0)
      }
    }
    else {
      if (!(enemies.contains(added))) {
        enemies.addOne(added.asInstanceOf[Enemigo])
        this.registro.addOne(0)
      }
    }
  }

    /** A method for removing either players or enemies to the the turn calculator
     *
     * The function remover receives a player or enemy and removes it from their corresponding array
     * It removes him from the array
     * If that character in not inside the array, the array stays the same
     */

    def sacar(removed: Entidad): Unit = {
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


    def Barra():ArrayBuffer[(Entidad,Double)] ={
        var barraMaxima: ArrayBuffer[(Entidad,Double)] = ArrayBuffer()
        for(i <- 0 until players.length){
          if(players(i).weapon.isDefined) {
            var barra: Double = players(i).weight + 0.5*players(i).weapon.get.weight
            barraMaxima += ((players(i), barra): (Entidad, Double))
          }
          else{
            var barra: Double = players(i).weight
            barraMaxima += ((players(i), barra): (Entidad, Double))
          }
        }
        for(i <- 0 until enemies.length)  {
          var barra: Double = enemies(i).weight
          barraMaxima += ((enemies(i), barra): (Entidad, Double))
      }
      return barraMaxima
    }

    /** A method that adds a constant value to each character currently in combat*
     * The function takes a parameter th Integer k, that is added to each action bar
     *
     */
     def continuar(k:Int): Unit = {
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

    def revisionTurno():Unit ={
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

    def next_turn():Option[Entidad]={
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
