package spells
import attributes.{Attributes, Mage}
import entity.Entidad
import exceptions.FriendlyFireException

/**Class for a poison spell
 *
 * Used for poisoning an enemy, has 100% accuracy rate
 *
 */
class Veneno extends HechizoLuz {

  /**Method for poisoning an enemy
   * Also checks whether the mage has enough mana to use the spell
   *
   * @param user mage using the spell
   * @param victim victim being poisoned
   */
  override def finalInflict(user: Mage, victim: Entidad): Unit = {
    user.checkMana(30)
    user.useMana(30)
  }

  /**Method for checking whether the mage is trying to poison an ally or enemy
   *
   * @param user the mage trying to use the spell
   * @param victim the entity the mage want to poison
   *
   * @throws FriendlyFireException if the victim is not an ally
   */

  override def friendlyFire(user: Mage, victim: Entidad): Unit = {
    if(victim.isInstanceOf[Attributes]){
      throw new FriendlyFireException("Cant poison an ally")
    }
  }
}
