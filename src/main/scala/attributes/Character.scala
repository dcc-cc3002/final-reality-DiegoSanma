package attributes

import enemigo.Enemigo
import entity.{AEntidad, Entidad}
import weapons.{TWeapons, Weapon}

/**An abstract class for a character that includes the methods used for
 * different types of characters
 *
 * The abstract class is defined using the characteristics from the trait Attributes
 *
 * @author Diego San Martin
 */

abstract class Character(name:String,hp:Int,defense: Int, weight: Int,private var weapon:Option[TWeapons]=None)
  extends AEntidad(name,hp,defense,weight) with Attributes {

  override def getWeapon(): Option[TWeapons] = {
    this.weapon
  }

  override def receiveWeapon(weapon:TWeapons): Unit = {
    if (this.weapon.isDefined) {
      if (this.weapon.get == weapon) {
        return
      }
    }
      this.weapon = Some(weapon)
      weapon.giveToOwner(this)
  }


  override def dropWeapon(): Unit = {
    if(this.weapon.isDefined){
      var aux_weapon: TWeapons = this.weapon.get
      this.weapon = None
      aux_weapon.leaveOwner()
    }
  }


}
