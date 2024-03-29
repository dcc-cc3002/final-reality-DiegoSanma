package attributes

/**Trait defines the magic attributes of a mage (mago)
 *
 * The magic attributes of a mage are defined by their attributes(see Attributes) and mana
 *
 * @tparam mana the amount of mana/magic a mage has
 *
 * @author Diego San Martin
 */
trait MagicAttributes extends Attributes {
  def mana: Int
}
