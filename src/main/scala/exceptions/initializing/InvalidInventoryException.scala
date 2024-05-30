package exceptions.initializing

class InvalidInventoryException (details: String) extends Exception(s"An invalid inventory was found -- $details"){

}
