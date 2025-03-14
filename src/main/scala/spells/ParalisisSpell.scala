package spells
import attributes.{Attributes, IMage, Mage}
import characters.WhiteMage
import enemy.EnemyAttributes
import entity.Entity
import exceptions.damage.FriendlyFireException
import status.Paralyzed
import weapons.IMagicWeapon

/**Class for paralyzing spell
 *
 * Used to paralyze enemies, has a 100% acuraccy rate
 *
 */
class ParalisisSpell extends LightSpells with StatusLightSpells {

  /** Method for using a the paralyzing spell
   * Also checks whether the mage has enough mana to use the spell
   *
   * @param user the mage using the spell
   * @param victim the enemy being paralyzed
   * @param magicWeapon the magic weapon the mage is holding
   */
  override def finalCheck(user: IMage, victim: Entity,magicWeapon:IMagicWeapon): Unit = {
    user.checkMana(25)
    victim.checkLightStatusSpell(user,this,magicWeapon)
  }

  /**Method for finally inflicting the paralisis spell on an enemy
   * Sets enemy´s status condition to paralyzed
   *
   * @param user the WhiteMage using the spell
   * @param victim the enemy the paralisis ingoing to be inflicted on
   */
  override def finalStatusSpell(user: IMage, victim: EnemyAttributes,magicWeapon: IMagicWeapon): Unit = {
    victim.setStatus(new Paralyzed)
    user.useMana(25)
  }
}
