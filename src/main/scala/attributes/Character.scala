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

abstract class Character(name:String,hp:Int,defense: Int, weight: Int,private var weapon:Option[Weapons])
  extends AEntidad(name,hp,defense,weight) with Attributes {

  def this(name:String,hp:Int,defense:Int,weight:Int,weapon:Option[Weapons]){
    this(name,hp,defense,weight,weapon)
  }
  override def getMana(): Option[Int] = {
    None
  }

  override def getWeapon(): Option[Weapons] = {
    this.weapon
  }


}
