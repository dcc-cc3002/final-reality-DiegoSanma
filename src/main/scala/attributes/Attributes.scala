package attributes

import entity.{AEntidad}
import weapons.Weapons

/** A trait representing the attributes of a character
 *
 * The attributes of a character extends from the abstact class AEntidad
 * Declares methods for getting mana and wepaon from a Character
 */

trait Attributes extends AEntidad{
  def getMana(): Option[Int]
  def getWeapon(): Option[Weapons]

  var mana: Option[Int] = None
  var weapon : Option[Weapons] = None
}