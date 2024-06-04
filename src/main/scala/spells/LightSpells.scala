package spells
import attributes.{IMage, Mage}
import entity.Entity

/**Abstract class for method used by light spells
 */
abstract class LightSpells extends ISpells with ILightSpells {

  /**Method for telling the mage what type of spell they are trying to use
   *
   * @param user the mage utrying to use the light spell
   * @param victim the victim of the spell
   */
  override def inflict(user:IMage,victim: Entity): Unit = {
    user.throwLightSpell(this,victim)
  }
}
