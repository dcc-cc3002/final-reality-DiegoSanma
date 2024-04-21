package attributes

import entity.AEntidad
import weapons.{AWeapon, TWeapons}

/** A trait representing the attributes of a character
 *
 * The attributes of a character extends from the abstact class AEntidad
 * Declares methods for getting mana and weapon from a Character
 */

trait Attributes extends AEntidad{

  def getWeapon(): Option[TWeapons]

  def receiveWeapon(weapon:TWeapons): Unit

  def dropWeapon(): Unit

}