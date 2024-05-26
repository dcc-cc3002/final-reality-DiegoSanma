package spells
import attributes.Mage
import entity.Entidad

/**Class for a fire spell
 *
 * Inflicted on enemies, does a certain amount of damage and has a chance to burn them
 *
 */
class Fuego extends HechizoOscuro {

  /**Method for inflictinf finally the spell
   * The enemy takes damage according to the weapon the mage is currently holding
   *
   * @param user the mage using the spell
   * @param victim the enemy being burnt
   */
  override def finalInflict(user: Mage, victim: Entidad): Unit = {
    victim.takedamagePlayer(user)
    user.useMana(15)
  }

}
