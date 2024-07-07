package attributes

import axe.Axe
import bow.Bow
import entity.AEntity
import staff.Staff
import sword.Sword
import wand.Wand
import weapons.{AWeapon, TWeapons}

import scala.collection.mutable.ArrayBuffer

/** A trait representing the attributes of a character
 *
 * The attributes of a character extends from the abstact class AEntity
 * Declares methods for getting mana and weapon from a Character
 */

trait Attributes extends AEntity{

  def getWeapons(): ArrayBuffer[TWeapons]

  def getMaxActionBar(): Int
  def getActiveWeapon(): Option[TWeapons]

  protected def updateMaxActionBar(): Unit

  def receiveWeapon(weapon:TWeapons): Unit

  def receiveBow(weapon:Bow): Unit

  def receiveSword(weapon:Sword): Unit

  def receiveAxe(weapon:Axe): Unit

  def receiveWand(weapon:Wand): Unit

  def receiveStaff(weapon:Staff): Unit
  def dropWeapon(position:Int): Unit

  def changeWeapon(position:Int): Unit

  def seeIfMage(): IMage

}