package exceptions

class InvalidOwnerException (details: String) extends Exception(s"An invalid initial owner was found -- $details"){

}
