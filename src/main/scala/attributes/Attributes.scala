package attributes

import axe.Axe
import bow.Bow
import entity.AEntidad
import staff.Staff
import sword.Sword
import wand.Wand
import weapons.{AWeapon, TWeapons}

import scala.collection.mutable.ArrayBuffer

/** A trait representing the attributes of a character
 *
 * The attributes of a character extends from the abstact class AEntidad
 * Declares methods for getting mana and weapon from a Character
 */

trait Attributes extends AEntidad{

  def getWeapons(): ArrayBuffer[TWeapons]

  def getActiveWeapon(): Option[TWeapons]

  def receiveWeapon(weapon:TWeapons): Unit

  def receiveBow(weapon:Bow): Unit

  def receiveSword(weapon:Sword): Unit

  def receiveAxe(weapon:Axe): Unit

  def receiveWand(weapon:Wand): Unit

  def receiveStaff(weapon:Wand): Unit
  def dropWeapon(weapon:TWeapons): Unit

  def changeWeapon(position:Int): Unit



}