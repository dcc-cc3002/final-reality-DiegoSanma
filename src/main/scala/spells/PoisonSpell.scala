package spells
import attributes.{Attributes, IMage, Mage}
import characters.WhiteMage
import enemy.EnemyAttributes
import entity.Entity
import exceptions.damage.FriendlyFireException
import status.Poisoned
import weapons.IMagicWeapon

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
   * @param victim victim being
   * @param magicWeapon the magic weapon the mahe is holding
   */
  override def finalCheck(user: IMage, victim: Entity,magicWeapon:IMagicWeapon): Unit = {
    user.checkMana(30)
    victim.checkLightStatusSpell(user,this,magicWeapon)
  }

  /**Method for finally inflicting the poison spell on an enemy
   * Sets enemy´s current status condition to poisoned
   *
   * @param user the WhiteMage using the spell
   * @param victim the enemy the poison ingoing to be inflicted on
   */
  override def finalStatusSpell(user: IMage, victim: EnemyAttributes,magicWeapon: IMagicWeapon): Unit = {
    victim.setStatus(new Poisoned(magicWeapon))
    user.useMana(30)
  }
}
