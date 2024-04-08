package weapons

import attributes.Attributes
import characters.{Guerrero, MagoNegro, Ninja, Paladin}

/**An  class for a weapon
 *
 *A weapon is defined by its name, atkpoints, weight and owner
 *
 *
 * @tparam name the name of the weapon
 * @tparam atkpoints the attack points of the weapon
 * @tparam weight the weight of the weapon
 * @tparam owner the owner of the weapon
 *
 * @author Diego San Martin
 */

class Weapon(var name:String,var atkpoints:Int,var weight:Int,var owner:Attributes) extends Weapons {
  owner.weapon = Some(this)
  /**Renames the weapon to the name(named) of choice
   *
   * @param named the new name to be assigned to the weapon
   *
   * @throws This weapon has already been named $name$, if the weapon has already received a name
   */

 def rename(named: String): Unit ={
   if (this.name != ""){
     println(s"This weapon has already been named ${name}" )
     return null
   }
   this.name = named
   println(s"This weapon has been named ${name}")
 }

}
