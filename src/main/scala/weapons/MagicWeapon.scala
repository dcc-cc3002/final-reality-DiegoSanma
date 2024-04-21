package weapons

import attributes.Attributes

/**A  class for a magic weapon
 *
 *A magic weapon is defined by its name, atkpoints, weight, magic points and owner
 *
 * @param name the name of the magic weapon
 * @param atkpoints the attack points of the magic weapon
 * @param weight the weight of the magic weapon
 * @param magicpoints the magic points of the magic weapon
 * @param owner the owner of the magic weapon
 *
 * @constructor creates a new magic weapon with a name(could be ""), attack points, weight, magic points and an owner
 *
 * @author Diego San Martin
 */
class MagicWeapon(name:String,atkpoints:Int,weight:Int,owner:Attributes,private var magicpoints:Int) extends Weapon(name,atkpoints,weight,owner) {


}
