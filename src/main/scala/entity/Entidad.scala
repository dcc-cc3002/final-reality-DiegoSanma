package entity

/**A trait that includes the attributes that define all entities
 * The arritbutes of an entity are their name, hitpoints, defense and weight
 *
 */
trait Entidad{
  val name: String
  var hp: Int
  var defense: Int
  var weight: Int
}
