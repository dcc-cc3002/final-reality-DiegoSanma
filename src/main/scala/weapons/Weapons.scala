package weapons

import attributes.{Attributes, Mage}
import characters.{Guerrero, MagoNegro, Ninja, Paladin}

/** A trait used to define the characteristics of a weapon
 *
 * The charactersitics of a sword are defined by its name, attack points (atkpts), weight and owner
 *
 * @tparam name the name of the weapon
 * @tparam atkpoints the attack points of the weapon
 * @tparam weight the weight of the weapon (in kilos)
 * @tparam magicpoints the magic points of the wepaon(currently None)
 * @tparam owner the owner of the weapon
 * @author Diego San Martin
 */


trait Weapons {
  var name: String
  var atkpoints: Int
  var weight: Int
  var magicpoints: Option[Int] = None
  var owner : Attributes

}
