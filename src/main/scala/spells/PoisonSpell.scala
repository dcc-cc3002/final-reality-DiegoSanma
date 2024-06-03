package spells
import attributes.{Attributes, Mage}
import entity.Entity
import exceptions.damage.FriendlyFireException

/**Class for a poison spell
 *
 * Used for poisoning an enemy, has 100% accuracy rate
 *
 */
class PoisonSpell extends LightSpells {

  /**Method for poisoning an enemy
   * Also checks whether the mage has enough mana to use the spell
   *
   * @param user mage using the spell
   * @param victim victim being poisoned
   */
  override def finalInflict(user: Mage, victim: Entity): Unit = {
    user.checkMana(30)
    user.useMana(30)
  }

  /**Method for checking whether the mage is trying to poison an ally or enemy
   *
   * @param user the mage trying to use the spell
   * @param victim the entity the mage want to poison
   *
   * @throws FriendlyFireException if the victim is an ally
   */

  override def friendlyFire(user: Mage, victim: Entity): Unit = {
    victim.checkifEnemy()
  }
}
