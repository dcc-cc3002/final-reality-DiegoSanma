package exceptions.turns

class AlreadyInSchedulerException(details:String) extends Exception(s"$details is already in the turn scheduler"){

}
