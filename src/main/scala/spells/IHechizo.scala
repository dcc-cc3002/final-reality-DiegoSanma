package spells

import attributes.Mage
import entity.Entidad

/**Trait for spells, includes methods that will be used by all types of spells(healing, damaging, or effect spells)
 *
 */
trait IHechizo {

  def inflict(user:Mage,victim:Entidad)

  def friendlyFire(user:Mage,victim:Entidad)

  def finalInflict(user:Mage,victim:Entidad)

}
