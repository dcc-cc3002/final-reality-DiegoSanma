package status

/**
 * Abstract class for StatusEffects
 */

abstract class AStatusEffect extends IStatusEffect {
  /** Parameter for the amount of turns left with the status condition inflicted */
  private var turnsLeft: Int = 0

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
   */
  override def turnPassed(): Unit = {
    this.turnsLeft -= 1
  }

}
