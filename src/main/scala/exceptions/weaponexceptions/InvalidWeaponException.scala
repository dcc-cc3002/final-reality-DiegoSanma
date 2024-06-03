package exceptions.weaponexceptions

/** Exception for when a character is trying to be initialized with an active weapon equipped
 *
 * @param details message shown ehn exception is thrown
 */

class InvalidWeaponException (details: String) extends Exception(s"An invalid initial weapon was found -- $details"){

}
