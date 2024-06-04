package spells
import attributes.{Attributes, IMage, Mage}
import characters.WhiteMage
import enemy.EnemyAttributes
import entity.Entity
import exceptions.damage.FriendlyFireException

/**Class for a healing spell
 *
 * Takes no parameters, and can only be used on allies
 *
 */

class HealingSpell extends LightSpells with HealingLightSpells {
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
  override def finalCheck(user: IMage, victim: Entity): Unit = {
    user.checkMana(15)
    victim.checkLightHealSpell(user,this)
  }

  override def finalHealSpell(user: IMage, victim: Attributes): Unit = {
    victim.heal(0.3)
    user.useMana(15)
  }
}
