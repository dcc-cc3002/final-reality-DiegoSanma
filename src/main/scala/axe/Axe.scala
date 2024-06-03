package axe

import attributes.Attributes
import weapons.AWeapon

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

class Axe (name:String,atkpoints:Int,weight:Int,owner:Option[Attributes]=None) extends AWeapon(name,atkpoints,weight, owner){

  /**Method for telling owner what type of weapon they are receiving
   *
   * As this weapon is of the class Axe, it calls receiveAxe for the receiver
   *
   * @param receiver expected new owner of the Axe
   */
  override def giveToOwner(receiver: Attributes): Unit = {
    receiver.receiveAxe(this)
  }
}
