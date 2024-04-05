package sword

/** A class representing as sword(espada)
 *
 * A sword is defined by his name, atkpoints, weight and owner
 *
 * @param name the name of the sword
 * @param atkpoints the hitpoints of the sword
 * @param weight the weight of the sword
 * @param owner the owner of the sword
 *
 * @constructor creates a new sword  with a name, and values for atkpoints and weight, as well as an owner
 *
 *
 * @author Diego San Martin
 *
 */

import attributes.Attributes
import weapons.Weapons

class Espada(var name:String, var atkpoints:Int, var weight:Int,var owner:Option[Attributes]) extends Weapons{
}