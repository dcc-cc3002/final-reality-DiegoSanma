package attributes

import weapons.Weapons

/** A trait representing the attributes of a character
 *
 * The attributes of a character are defined by their name, hitpoints(hp), defense and weight
 *
 * @tparam name the name of the character
 * @tparam hp the hitpoints/health of a character
 * @tparam defense the defense value of a character
 * @tparam weight the weight of a character
 * @tparam mana the mana of the character(currently, the trait takes mana as None)
 * @tparam weapon the wepaon the character is holding(is built without a weapon)
 * @author Diego San Martin
 */
trait Attributes {
  val name: String
  var hp: Int
  var defense: Int
  var weight: Int
  var mana: Option[Int] = None
  var weapon : Option[Weapons] = None
}
