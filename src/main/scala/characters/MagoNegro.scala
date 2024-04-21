package characters

import attributes.{Attributes, Mage}
import weapons.TWeapons

/** A class representing a black mage (mago negro)
 *
 * A mago negro is defined by his name, hp, defense, weight and mana
 *
 * @constructor creates a new black mage with name, hp, defense, weight and mana
 *
 * @param name the name of the mago negro
 * @param hp the hitpoints of the mago negro
 * @param defense the defense value of the mago negro
 * @param weight the weight of the mago negro
 * @param mana the mana/magic points of the mago negro
 *
 * @constructor creates a new mago negro with a name, and values for hp, defense, weight and mana
 *
 * @author Diego San Martin
 *
 */

class MagoNegro(name: String,hp: Int,defense:Int,weight: Int,mana: Int,weapon:Option[TWeapons]) extends Mage(name,hp,defense,weight,mana,weapon){
}
