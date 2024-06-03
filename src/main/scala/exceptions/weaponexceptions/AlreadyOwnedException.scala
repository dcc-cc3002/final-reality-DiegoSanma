package exceptions.weaponexceptions

/**Exception for when a character is trying to add a weapon to their inventory, but it already has an owner
 *
 * @param details the message shown
 */
class AlreadyOwnedException(details: String) extends Exception(s"$details"){

}
