package exceptions.turns

class NotInSchedulerException(details:String) extends Exception(s"$details is not in the scheduler currently"){

}
