package spells
import attributes.{Attributes, IMage, Mage}
import entity.Entity
import exceptions.damage.FriendlyFireException
import weapons.AMagicWeapon

/**Abstract class for methods used by a dark spell
 *
 */
abstract class DarkSpells extends ISpells with IDarkSpells {
  /**Method for telling a mage what type of spell they are trying to use
   *
   * @param user the mage trying to use the spell
   * @param victim the victim of the spell
   */
  override def inflict(user: IMage, victim: Entity): Unit = {
    user.throwDarkSpell(this,victim)
  }

  /**Method for checking whether a mage is trying to use a dark spell on an ally or enemy
   * As all dark spells inflict damage, these spells can only be used on enemies
   *
   * @param user the mage trying to use the spell
   * @param victim the entity the mage is trying to use the spell on
   *
   * @throws FriendlyFireException if the entity is an ally
   */
  override def friendlyFire(user: IMage, victim: Entity): Unit = {
    victim.checkifEnemy()
  }
}
