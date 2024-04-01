package sword

import weapons.Weapon

import java.security.InvalidParameterException

/**A class representing a Sword
 *
 * A Sword is defined by its name, attack points and weight. It may also have an owner
 *
 * @example
 * {{{
 *   var sword = new Sword("Excalibur",40,40)
 *   sword.name() = name
 *   println(s"The sword´s name is $name")
 * }}}
 * @author Diego San Martin
 */

class Sword (name: String, atkpoints : Int, weight : Int) extends Weapon{
  /** The current owner of the sword (currently no one)*/
  var owner: Either[Either[Either[Paladin,Guerrero],Ninja],MagoNegro] = null

  override def nameGet(): Unit = super.nameGet()

  override def rename(named: String): Unit = super.rename(named)


  /**The sword is obtained by one of 4 classes who can weild it
   *
   * In this case, the owner changes, and also the wepaon for the character who obtains is
   *
   * @throws InvalidParameterException when the owner can´t weild it
   *
   * @example
   * {{{
   *   var sword = new Sword("Excalibur",40,40,null)
   *   var paladin = new Paladin("Diego",100,90,70)
   *   sword.obtain(paladin)
   *   println(sword.owner.name) ----> should give "Diego"
   * }}}
   */

  def obtain(character:Either[Either[Either[Paladin,Guerrero],Ninja],MagoNegro]): Unit ={
    owner = character
  }

}
