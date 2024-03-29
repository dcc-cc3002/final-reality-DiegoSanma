package attributes

/**Trait defines the magic attributes of a mage
 *
 * The magic attributes of a mage are defined by their attributes(see Attributes) and mana
 *
 * @tparam mana the amount of mana/magic a mage has
 *
 */
trait MagicAttributes extends Attributes {
  def mana: Int
}
