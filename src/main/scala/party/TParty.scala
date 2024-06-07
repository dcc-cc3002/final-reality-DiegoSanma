package party

import attributes.Attributes


import scala.collection.mutable.ArrayBuffer

/**A trait that declares the methods that are going to be used in a party
 *
 */
trait TParty {
  def getMembers(): ArrayBuffer[Option[Attributes]]

  def defeated(): Int

  def addMember(memb:Attributes): Unit
}
