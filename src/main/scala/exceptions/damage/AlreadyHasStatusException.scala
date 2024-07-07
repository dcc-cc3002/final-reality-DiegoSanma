package exceptions.damage

/**Exception for when a status effect is trying to be placed on someone who already has one
 *
 * @param details the message shown when the exception is thrown
 */
class AlreadyHasStatusException(details:String) extends Exception(s"$details"){

}
