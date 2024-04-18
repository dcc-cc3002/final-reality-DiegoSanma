package attributes

import entity.Entidad
import weapons.Weapons

/** A trait representing the attributes of a character
 *
 * The attributes of a character are defined by the same attributes of an entity
 * Other attributes included are their mana and weapon(that begin as None)
 *
 * @tparam mana the mana of the character(currently, the trait takes mana as None)
 * @tparam weapon the wepaon the character is holding(is built without a weapon)
 * @author Diego San Martin
 */

trait Attributes extends Entidad{
  var mana: Option[Int] = None
  var weapon : Option[Weapons] = None
}
