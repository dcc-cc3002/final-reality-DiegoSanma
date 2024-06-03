package sword

import attributes.Attributes
import weapons.AWeapon

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

class Sword (name:String,atkpoints:Int,weight:Int,owner:Option[Attributes]=None) extends AWeapon(name,atkpoints,weight, owner) {

  /**Method for telling owner what type of weapon they are receiving
   *
   * As this weapon is of the class Sword, it calls receiveSword for the receiver
   *
   * @param receiver expected new owner of the Sword
   */
  override def giveToOwner(receiver: Attributes): Unit = {
    receiver.receiveSword(this)
  }
}