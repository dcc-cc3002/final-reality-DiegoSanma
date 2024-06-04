package spells
import attributes.{Attributes, IMage, Mage}
import enemy.EnemyAttributes
import entity.Entity

/**Class for a thunder spell
 *
 * Inflicts a certain amount of damage on an emeny and has a chance ot paralyze him
 *
 */
class ThunderSpell extends DarkSpells {

  /** Method for using thunder spell on an enemy
   * The amount of damage done is defined by the weapon the mage is currently holding
   * Also checks whether the mage has enough mana to use the spell
   *
   * @param user the mage using the spell
   * @param victim the victim being attacked
   */
  override def finalCheck(user: IMage, victim: Entity): Unit = {
    user.checkMana(20)
    victim.checkDarkInflictSpell(user,this)
  }

  /**Method for finally inflicting thunder on an enemy
   *
   * @param user the mage using the spell
   * @param victim the enemy being thundered
   */

  override def throwFinalDarkAttack(user: IMage, victim: EnemyAttributes): Unit = {
    victim.takeSpellDamage(user)
    user.useMana(20)
  }
}
