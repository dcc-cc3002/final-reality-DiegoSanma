package paladin

import attributes.Attributes
import weapons.Weapons
import attributes.Character

import java.security.InvalidParameterException

/** A class representing a paladin
 *
 * A paladin is defined by his name, hp, defense and weight
 *
 * @param name the name of the paladin
 * @param hp the hitpoints of the paladin
 * @param defense the defense value of the paladin
 * @param weight the weight of the paladin
 *
 * @constructor creates a new paladin with a name, and values for hp, defense and weight
 *
 * @author Diego San Martin
 *
 */


class Paladin(val name: String,var hp: Int, var defense:Int, var weight: Int) extends Character {
  /** weapon the Paladin is holding*/

  var weapon: Option[Weapons] = None



  /**Gives the weapon to the Paladin
   *
   * The variable weapon is changed to the weapon that we wanted to have
   * If the Paladin is already holding a weapon, this one will be dropped in favour
   * of the new one
   *
   * @param weapon the weapon that the paladin wants to have
   *
   * @throws InvalidParameterException if the weapon cannot be equipped by the paladin
   *
   * @example
   * {{{
   *   var paladin = Paladin("Diego",100,90,70)
   *   var sword = Sword("Excalibur",40,40)
   *   paladin.obtain(sword)
   *   println("The weapon of the paladin $name is ${weapon.name}"
   * }}}
   *
   */
}
