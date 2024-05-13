package bow

import attributes.Attributes
import weapons.Weapon

/**A class for a bow (weapon)
 * Extends from weapon class, inheriting all methods
 *
 * @param name the name of the bow
 * @param atkpoints the attack points of the bow
 * @param weight the weight of the bow
 * @param owner the current owner of the bow
 *
 */

class Bow(name:String,atkpoints:Int,weight:Int,owner:Option[Attributes]=None) extends Weapon(name,atkpoints,weight, owner)
{
}
