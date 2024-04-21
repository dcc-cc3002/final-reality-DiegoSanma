package attributes

import entity.AEntidad
import weapons.Weapons

/**An abstract class for a character that includes the methods used for
 * different types of characters
 *
 * The abstract class is defined using the characteristics from the trait Attributes
 *
 * @author Diego San Martin
 */

abstract class Character(name:String,hp:Int,defense: Int, weight: Int,private var weapon:Option[Weapons]=None)
  extends AEntidad(name,hp,defense,weight) with Attributes {

  override def getMana(): Option[Int] = {
    None
  }

  override def getWeapon(): Option[Weapons] = {
    this.weapon
  }

  override def receiveWeapon(): Unit = {

  }


}
