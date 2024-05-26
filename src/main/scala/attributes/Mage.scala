package attributes

import enemigo.{Enemigo, EnemigoAttributes}
import entity.{AEntidad, Entidad}
import exceptions.{AlreadyOwnedException, FullInventoryException, SameClassAttackException}
import spells.IHechizo
import weapons.TWeapons

import scala.collection.mutable.ArrayBuffer

/**An abstract class for a mage that includes the methods used for both
 * types of mages
 *
 * The abstract class is defined using the characteristics from the trait Attributes
 *
 * @author Diego San Martin
 */

abstract class Mage(name:String,hp:Int,defense: Int, weight: Int,
                    private var mana:Int, inventory: ArrayBuffer[TWeapons],activeWeapon: Option[TWeapons])
  extends Character(name,hp,defense,weight,inventory, activeWeapon) with IMage {
  override def getMana(): Int = {
    this.mana
  }

  override def throwSpell(spell: IHechizo, victim: Entidad): Unit = {
    spell.inflict(victim)
  }


}
