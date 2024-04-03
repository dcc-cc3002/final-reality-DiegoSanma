package attributes

/**An abstract class for a mage that includes the methods used for both
 * type of mages
 *
 * The abstract class is defined using the characteristics from thr trait Magic Attributes
 *
 * @author Diego San Martin
 */

abstract class Mage extends MagicAttributes {
  /**Returns the name of the Mage
   *
   * The name of the mage corresponds to a string
   *
   */
  def nameGet(): Unit ={
    this.name
  }

  /**Returns the amouunt of mana the mage has
   *
   * The mana of the mage corresponds to an Int
   *
   */
  def manaAmount(): Unit ={
    this.mana
  }
}
