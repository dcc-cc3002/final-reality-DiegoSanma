package characters

import attributes.Attributes
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

class Ninja(name: String,hp: Int,defense:Int,weight: Int,weapon:Option[Weapons]) extends Character(name,hp,defense,weight,weapon){

}
