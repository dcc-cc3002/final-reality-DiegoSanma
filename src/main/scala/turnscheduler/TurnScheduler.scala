package turnscheduler

import attributes.{Attributes, Mage}
import enemy.{Enemy, EnemyAttributes}
import entity.Entity
import exceptions.turns.{AlreadyInSchedulerException, NotInSchedulerException}

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

class TurnScheduler(private val players: ArrayBuffer[Attributes],
                    private val enemies: ArrayBuffer[EnemyAttributes]) extends ITurnScheduler {

  /** Getter for player parameter */
  override def getPlayers(): ArrayBuffer[Attributes] = {
    this.players
  }

  /** Getter for enemies parameter */
  override def getEnemies(): ArrayBuffer[EnemyAttributes] = {
    this.enemies
  }

  /** A method for adding either players or enemies to the the turn calculator
   *
   * Will cal method in the entity that is being added, that will later tell the turn scheduler what
   * type of entity it is adding (enemies or characters)
   */

    override def addTo(added: Entity): Unit = {
      added.addToTurns(this)
    }

  /**Method for adding a character into the turn scheduler
   *
   * @param added the character being added
   *
   * @throws AlreadyInSchedulerException if Character is already in the turn scheduler
   */
  override def addCharacter(added: Attributes): Unit = {
    if(players.contains(added)){
      throw new AlreadyInSchedulerException("Character")
    }
    players.addOne(added)
  }

  /**Method for adding an enemy to the turn scheduler
   *
   * @param added enemy being added to the turn scheduler
   *
   * @throws AlreadyInSchedulerException if the enemy is already in the turns scheduler
   */

  override def addEnemy(added: EnemyAttributes): Unit = {
    if(enemies.contains(added)){
      throw new AlreadyInSchedulerException(("Enemy"))
    }
    enemies.addOne(added)
  }

    /** A method for removing either players or enemies to the the turn calculator
     * Calls function for entity, where it tells the turn scheduler what type of entity is being removed
     */

    override def removeEntity(removed: Entity): Unit = {
      removed.removeFromTurns(this)
    }

  /**Method for removing a character from the turn scheduler
   *
   * @param removed the character being removed
   *
   * @throws NotInSchedulerException if character is not currently in the turn scheduler
   */
  override def removeCharacter(removed: Attributes): Unit = {
    if(!(players.contains(removed))){
      throw new NotInSchedulerException("Character")
    }
    var position:Int = players.indexOf(removed)
    players.remove(position)
  }

  /**Method for removing an enemy from the turn scheduler
   *
   * @param removed enemy being removed from the turn scheduler
   *
   * @throws NotInSchedulerException if the enemy is not currently in the turn scheduler
   */
  override def removeEnemy(removed: EnemyAttributes): Unit = {
    if(!(enemies.contains(removed))){
      throw new NotInSchedulerException("Enemy")
    }
    var position:Int = enemies.indexOf(removed)
    enemies.remove(position)
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


    /** A method that adds a constant value to each character currently in combat
     * The function takes an Integer k as a parameter, that is added to each action bar
     *
     */
     override def continue(k:Int): Unit = {
       for(i <- this.players){
          i.addToActionBar(k)
       }
       for(i <- this.enemies){
         i.addToActionBar(k)
       }
     }

    /** A parameter that holds the turns in which the combat shall be played out */
    var turns: ArrayBuffer[Entity] = ArrayBuffer()

  /**Getter for turns array, that holds the "waiting line" for each of the entities turn
   *
   * @return this.turns
   */
  override def getTurnsLine(): ArrayBuffer[Entity] = {
    this.turns
  }

    /** A method that checks whether someone has completed their action bar,
     * signals that the character has done by adding him/her/it to the turn array
     * and finally restarts their action bar
     *
     * If no action bar was completed, then nothing happens
     */

    override def checkTurn():Unit ={
      var past: ArrayBuffer[(Entity,Int)] = ArrayBuffer()
      for(element<-players){
        var dif: Int = element.getActionBar()-element.getMaxActionBar
        if(dif>=0){
          past.addOne(element,dif)
          element.addToActionBar(-element.getActionBar())
        }
      }
      for(enemy<-enemies){
        var dif: Int = enemy.getActionBar()-enemy.getMaxActionBar
        if(dif>=0){
          past.addOne(enemy,dif)
          enemy.addToActionBar(-enemy.getActionBar())
        }
      }
      var tieBreaker: ArrayBuffer[(Entity,Int)] = past.sortBy(_._2).reverse
      for((element,dif)<-tieBreaker){
        turns.addOne(element)
      }
    }

    /**A method that returns whose turn it is during a combat sequence
     * Also removes entity from the turn array, so next time this function is called,
     * will return next entity in line
     *
     * If no one has completed their action bar and the turns array is empty, the function returns None
     *
     */

    override def nextTurn():Option[Entity]={
      if(this.turns.isEmpty){
        return None
      }
      else{
        var my_turn: Entity = turns(0)
        this.turns.remove(0)
        return(Some(my_turn))
      }
    }
}
