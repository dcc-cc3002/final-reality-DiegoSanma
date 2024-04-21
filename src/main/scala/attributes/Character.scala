package attributes

import weapons.Weapons

/**An abstract class for a character that includes the methods used for
 * different types of characters
 *
 * The abstract class is defined using the characteristics from the trait Attributes
 *
 * @author Diego San Martin
 */

abstract class Character extends Attributes {
  private var mana: Option[Int] = None
  var weapon : Option[Weapons] = None

}
