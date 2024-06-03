package exceptions.turns

/** Exception thrown when an entity is being removed from the turn scheduler despite not being in it
 *
 * @param details the type of entity trying to be removed (character or entity)
 */
class NotInSchedulerException(details:String) extends Exception(s"$details is not in the scheduler currently"){

}
