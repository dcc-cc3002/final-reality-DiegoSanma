package weapons

import attributes.{Attributes, Mage}
import guerrero.Guerrero
import magonegro.MagoNegro
import ninja.Ninja
import paladin.Paladin

/** A trait used to define the characteristics of a weapon
 *
 * The charactersitics of a sword are defined by its name, attack points (atkpts), weight and owner
 *
 * @tparam name the name of the weapon
 * @tparam atkpoints the attack points of the weapon
 * @tparam weight the weight of the weapon (in kilos)
 * @tparam owner the owner of the weapon
 * @author Diego San Martin
 */


trait Weapons {
  var name: String
  var atkpoints: Int
  var weight: Int
  var owner : Option[Either[Paladin,Either[Ninja,Either[Guerrero,MagoNegro]]]]

}
