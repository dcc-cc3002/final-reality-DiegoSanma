package exceptions

import characters.{Guerrero, MagoBlanco, MagoNegro, Ninja}
import partyexc.{FullPartyException}
import party.Party

import scala.collection.mutable.ArrayBuffer

class FullPartyTest extends munit.FunSuite {
  var party1: Party = null
  var party2: Party = null

  var guerrero: Guerrero = null
  var ninja: Ninja = null
  var magonegro: MagoNegro = null
  var magoblanco: MagoBlanco = null

  override def beforeEach(context: BeforeEach): Unit = {
    party1 = new Party(ArrayBuffer(Some(ninja),Some(guerrero),Some(magoblanco)))
  }

  test("Initializing overfilled party") {
    var caught: Int = 0
    try {
      party2 = new Party(ArrayBuffer(Some(ninja), Some(guerrero), Some(magoblanco), Some(magoblanco)))
    }
    catch{
      case e: FullPartyException => caught +=1
    }
    assertEquals(caught,1,"The exception was not thrown")
    assertEquals(party2,null,"Party should not be initialized")
  }

  test("Adding to Full Party"){
    var caught: Int = 0
    try{
      party1.addMember(magoblanco)
    }
    catch{
      case e: FullPartyException => caught +=1
    }
    assertEquals(caught,1,"The exception was not thrown")
    assertEquals(party1.getMembers().length,3,"Party should remain with the same amount of members")
  }
}
