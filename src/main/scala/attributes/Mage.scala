package attributes

import entity.AEntidad
import weapons.TWeapons

/**An abstract class for a mage that includes the methods used for both
 * types of mages
 *
 * The abstract class is defined using the characteristics from the trait Attributes
 *
 * @author Diego San Martin
 */

abstract class Mage(name:String,hp:Int,defense: Int, weight: Int,private var mana:Int, private var weapon:Option[TWeapons]=None) extends AEntidad(name,hp,defense,weight) with Attributes {
  def getMana(): Int = {
    this.mana
  }

  override def getWeapon(): Option[TWeapons] = {
    this.weapon
  }

  override def receiveWeapon(weapon:TWeapons): Unit = {
    this.weapon = Some(weapon)
  }

  override def dropWeapon(): Unit = {
    this.weapon = None
  }
}
