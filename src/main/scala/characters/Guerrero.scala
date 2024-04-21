package characters

import attributes.Attributes
import attributes.Character
import weapons.TWeapons

/** A class representing a warrior(guerrero)
 *
 * A guerrero is defined by his name, hp, defense and weight
 *
 * @param name the name of the guerrero
 * @param hp the hitpoints of the guerrero
 * @param defense the defense value of the guerrero
 * @param weight the weight of the guerrero
 * @param weapon the weapon the guerrero is holding
 *
 * @constructor creates a new guerrero with a name, and values for hp, defense and weight
 *
 * @author Diego San Martin
 *
 */
class Guerrero(name: String,hp: Int,defense:Int,weight: Int,weapon:Option[TWeapons]=None) extends
  Character(name,hp,defense,weight,weapon){
  /** weapon the Guerrero is holding*/

}
