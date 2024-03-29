package attributes

/** A trait representing the attributes of a character
 *
 * The attributes of a character are defined by their name, hitpoints(hp), defense and weight
 *
 * @tparam name the name of the character
 * @tparam hp the hitpoints/health of a character
 * @tparam defense the defense of a character
 * @tparam weight the weight of a character
 *
 * @author Diego San Martin
 */
trait Attributes {
  def name: String
  def hp: Int
  def defense: Int
  def weight: Int
}
