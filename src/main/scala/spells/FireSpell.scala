package spells
import attributes.{IMage, Mage}
import enemy.EnemyAttributes
import entity.Entity

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
  override def finalCheck(user: IMage, victim: Entity): Unit = {
    user.checkMana(15)
    victim.checkDarkInflictSpell(user,this)
  }

  /**Method for finally attacking an enemy with fire
   *
   * @param user the mage using the spell
   * @param victim the enemy being attacked
   */
  override def throwFinalDarkAttack(user:IMage,victim:EnemyAttributes): Unit = {
    victim.takeSpellDamage(user)
    user.useMana(15)
  }
}
