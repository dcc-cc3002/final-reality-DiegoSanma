package ninja

import attributes.Character
import weapons.Weapons

/** A class representing a ninja
 *
 * A ninja is defined by his name, hp, defense and weight
 *
 * @param name the name of the ninja
 * @param hp the hitpoints of the ninja
 * @param defense the defense value of the ninja
 * @param weight the weight of the ninja
 *
 * @constructor creates a new ninja with a name, and values for hp, defense and weight
 *
 * @author Diego San Martin
 *
 */

class Ninja(val name: String,var hp: Int, var defense:Int, var weight: Int) extends Character{
  /** weapon the Ninja is holding*/

  var weapon: Option[Weapons] = None
}
