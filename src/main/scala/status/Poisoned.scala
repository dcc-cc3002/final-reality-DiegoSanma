package status

import enemy.EnemyAttributes
import weapons.IMagicWeapon

/**Class for poisoned status effect
 *
 * @param magicWeapon the magic weapon used to cast the spell
 */
class Poisoned(private val magicWeapon: IMagicWeapon) extends AStatusEffect {
  /** Sets amount of turns left to 4 */
  setTurnsLeft(4)

  /**Method for inflicting poison effect on enemy
   * The damage done is equivalent to magicpts/3
   *
   * @param enemy the poisoned enemy
   * @param magicWeapon the magicWeapon used to cast the poison spell
   */

  override def doEffect(enemy: EnemyAttributes, magicWeapon: IMagicWeapon): Unit = {
    var damage:Int = (magicWeapon.getMagicpts()/3).toInt
    enemy.checkHealth(damage)
  }

}
