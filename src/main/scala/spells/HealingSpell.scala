package spells
import attributes.{Attributes, Mage}
import enemy.EnemyAttributes
import entity.Entity
import exceptions.damage.FriendlyFireException

/**Class for a healing spell
 *
 * Takes no parameters, and can only be used on allies
 *
 */

class HealingSpell extends LightSpells {
  /**Method for healing an ally
   *
   * The ally will always be healed by 30% of their maximum health
   * Even if when healing, the max health is surpassed, the hp will remain capped by that value
   * Mage also spends 15 mana in the process
   * Also checks whether the mage has enough mana to use the spell
   *
   * @param user the mage healing
   * @param victim the caharcter being healed
   */
  override def finalInflict(user: Mage, victim: Entity): Unit = {
    user.checkMana(15)
    victim.asInstanceOf[Attributes].heal(0.3)
    user.useMana(15)
  }

  /**Checks whether or not the mage is trying to heal an enemy or ally
   *
   * @param user the mage healing
   * @param victim entity the mage want to heal
   *
   * @throws FriendlyFireException if they are trying to heal an enemy
   */
  override def friendlyFire(user: Mage, victim: Entity): Unit = {
    if(victim.isInstanceOf[EnemyAttributes]){
      throw new FriendlyFireException("Cant heal an enemy")
    }
  }
}
