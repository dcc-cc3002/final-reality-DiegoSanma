package exceptions.weaponexceptions

/**Exception for when trying to add a weapon to the inventory, but it is already full (max 3)
 *
 * @param details the message shown
 */

class FullInventoryException(details:String) extends Exception(s"$details"){

}
