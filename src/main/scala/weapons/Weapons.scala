package weapons

import attributes.{Attributes, Mage}
import characters.{Guerrero, MagoNegro, Ninja, Paladin}

/** A trait used to define the methods that will be implemented by a weapon
 *
 */


trait Weapons {
  def getWeaponName(): String
  def getAtkPts(): Int
  def getWeaponWeight(): Int
  def getMagicPts(): Int
  def getOwner(): Attributes

  def rename(named:String): Unit

  def changeOwner(receiver:Option[Attributes]=None): Unit
}
