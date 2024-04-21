package weapons

import attributes.Attributes
import characters.{Guerrero, MagoNegro, Ninja, Paladin}

/**A  class for a weapon
 *
 *A weapon is defined by its name, atkpoints, weight and owner
 *
 * TO CONSIDER: as the weapon inherits the trait Weapons, it also has mana defined for it,
 * nevertheless, this attribute is defined as None, so it may not be considered for normal
 * weapons
 *
 * @tparam name the name of the weapon
 * @tparam atkpoints the attack points of the weapon
 * @tparam weight the weight of the weapon
 * @tparam owner the owner of the weapon
 *
 * @constructor creates a new weapon with a name(could be ""), attack points, weight and an owner
 *
 * @author Diego San Martin
 */

abstract class Weapon(private var name:String,private var atkpoints:Int,private var weight:Int,private var owner:Option[Attributes]=None) extends Weapons {
  /**Method that gives the weapon to the owner when it was created, and drops the other
   * If the player that wants to receive the weapon already has one, said weapon is dropped in favour of this one
   */
  override def giveTo(receiver:Attributes = this.owner.get): Unit = {
    owner.dropWeapon()
    owner.receiveWeapon(this)
  }
  giveTo()
  /**Renames the weapon to the name(named) of choice
   *
   * @throws This weapon has already been named $name$, if the weapon has already received a name
   */

 override def rename(named: String): Unit ={
   if (this.name != ""){
     println(s"This weapon has already been named ${name}" )
     return null
   }
   this.name = named
   println(s"This weapon has been named ${name}")
 }

}
