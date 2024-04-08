package party

import attributes.Attributes
import scala.collection.mutable.ArrayBuffer

/**A class that defines a party of characters
 *
 * A party is defined by each of its memeber, which can be maximum five
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

  /**A function that determines wether a party is defeated or not
   *
   * Returns the amount of party members alive
   * If it return 0,it is because the party has been defeated
   *
   */
  def defeated():Int = {
    val memberArray: ArrayBuffer[Option[Attributes]] = ArrayBuffer(member1,member2,member3,member4,member5)
    var alive:Int = 5
    for (mem<-memberArray){
      val status= if (mem.isDefined) mem.get.hp else 0
      if (status ==0){
        alive-=1
      }
    }
  return alive
  }
}
