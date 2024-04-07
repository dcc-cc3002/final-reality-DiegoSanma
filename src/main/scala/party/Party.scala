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
   * Returns 1 if the party is not defeated and 0 if it is
   *
   */
  def defeated():Int = {
    val memberArray: ArrayBuffer[Option[Attributes]] = ArrayBuffer(member1,member2,member3,member4,member5)
    var alive:Int = 5
    for (mem<-memberArray){
      if (mem.isDefined) {
        if (mem.hp == 0) {
          alive -= 1
        }
      }
      else {
        alive -= 1
      }
    }
  return alive
  }
}
