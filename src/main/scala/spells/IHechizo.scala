package spells

import entity.Entidad

/**Trait for spells, includes methods that will be used by all types of spells(healing, damaging, or effect spells)
 *
 */
trait IHechizo {

  def inflict(victim:Entidad)



}
