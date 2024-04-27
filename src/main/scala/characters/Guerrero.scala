package characters

import attributes.Attributes
import attributes.Character
import weapons.TWeapons

import scala.collection.mutable.ArrayBuffer

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
class Guerrero(name: String,hp: Int,defense:Int,weight: Int,
               inventory:ArrayBuffer[TWeapons]=ArrayBuffer(),activeWeapon:Option[TWeapons]=None) extends
  Character(name,hp,defense,weight,inventory,activeWeapon){
  /** weapon the Guerrero is holding*/

}
