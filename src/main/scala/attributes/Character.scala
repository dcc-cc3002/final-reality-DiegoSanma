package attributes

/**An abstract class for a weapon that includes the methods used for
 * different types of weapons
 *
 * The abstract class is defined using the characteristics from thr trait Weapons
 *
 * @author Diego San Martin
 */

abstract class Character extends Attributes {
  /**Returns the name of the Character
   *
   * The name of the character corresponds to a string
   *
   * @throws "This weapon has no name" if the weapon does not have name yet
   *
   */
  def nameGet(): Unit = {
    this.name
  }
}
