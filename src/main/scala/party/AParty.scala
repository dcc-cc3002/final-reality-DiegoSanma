package party

import attributes.Attributes
import exceptions.FullPartyException

import scala.collection.mutable.ArrayBuffer

/** Abstract class that defines the methods and parameters of a Party
 *
 * @param memberArray array with all current party members
 */
abstract class AParty(private var memberArray:ArrayBuffer[Option[Attributes]]) extends TParty {
  /** When a party is created, we must check if it begins with more than the allowed members */
  if(memberArray.length>3){
    throw new FullPartyException("Party cant begin with more than 3 members")
  }

  /**A method that returns an array buffer with all the members currently in the party
   * If the member is a None, it is included as a None in the array buffer
   *
   * @example
   * var party = new AParty(Some(ninja),Some(guerrero),Some(magoblanco))
   * val members = party.getMembers()
   * println(${members}), gives ArrayBuffer(Some(ninja),Some(guerrero),Some(magoblanco))
   *
   * @return ArrayBuffer(member1,member2,member3)
   */
  override def getMembers(): ArrayBuffer[Option[Attributes]] = {
    return this.memberArray
  }
  /**A function that determines whether a party is defeated or not
   *
   * Returns the amount of party members alive
   * If it return 0,it is because the party has been defeated
   *
   * If there is no one in the party, it will return 0, assuming the party was defeated
   *
   * @example
   * var party = new Party(Some(guerrero),Some(ninja),None,None,None)
   * var alive: Int = party.defeated()
   * println("The amount of people alive in the party is ${alive}"), should return 2
   *
   * @author Diego San Martin
   */
  override def defeated():Int = {
    var alive:Int = 0
    for (mem<-this.memberArray){
      val status= if (mem.isDefined) mem.get.getHp() else 0
      if (status !=0){
        alive+=1
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
   * @throws FullPartyException if party is full and another members want to be added
   *
   */
  override def addMember(memb: Attributes): Unit = {
    var length = memberArray.length
    if(length>3){
      throw new FullPartyException("Party already has 3 members")
    }
    else{
      memberArray.addOne(Some(memb))
    }


  }
}
