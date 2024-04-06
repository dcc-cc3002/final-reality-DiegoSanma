package magonegro

import attributes.{Mage, MagicAttributes}
import weapons.Weapons

/** A class representing a black mage (mago negro)
 *
 * A mago negro is defined by his name, hp, defense, weight and mana
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

class MagoNegro(val name: String,var hp: Int, var defense:Int, var weight: Int, var mana: Int) extends Mage {
  /**The weapon the black mage is holding*/
  var weapon: Option[Weapons] = None
}
