package exceptions.initializing

/**Method for when a weapon is being initialized with an owner
 *
 * @param details message shown when an invalid initial owner is found
 */
class InvalidOwnerException (details: String) extends Exception(s"An invalid initial owner was found -- $details"){

}
