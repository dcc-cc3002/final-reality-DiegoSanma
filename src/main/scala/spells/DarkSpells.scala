package spells
import attributes.{Attributes, IMage, Mage}
import entity.Entity
import exceptions.damage.FriendlyFireException
import weapons.{AMagicWeapon, IMagicWeapon}

/**Abstract class for methods used by a dark spell
 *
 */
abstract class DarkSpells extends ISpells with IDarkSpells {
  /**Method for telling a mage what type of spell they are trying to use
   *
   * @param user the mage trying to use the spell
   * @param victim the victim of the spell
   */
  override def inflict(user: IMage, victim: Entity,magicWeapon: IMagicWeapon): Unit = {
    user.throwDarkSpell(this,victim,magicWeapon)
  }

}
