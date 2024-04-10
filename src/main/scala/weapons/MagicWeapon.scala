package weapons

import attributes.Attributes

/**A  class for a magic weapon
 *
 *A magic weapon is defined by its name, atkpoints, weight, magic points and owner
 *
 * @tparam name the name of the magic weapon
 * @tparam atkpoints the attack points of the magic weapon
 * @tparam weight the weight of the magic weapon
 * @tparam magicpoints the magic points of the magic weapon
 * @tparam owner the owner of the magic weapon
 *
 * @constructor creates a new magic weapon with a name(could be ""), attack points, weight, magic points and an owner
 *
 * @author Diego San Martin
 */
class MagicWeapon(var name:String,var atkpoints:Int, var weight:Int, var initialmpts:Option[Int],var owner:Attributes) extends Weapons {
  owner.weapon = Some(this)
  magicpoints = initialmpts
  /**Renames the weapon to the name(named) of choice
   *
   * @param named the new name to be assigned to the weapon
   *
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
