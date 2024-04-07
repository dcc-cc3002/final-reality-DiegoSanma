package weapontypes.bow.staff

/** A class representing an staff(bastom)
 *
 * As staff is defined by his name, atkpoints, weight, magicpoints and owner
 *
 * @param name the name of the staff
 * @param atkpoints the hitpoints of the staff
 * @param weight the weight of the staff
 * @param magicpoints the magic points of the staff
 * @param owner the owner of the staff
 *
 * @constructor creates a new staff  with a name, and values for atkpoints and weight, as well as an owner
 *
 *
 * @author Diego San Martin
 *
 */

import attributes.{Attributes, Mage, MagicAttributes}
import guerrero.Guerrero
import magonegro.MagoNegro
import ninja.Ninja
import paladin.Paladin
import weapons.MagicWeapons

class Baston(var name:String, var atkpoints:Int, var weight:Int,var magicpoints: Int,var owner:MagicAttributes) extends MagicWeapons{
}