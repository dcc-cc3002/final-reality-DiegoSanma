package exceptions.weaponexceptions

/**Exception for when a weapon is trying to be renamed, but already has a name
 *
 * @param details message shown
 */
class AlreadyNamedException(details:String) extends Exception(s"$details"){

}
