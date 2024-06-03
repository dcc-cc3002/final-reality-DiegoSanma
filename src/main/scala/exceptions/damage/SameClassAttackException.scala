package exceptions.damage

/**An exception for when an entity is trying to attack an entity of the same type
 * For example, a character attacking another characters or an enemy attacking an enemy
 *
 * @param details the message the exception shows
 */

class SameClassAttackException(details:String) extends Exception(s"$details"){

}
