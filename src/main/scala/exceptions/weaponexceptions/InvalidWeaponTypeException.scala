package exceptions.weaponexceptions

/**Exception for when a character is trying to add to their inventory a weapon they cannot hold
 *
 * @param details the weapon that the character cannot hold
 */
class InvalidWeaponTypeException(details:String) extends Exception(s"An invalid weapon type was given -- $details"){

}
