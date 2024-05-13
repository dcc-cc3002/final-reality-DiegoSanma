package wand

import attributes.Attributes
import weapons.MagicWeapon

/**A class for a wand
 *
 * Extends from Magic Weapon class, inheriting all its methods
 *
 * @param name the name of the wand
 * @param atkpoints the attack points of the wand
 * @param weight the weight of the wand
 * @param magicpoints the magic points of the wand
 * @param owner the current owner of the wand
 *
 */

class Wand (name:String,atkpoints:Int,weight:Int,magicpoints:Int,owner:Option[Attributes]=None)
  extends MagicWeapon(name,atkpoints,weight,magicpoints,owner) {

  override def giveToOwner(receiver: Attributes): Unit = {
    receiver.receiveWand(this)
  }
}
