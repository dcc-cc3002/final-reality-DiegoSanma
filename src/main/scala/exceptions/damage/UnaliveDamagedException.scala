package exceptions.damage

/**An exception for when a spell is being casted on a dead entity
 *
 * @param details the message being shown
 */
class UnaliveDamagedException(details:String) extends Exception(s"$details"){

}
