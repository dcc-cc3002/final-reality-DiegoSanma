package exceptions.mage

/** An exception for when a mage is trying to cast a spell they cannot
 * For example, when a black mage tries to cast a light spell and vice versa
 *
 * @param details the message shown
 */
class WrongMageException(details:String) extends Exception(s"$details"){

}
