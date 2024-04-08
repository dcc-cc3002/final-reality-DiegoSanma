package characters

import attributes.Attributes

/** A class representing a white mage (mago blanco)
 *
 * A mago blanco is defined by his name, hp, defense, weight and mana
 *
 * @param name the name of the mago blanco
 * @param hp the hitpoints of the mago blanco
 * @param defense the defense value of the mago blanco
 * @param weight the weight of the mago blanco
 * @param mana the mana/magic points of the mago blanco
 *
 * @constructor creates a new mago blanco with a name, and values for hp, defense, weight and mana
 *
 * @author Diego San Martin
 *
 */


class MagoBlanco(val name: String,var hp: Int, var defense:Int, var weight: Int, var initialmana: Option[Int]) extends Attributes {
  //*The weapon the white mage is holding*/
  mana= initialmana

}
