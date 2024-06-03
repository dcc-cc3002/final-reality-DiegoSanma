package exceptions.weaponexceptions

/**Exception for when a character is trying to attack or cast a spell without an active weapon equipped
 *
 * @param details the message shown
 */

class NoWeaponException(details:String) extends Exception(s"$details"){

}
