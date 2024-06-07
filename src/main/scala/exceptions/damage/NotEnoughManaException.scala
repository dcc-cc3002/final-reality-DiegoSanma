package exceptions.damage

/**An exception for when a mage is trying to cast a spell without enough mana
 *
 * @param details the message the exception shows
 */
class NotEnoughManaException(details:String) extends Exception(s"$details"){
}
