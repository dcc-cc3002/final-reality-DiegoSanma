package spells
import attributes.{Attributes, IMage, Mage}
import characters.WhiteMage
import enemy.EnemyAttributes
import entity.Entity
import exceptions.damage.FriendlyFireException

/**Class for a poison spell
 *
 * Used for poisoning an enemy, has 100% accuracy rate
 *
 */
class PoisonSpell extends LightSpells with StatusLightSpells {

  /**Method for poisoning an enemy
   * Also checks whether the mage has enough mana to use the spell
   *
   * @param user mage using the spell
   * @param victim victim being poisoned
   */
  override def finalCheck(user: IMage, victim: Entity): Unit = {
    user.checkMana(30)

    user.useMana(30)
  }

  /**Method for finally inflicting the poison spell on an enemy (currently, nothing is done to the enemy)
   *
   * @param user the WhiteMage using the spell
   * @param victim the enemy the poison ingoing to be inflicted on
   */
  override def finalStatusSpell(user: IMage, victim: EnemyAttributes): Unit = {
    user.useMana(30)
  }
}
