package spells
import attributes.{Attributes, IMage, Mage}
import characters.WhiteMage
import enemy.EnemyAttributes
import entity.Entity
import exceptions.damage.FriendlyFireException
import weapons.IMagicWeapon

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
   * Also checks whether the mage has enough mana to use the spell
   *
   * @param user the mage healing
   * @param victim the character being healed
   * @param magicWeapon the magic weapon the mage is holding
   */
  override def finalCheck(user: IMage, victim: Entity,magicWeapon:IMagicWeapon): Unit = {
    user.checkMana(15)
    victim.checkLightHealSpell(user,this)
  }

  /**Method for finally healing an ally by 30%
   * Also spends the corresponding 15 mana
   *
   * @param user the mage using the spell
   * @param victim the Character being healed
   */

  override def finalHealSpell(user: IMage, victim: Attributes): Unit = {
    victim.heal(0.3)
    user.useMana(15)
  }
}
