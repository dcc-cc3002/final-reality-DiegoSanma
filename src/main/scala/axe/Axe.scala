package axe

import attributes.Attributes
import weapons.Weapon

/**A class for an Axe
 *
 * Extends from Weapon class, inheriting all methods
 *
 * @param name the name of the axe
 * @param atkpoints the attack points of the axe
 * @param weight the weight of the axe
 * @param owner the current owner of the axe
 *
 */

class Axe (name:String,atkpoints:Int,weight:Int,owner:Option[Attributes]=None) extends Weapon(name,atkpoints,weight, owner){

  override def giveToOwner(receiver: Attributes): Unit = {
    receiver.receiveAxe(this)
  }
}
