package exceptions

class InvalidInventoryException (details: String) extends Exception(s"An invalid inventory was found -- $details"){

}
