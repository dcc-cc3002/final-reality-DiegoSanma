package weapontypes.bow.wand

/** A class representing an wand(varita magica)
 *
 * As wand is defined by his name, atkpoints, weight, magicpoints and owner
 *
 * @param name the name of the wand
 * @param atkpoints the hitpoints of the wand
 * @param weight the weight of the wand
 * @param magicpoints the magic points of the wand
 * @param owner the owner of the wand
 *
 * @constructor creates a new wand  with a name, and values for atkpoints and weight, as well as an owner
 *
 *
 * @author Diego San Martin
 *
 */

import attributes.{Attributes, Mage, MagicAttributes}
import characters.{Guerrero, MagoNegro, Ninja, Paladin}
import weapons.MagicWeapons

class VaritaMagica(var name:String, var atkpoints:Int, var weight:Int,var initialmpts: Option[Int],var owner:Attributes) extends MagicWeapons