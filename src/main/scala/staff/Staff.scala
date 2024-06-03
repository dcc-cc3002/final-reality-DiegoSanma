package staff

import attributes.Attributes
import weapons.AMagicWeapon

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
  extends AMagicWeapon(name,atkpoints,weight,magicpoints,owner){

  /**Method for telling owner what type of weapon they are receiving
   *
   * As this weapon is of the class Staff, it calls receiveStaff for the receiver
   *
   * @param receiver expected new owner of the Staff
   */

  override def giveToOwner(receiver: Attributes): Unit = {
    receiver.receiveStaff(this)
  }
}
