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
 *
 * @author Diego San Martin
 *
 */
class Party (member1:Option[Attributes]=None,member2:Option[Attributes]=None,member3:Option[Attributes]=None,
             member4:Option[Attributes]=None,member5:Option[Attributes]=None)
             extends AParty(member1,member2,member3, member4, member5) {
}
