package spells

import attributes.Mage
import entity.Entity

/**Trait for spells, includes methods that will be used by all types of spells(healing, damaging, or effect spells)
 *
 */
trait ISpells {

  def inflict(user:Mage,victim:Entity) : Unit

  def friendlyFire(user:Mage,victim:Entity) : Unit

  def finalInflict(user:Mage,victim:Entity): Unit

}
