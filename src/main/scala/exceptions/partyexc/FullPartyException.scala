package exceptions.partyexc

/**Exception thrown in two cases
 * First, when a party is initialized with more than 3 initial members
 * Second, when a member is trying to be added when the party is already full
 *
 * @param details the message shown when the exception is thrown
 */
class FullPartyException (details:String) extends Exception(s"$details"){

}
