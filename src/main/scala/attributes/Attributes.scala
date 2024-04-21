package attributes

import entity.AEntidad
import weapons.{Weapon, Weapons}

/** A trait representing the attributes of a character
 *
 * The attributes of a character extends from the abstact class AEntidad
 * Declares methods for getting mana and weapon from a Character
 */

trait Attributes extends AEntidad{
  def getMana(): Option[Int]
  def getWeapon(): Option[Weapons]

  def receiveWeapon(weap:Weapon): Unit

  def dropWeapon(): Unit

}