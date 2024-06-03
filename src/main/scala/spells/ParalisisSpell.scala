package spells
import attributes.{Attributes, Mage}
import entity.Entity
import exceptions.damage.FriendlyFireException

/**Class for paralyzing spell
 *
 * Used to paralyze enemies, has a 100% acuraccy rate
 *
 */
class ParalisisSpell extends LightSpells {

  /** Method for using a the paralyzing spell
   * Also checks whether the mage has enough mana to use the spell
   *
   * @param user the mage using the spell
   * @param victim the enemy being paralyzed
   */
  override def finalInflict(user: Mage, victim: Entity): Unit = {
    user.checkMana(25)
    user.useMana(25)
  }

  /**Method for checking whether the mae is trying to paralyze an ally or enemy
   *
   *
   * @param user mage using the spell
   * @param victim entity that the mage want to paralyze
   *
   * @throws FriendlyFireException if victim is not an ally
   */
  override def friendlyFire(user: Mage, victim: Entity): Unit = {
      victim.checkifEnemy()
  }
}
