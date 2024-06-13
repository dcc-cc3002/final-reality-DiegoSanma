package exceptions.mage

/**Exception class for checking if a character is a mage or not
 *
 * @param details the message shown when the exception is thrown
 */
class NotMageException(details:String) extends Exception(s"$details"){

}
