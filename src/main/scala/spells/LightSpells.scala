package spells
import attributes.{IMage, Mage}
import entity.Entity
import weapons.IMagicWeapon

/**Abstract class for method used by light spells
 */
abstract class LightSpells extends ISpells {

  /**Method for telling the mage what type of spell they are trying to use
   *
   * @param user the mage trying to use the light spell
   * @param victim the victim of the spell
   * @param magicWeapon the magic weapon the mage is holding
   */
  override def inflict(user:IMage,victim: Entity,magicWeapon:IMagicWeapon): Unit = {
    user.throwLightSpell(this,victim,magicWeapon)
  }
}
