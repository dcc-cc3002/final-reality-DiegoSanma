package wand

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

import attributes.Attributes
import weapons.MagicWeapons

class VaritaMagica(var name:String, var atkpoints:Int, var weight:Int,var magicpoints: Int,var owner:Option[Attributes]) extends MagicWeapons{
}