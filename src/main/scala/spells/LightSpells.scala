package spells
import attributes.Mage
import entity.Entity

abstract class LightSpells extends ISpells {

  /**Method for telling the mage what type of spell they are trying to use
   *
   * @param user the mage utrying to use the light spell
   * @param victim the victim of the spell
   */
  override def inflict(user:Mage,victim: Entity): Unit = {
    user.throwLightSpell(this,victim)
  }
}
