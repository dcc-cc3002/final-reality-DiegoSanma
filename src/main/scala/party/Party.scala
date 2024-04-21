package party

import attributes.Attributes

import scala.collection.mutable.ArrayBuffer


/**A class that defines a party of characters
 *
 * A party is defined by each of its member, which can be maximum five
 *
 * @constructor creates a new party with a possible 5 members
 *
 * @tparam member1 the first member of the party
 * @tparam member2 the second member of the party
 * @tparam member3 the third member of the party
 * @tparam member4 the fourth member of the party
 * @tparam member5 the fifth member of the party
 * @author Diego San Martin
 *
 */
class Party (var member1:Option[Attributes],var member2:Option[Attributes],var member3:Option[Attributes],
             var member4:Option[Attributes],var member5:Option[Attributes]){

  /**A function that determines whether a party is defeated or not
   *
   * Returns the amount of party members alive
   * If it return 0,it is because the party has been defeated
   *
   * EXCEPTION: if there is no one in the party, it will return 0, assuming the party was defeated
   *
   * @example
   * var party = new Party(Some(guerrero),Some(ninja),None,None,None)
   * var alive: Int = party.defeated()
   * println("The amount of people alive in the party is ${alive}"), should return 2
   *
   * @author Diego San Martin
   */
  def defeated():Int = {
    val memberArray: ArrayBuffer[Option[Attributes]] = ArrayBuffer(member1,member2,member3,member4,member5)
    var alive:Int = 5
    for (mem<-memberArray){
      val status= if (mem.isDefined) mem.get.getHp() else 0
      if (status ==0){
        alive-=1
      }
    }
  return alive
  }

  /**A function that adds characters to the Party
   *
   * It takes as a parameter, the new member we wish to add
   * This member is added as one of the vacant slots in the party
   *
   *
   * EXCEPTION: if the party is full, the new member is not added, and the party remains the same
   *
   * @example
   * var party = new Party(Some(guerrero),Some(ninja),None,None,None)
   * party.addMember(Some(magonegro))
   * println("The third member of the party is ${party.member3.get}"), should be mago negro
   *
   */
  def addMember(memb: Option[Attributes]): Unit = {
    var memberArray: ArrayBuffer[Option[Attributes]] = ArrayBuffer(member1,member2,member3,member4,member5)
    var added = false

    for (index<-memberArray.indices if !added){
      if(memberArray(index).isEmpty){
        memberArray(index) = memb
        added = true
      }
    }
    member1=memberArray(0)
    member2=memberArray(1)
    member3=memberArray(2)
    member4=memberArray(3)
    member5=memberArray(4)
  }
}
