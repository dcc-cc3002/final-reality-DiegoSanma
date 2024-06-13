package exceptions.damage

/**An exception made for when a spell is being casted on the wrong type of entity
 *
 * @param details the message the exception shows
 */
class FriendlyFireException(details:String) extends Exception(s"$details"){

}
