package weapons



/**A trait that defines the characteristics of a magic weapon
 *
 * A magic weapon is defined by the charactersitics of a normal weapon and the magic attack points (magicattk)
 *
 * @tparam magicpoints the magic attack points of a magic weapon
 * @author Diego San Martin
 */

trait MagicWeapons extends Weapons {
    var magicpoints: Int
}
