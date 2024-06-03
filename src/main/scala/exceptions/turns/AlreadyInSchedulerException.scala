package exceptions.turns

/**Exception thrown when trying to add a member to a turn scheduler they is already in
 *
 * @param details the type of entity trying to be added (character or enemy)
 */
class AlreadyInSchedulerException(details:String) extends Exception(s"$details is already in the turn scheduler"){

}
