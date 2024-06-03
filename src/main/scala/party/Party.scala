package party

import attributes.Attributes

import scala.collection.mutable.ArrayBuffer


/**A class that defines a party of characters
 *
 * A party is defined by each of its member, which can be maximum three
 *
 * @constructor creates a new party with a possible 3 members
 *
 * @tparam memberArray the member array of the party
 * @author Diego San Martin
 *
 */
class Party (memberArray: ArrayBuffer[Option[Attributes]])
             extends AParty(memberArray) {
}
