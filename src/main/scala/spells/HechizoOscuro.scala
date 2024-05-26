package spells
import attributes.{Attributes, Mage}
import entity.Entidad
import exceptions.FriendlyFireException

abstract class HechizoOscuro extends IHechizo {
  /**Method for telling a mage ehat type of spell they are trying to use
   *
   * @param user the mage trying to use the spell
   * @param victim the victim of the spell
   */
  override def inflict(user: Mage, victim: Entidad): Unit = {
    user.throwDarkSpell(this,victim)
  }

  /**Method for checking whether a mage is trying to use a dark spell on an ally or enemy
   * As all dark spells inflict damage, these spells can only be used on enemies
   *
   * @param user the mage trying to use the spell
   * @param victim the entity the mage is trying to use the spell on
   *
   * @throws FriendlyFireException if the entity is not an ally
   */
  override def friendlyFire(user: Mage, victim: Entidad): Unit = {
    if (victim.isInstanceOf[Attributes]) {
      throw new FriendlyFireException("Cant inflict damage/effect to an ally")
    }
  }
}
