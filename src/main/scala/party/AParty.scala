package party

import attributes.Attributes

import scala.collection.mutable.ArrayBuffer

/** Abstract class that defines the methods and parameters of a Party
 *
 * @param member1 first member of the party
 * @param member2 second member of the party
 * @param member3 third member of the party
 * @param member4 fourth member of the party
 * @param member5 fifth member of the party
 */
abstract class AParty(private var member1: Option[Attributes]=None,private var member2: Option[Attributes]=None,private var member3: Option[Attributes]=None,private var member4: Option[Attributes]=None,private var member5: Option[Attributes]=None) extends TParty {

  /**A method that returns an array buffer with all the members currently in the party
   * If the member is a None, it is included as a None in the array buffer
   *
   * @example
   * var party = new AParty(Some(ninja),Some(guerrero),Some(magoblanco))
   * val members = party.getMembers()
   * println(${members}), gives ArrayBuffer(Some(ninja),Some(guerrero),Some(magoblanco))
   *
   * @return ArrayBuffer(member1,member2,member3,member4,member5)
   */
  override def getMembers(): ArrayBuffer[Option[Attributes]] = {
    val memberArray: ArrayBuffer[Option[Attributes]] = ArrayBuffer(member1,member2,member3,member4,member5)
    return memberArray
  }
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
  override def defeated():Int = {
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
  override def addMember(memb: Attributes): Unit = {
    val memberArray: ArrayBuffer[Option[Attributes]] = ArrayBuffer(member1,member2,member3,member4,member5)
    var added = false

    for (index<-memberArray.indices if !added){
      if(memberArray(index).isEmpty){
        memberArray(index) = Some(memb)
        added = true
      }
    }
    this.member1=memberArray(0)
    this.member2=memberArray(1)
    this.member3=memberArray(2)
    this.member4=memberArray(3)
    this.member5=memberArray(4)
  }
}
