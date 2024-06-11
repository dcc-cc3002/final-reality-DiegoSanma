package status
import enemy.EnemyAttributes
import weapons.IMagicWeapon

/**
 * Class for paralyzed status effect
 */

class Paralyzed extends AStatusEffect {
  /** Sets turns enemy is paralyzed for to 1 */
    setTurnsLeft(1)

  /**Method for applying effect to enemy
   * In this case, since the effect is skipping a turn, to do said effect, that must be taken care of in the
   * StatusState by the controller
   */
  override def doEffect(): Unit ={
    turnPassed()
  }
}
