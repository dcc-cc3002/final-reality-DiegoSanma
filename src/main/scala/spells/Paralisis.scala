package spells
import attributes.{Attributes, Mage}
import entity.Entidad
import exceptions.FriendlyFireException

/**Class for paralyzing spell
 *
 * Used to paralyze enemies, has a 100% acuraccy rate
 *
 */
class Paralisis extends HechizoLuz {

  /** Method for using a the paralyzing spell
   *
   * @param user the mage using the spell
   * @param victim the enemy being paralyzed
   */
  override def finalInflict(user: Mage, victim: Entidad): Unit = {
    user.useMana(25)
  }

  /**Method for checking whether the mae is trying to paralyze an ally or enemy
   *
   * @param user mage using the spell
   * @param victim entity that the mage want to paralyze
   *
   * @throws FriendlyFireException if victim is not an ally
   */
  override def friendlyFire(user: Mage, victim: Entidad): Unit = {
    if(victim.isInstanceOf[Attributes]){
      throw new FriendlyFireException("Cant paralyze an ally")
    }
  }
}
