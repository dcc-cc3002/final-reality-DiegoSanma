package attributes

import weapons.Weapons

/**An abstract class for a mage that includes the methods used for both
 * types of mages
 *
 * The abstract class is defined using the characteristics from the trait Attributes
 *
 * @author Diego San Martin
 */

abstract class Mage(name:String,hp:Int,defense: Int, weight: Int,private var mana:Int, private var weapon:Option[Weapons]=None) extends Attributes {
  def this(name:String,hp:Int,defense:Int,weight:Int,mana:Int,weapon:Option[Weapons]){
    this(name,hp,defense,weight,mana,weapon)
  }
  override def getMana(): Option[Int] = {
    Some(this.mana)
  }

  override def getWeapon(): Option[Weapons] = {
    this.weapon
  }

}
