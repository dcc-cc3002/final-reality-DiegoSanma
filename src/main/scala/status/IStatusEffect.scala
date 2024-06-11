package status

import enemy.EnemyAttributes
import weapons.IMagicWeapon

/**Trait for status effects imflicted on enemies
 *
 */
trait IStatusEffect {

  def doEffect(enemy:EnemyAttributes): Unit

  def getTurnsLeft(): Int

  protected def setTurnsLeft(turns:Int): Unit

  protected def turnPassed(): Unit
}
