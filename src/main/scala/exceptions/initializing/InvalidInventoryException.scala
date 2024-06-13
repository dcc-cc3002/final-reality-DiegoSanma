package exceptions.initializing

/**Exception for when a character is being initialized with an inventory that is not empty
 *
 * @param details message added for when exception finds an invalid initial exception
 */
class InvalidInventoryException (details: String) extends Exception(s"An invalid inventory was found -- $details"){

}
