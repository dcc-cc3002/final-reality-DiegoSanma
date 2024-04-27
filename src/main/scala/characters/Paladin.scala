package characters

import attributes.Attributes
import attributes.Character
import weapons.TWeapons

import java.security.InvalidParameterException
import scala.collection.mutable.ArrayBuffer

/** A class representing a paladin
 *
 * A paladin is defined by his name, hp, defense and weight
 *
 * @param name the name of the paladin
 * @param hp the hitpoints of the paladin
 * @param defense the defense value of the paladin
 * @param weight the weight of the paladin
 * @param weapon the wepaon the paladin is holding
 *
 * @constructor creates a new paladin with a name, and values for hp, defense and weight
 *
 * @author Diego San Martin
 *
 */


class Paladin(name: String,hp: Int,defense:Int,weight: Int,
              inventory:ArrayBuffer[TWeapons]=ArrayBuffer(),activeWeapon:Option[TWeapons]=None)
  extends Character(name,hp,defense,weight,inventory, activeWeapon) {

}
