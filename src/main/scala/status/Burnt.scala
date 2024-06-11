package status

import enemy.EnemyAttributes
import weapons.IMagicWeapon

/**Class for burnt status effect
 *
 * @param magicWeapon the magic weapon with which the spell was cast
 */

class Burnt(private val magicWeapon: IMagicWeapon) extends AStatusEffect {
  /** Sets the amount of turns a burnt status effect last to 3 */
  setTurnsLeft(3)

  /**Method for inflicting the burn on an enemy
   * The amount of damage is equivalent to magicpts/2
   *
   * @param enemy the burnt enemy
   * @param magicWeapon the magic weapon used to cast the burnt spell
   */

  override def doEffect(enemy:EnemyAttributes,magicWeapon:IMagicWeapon): Unit = {
    var damage: Int = (magicWeapon.getMagicpts()/2).toInt
    enemy.checkHealth(damage)
  }
}
