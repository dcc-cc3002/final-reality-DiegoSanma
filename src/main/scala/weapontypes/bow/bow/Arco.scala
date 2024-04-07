package weapontypes.bow

/** A class representing an bow(arco)
 *
 * A bow is defined by his name, atkpoints, weight and owner
 *
 * @param name the name of the bow
 * @param atkpoints the hitpoints of the bow
 * @param weight the weight of the bow
 * @param owner the owner of the bow
 *
 * @constructor creates a new bow  with a name, and values for atkpoints and weight, as well as an owner
 *
 *
 * @author Diego San Martin
 *
 */

import attributes.{Attributes, Mage}
import weapons.Weapons

class Arco(var name:String, var atkpoints:Int, var weight:Int,var owner:Attributes) extends Weapons{
}