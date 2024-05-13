package sword

import attributes.Attributes
import weapons.Weapon

/**A class for a sword
 *
 * Extends from Weapon class, inheriting all its methods
 *
 * @param name the name of the sword
 * @param atkpoints the attack points of the sword
 * @param weight the weight of the sword
 * @param owner the current owner of the sword
 *
 */

class Sword (name:String,atkpoints:Int,weight:Int,owner:Option[Attributes]=None) extends Weapon(name,atkpoints,weight, owner){

  override def giveToOwner(receiver: Attributes): Unit = {
    receiver.receiveSword(this)
}
