package spells
import attributes.{IMage, Mage}
import enemy.EnemyAttributes
import entity.Entity
import status.Burnt
import weapons.IMagicWeapon

import scala.util.Random

/**Class for a fire spell
 *
 * Inflicted on enemies, does a certain amount of damage and has a chance to burn them
 *
 */
class FireSpell extends DarkSpells {

  /**Method for inflicting finally the spell
   * The enemy takes damage according to the weapon the mage is currently holding
   * Also checks whether the mage has enough mana to use the spell
   *
   * @param user the mage using the spell
   * @param victim the enemy being burnt
   */
  override def finalCheck(user: IMage, victim: Entity,magicWeapon:IMagicWeapon): Unit = {
    user.checkMana(15)
    victim.checkDarkInflictSpell(user,this,magicWeapon)
  }

  /**Method for finally attacking an enemy with fire
   * Has a 20% chance of burning the enemy
   *
   * @param user the mage using the spell
   * @param victim the enemy being attacked
   * @param magicWeapon the magic weapon the mage is holding
   */
  override def throwFinalDarkAttack(user:IMage,victim:EnemyAttributes,magicWeapon:IMagicWeapon): Unit = {
    victim.takeSpellDamage(user,magicWeapon)
    val random = new Random().nextInt(9)
    if(random<2){
      victim.setStatus(new Burnt(magicWeapon))
    }
    user.useMana(15)
  }
}
