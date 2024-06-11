package status

import enemy.EnemyAttributes

/**
 * Abstract class for StatusEffects
 */

abstract class AStatusEffect extends IStatusEffect {
  /** Parameter for the amount of turns left with the status condition inflicted */
  private var turnsLeft: Int = 0

  /** Parameter that hold the enemy that currently has the status condition*/
  private var enemy: Option[EnemyAttributes] = None

  /**getter for amount of turns left
   *
   * @return this.turnsLeft
   */

  override def getTurnsLeft(): Int = {
    this.turnsLeft
  }

  /**Setter for the amount of turns left
   *
   * @param turns the amount of turns the status effects begins with
   */
  override protected def setTurnsLeft(turns: Int): Unit = {
    this.turnsLeft = turns
  }

  /** Method for counting that one turn has passed
   * Also, if turnsLeft is 0,
   */

  /**Getter for enemy
   *
   * @return this.enemy
   */
  override def getEnemy(): Option[EnemyAttributes]= {
    this.enemy
  }

  /**Setter for enemy inflicted by status
   *
   * @param enemy the enemy the status condition is inflicted on
   */

  override def setEnemy(enemy: EnemyAttributes): Unit = {
    this.enemy = Some(enemy)
  }

  /**Method for passing turns when effect is implemented
   * Also checks that if there are no more turns left, updates enemy´s status condition
   */
  override protected def turnPassed(): Unit = {
    this.turnsLeft -= 1
    if(turnsLeft == 0){
      this.enemy.get.setStatus(None)
      this.enemy = None
    }
  }

}
