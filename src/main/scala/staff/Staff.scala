package staff

import attributes.Attributes
import weapons.MagicWeapon

/**A class for a staff
 *
 * Extends from MagicWeapon class, inheriting all its methods
 *
 * @param name the name of the staff
 * @param atkpoints the attack points of the staff
 * @param weight the weight of the staff
 * @param magicpoints the magic points of the staff
 * @param owner the current owner of the staff
 *
 */

class Staff (name:String,atkpoints:Int,weight:Int,magicpoints:Int,owner:Option[Attributes]=None)
  extends MagicWeapon(name,atkpoints,weight,magicpoints,owner){

}
