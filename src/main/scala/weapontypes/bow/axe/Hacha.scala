package weapontypes.bow.axe

/** A class representing an axe(hacha)
 *
 * An axe is defined by his name, atkpoints, weight and owner
 *
 * @param name the name of the axe
 * @param atkpoints the hitpoints of the axe
 * @param weight the weight of the axe
 * @param owner the owner of the axe
 *
 * @constructor creates a new axe  with a name, and values for atkpoints and weight, as well as an owner
 *
 *
 * @author Diego San Martin
 *
 */

import attributes.{Attributes, Mage}
import guerrero.Guerrero
import magonegro.MagoNegro
import ninja.Ninja
import paladin.Paladin
import weapons.Weapons

class Hacha(var name:String, var atkpoints:Int, var weight:Int,var owner:Option[Either[Paladin,Either[Ninja,Either[Guerrero,MagoNegro]]]]) extends Weapons{
}
