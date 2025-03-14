package spells
import attributes.{Attributes, IMage, Mage}
import enemy.EnemyAttributes
import entity.Entity
import status.Paralyzed
import weapons.IMagicWeapon

import scala.util.Random

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
   * @param magicWeapon the magic weapon the mage is holding
   */
  override def finalCheck(user: IMage, victim: Entity,magicWeapon:IMagicWeapon): Unit = {
    user.checkMana(20)
    victim.checkDarkInflictSpell(user,this,magicWeapon)
  }

  /**Method for finally inflicting thunder on an enemy
   * Has a 30% chance of paralyzing the enemy
   *
   * @param user the mage using the spell
   * @param victim the enemy being thundered
   * @param magicWeapon the magic weapon the mage is holding
   *
   */

  override def throwFinalDarkAttack(user: IMage, victim: EnemyAttributes,magicWeapon:IMagicWeapon): Unit = {
    victim.takeSpellDamage(user,magicWeapon)
    val random: Int =new Random().nextInt(9)
    if(random<3){
      victim.setStatus(new Paralyzed)
    }
    user.useMana(20)
  }
}
