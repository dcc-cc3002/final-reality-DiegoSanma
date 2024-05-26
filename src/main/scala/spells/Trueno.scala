package spells
import attributes.{Attributes, Mage}
import entity.Entidad

/**Class for a thunder spell
 *
 * Inflicts a certain amount of damage on an emeny and has a chance ot paralyze him
 *
 */
class Trueno extends HechizoOscuro {

  /** Method for using thunder spell on an enemy
   * The amount of damage done is defined by the weapon the mage is currently holding
   *
   * @param user the mage using the spell
   * @param victim the victim being attacked
   */
  override def finalInflict(user: Mage, victim: Entidad): Unit = {
    victim.takedamagePlayer(user)
    user.useMana(20)
  }
}
