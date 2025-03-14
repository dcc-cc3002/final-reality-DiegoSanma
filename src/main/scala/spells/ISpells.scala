package spells

import attributes.{IMage, Mage}
import enemy.EnemyAttributes
import entity.Entity
import weapons.{AMagicWeapon, IMagicWeapon}

/**Trait for spells, includes methods that will be used by all types of spells(healing, damaging, or effect spells)
 *
 */
trait ISpells {

  def inflict(user:IMage,victim:Entity,magicWeapon:IMagicWeapon) : Unit

  def finalCheck(user:IMage,victim:Entity,magicWeapon:IMagicWeapon): Unit


}
