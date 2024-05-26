package spells
import attributes.{Attributes, Mage}
import enemigo.EnemigoAttributes
import entity.Entidad
import exceptions.FriendlyFireException

/**Class for a healing spell
 *
 * Takes no parameters, and can only be used on allies
 *
 */

class Curacion extends HechizoLuz {
  /**Method for healing an ally
   *
   * The ally will always be healed by 30% of their maximum health
   * Even if when healing, the max health is surpassed, the hp will remain capped by that value
   *
   * @param user the mage healing
   * @param victim the caharcter being healed
   */
  override def finalInflict(user: Mage, victim: Entidad): Unit = {
    victim.asInstanceOf[Attributes].heal(0.3)
  }

  /**Checks whether or not the mage is trying to heal an enemy or ally
   *
   * @param user the mage healing
   * @param victim entity the mage want to heal
   *
   * @throws FriendlyFireException if they are trying to heal an enemy
   */
  override def friendlyFire(user: Mage, victim: Entidad): Unit = {
    if(victim.isInstanceOf[EnemigoAttributes]){
      throw new FriendlyFireException("Cant heal an enemy")
    }
  }
}
