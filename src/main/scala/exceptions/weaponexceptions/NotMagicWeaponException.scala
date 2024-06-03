package exceptions.weaponexceptions

/**Exception for when a mage is trying to cast a spell without a magic weapon equipped
 *
 * @param details the message shown
 */

class NotMagicWeaponException(details:String) extends Exception(s"$details"){

}
