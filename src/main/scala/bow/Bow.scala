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
  /**Method for telling owner what type of weapon they are receiving
   *
   * As this weapon is of the class Bow, it calls receiveBow for the receiver
   *
   * @param receiver expected new owner of the Bow
   */
  override def giveToOwner(receiver: Attributes): Unit = {
    receiver.receiveBow(this)
  }
}
