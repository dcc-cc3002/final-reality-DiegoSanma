package weapons

import attributes.MagicAttributes

import java.util.jar.Attributes


/**A trait that defines the characteristics of a magic weapon
 *
 * A magic weapon is defined by the charactersitics of a normal weapon and the magic attack points (magicattk)
 *
 * @tparam magicpoints the magic attack points of a magic weapon
 * @author Diego San Martin
 */

trait MagicWeapons {
    var name: String
    var atkpoints: Int
    var weight: Int
    var owner : MagicAttributes
    var magicpoints: Int
}
