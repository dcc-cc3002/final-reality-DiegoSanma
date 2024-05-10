package weapons

import attributes.{Attributes, Mage}
import characters.{Guerrero, MagoNegro, Ninja, Paladin}

/** A trait used to define the methods that will be implemented by a weapon
 *
 */


trait TWeapons {
  def getWeaponName(): String
  def getAtkPts(): Int
  def getWeaponWeight(): Int

  def getOwner(): Option[Attributes]

  def rename(named:String): Unit

  def giveToOwner(receiver:Attributes): Unit

  def leaveOwner(): Unit
}
