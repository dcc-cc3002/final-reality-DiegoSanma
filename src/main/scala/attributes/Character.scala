package attributes

import entity.AEntidad
import weapons.TWeapons

/**An abstract class for a character that includes the methods used for
 * different types of characters
 *
 * The abstract class is defined using the characteristics from the trait Attributes
 *
 * @author Diego San Martin
 */

abstract class Character(name:String,hp:Int,defense: Int, weight: Int,private var weapon:Option[TWeapons]=None)
  extends AEntidad(name,hp,defense,weight) with Attributes {

  override def getMana(): Option[Int] = {
    None
  }

  override def getWeapon(): Option[TWeapons] = {
    this.weapon
  }

  override def receiveWeapon(weapon:TWeapons): Unit = {
    if(this.weapon.isDefined) {
      if (this.weapon.get != weapon) {
        this.weapon = Some(weapon)
        weapon.changeOwner(Some(this))
      }
    }
    else{
      this.weapon = Some(weapon)
      weapon.changeOwner(Some(this))
    }
  }

  override def dropWeapon(): Unit = {
    if(this.weapon.isDefined){
      this.weapon.get.changeOwner()
      this.weapon = None
    }
  }


}
