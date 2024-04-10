package characters

import attributes.Attributes
import attributes.Character

/** A class representing a warrior(guerrero)
 *
 * A guerrero is defined by his name, hp, defense and weight
 *
 * @param name the name of the guerrero
 * @param hp the hitpoints of the guerrero
 * @param defense the defense value of the guerrero
 * @param weight the weight of the guerrero
 *
 * @constructor creates a new guerrero with a name, and values for hp, defense and weight
 *
 * @author Diego San Martin
 *
 */
class Guerrero(val name: String,var hp: Int, var defense:Int, var weight: Int) extends
  Character{
  /** weapon the Guerrero is holding*/

}
