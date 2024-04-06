package weapons
/**An  class for a magic weapon
 *
 *A magic weapon is defined by its name, atkpoints, weight, magic points and owner
 *
 *
 * @tparam name the name of the magic weapon
 * @tparam atkpoints the attack points of the magic weapon
 * @tparam weight the weight of the magic weapon
 * @tparam magicpts the magic points of the magic weapon
 * @tparam owner the owner of the magic weapon
 *
 *
 * @author Diego San Martin
 */
class MagicWeapon(var name:String,var atkpoints:Int, var weight:Int, var magicpts:Int,var owner:Option[Either[Paladin,Either[Ninja,Either[Guerrero,MagoNegro]]]] extends MagicWeapons {
  /**Returns the name of the weapon
   *
   * The name of the weapon corresponds to a string, which could already belong to the
   * weapon or could have been given by someone
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
