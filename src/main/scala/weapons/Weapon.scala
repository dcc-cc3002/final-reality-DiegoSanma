package weapons

import guerrero.Guerrero
import magonegro.MagoNegro
import ninja.Ninja
import paladin.Paladin

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

class Weapon(var name:String,var atkpoints:Int,var weight:Int,var owner:Option[Either[Paladin,Either[Ninja,Either[Guerrero,MagoNegro]]]]) extends Weapons {
  /**Returns the name of the weapon
   *
   * The name of the weapon corresponds to a string, which could already belong to the
   * weapon or could have been given by someone
   *
   * @throws "This weapon has no name" if the weapon does not have name yet
   *
   */

  def nameGet(): Unit = {
    if (this.name == ""){
      println("This weapon has no name")
      return null
    }
    this.name
  }

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
