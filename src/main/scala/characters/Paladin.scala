package characters

import attributes.Attributes
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

}
