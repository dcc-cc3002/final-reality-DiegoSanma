import attributes.Attributes
import characters.{Warrior, WhiteMage, BlackMage, Ninja, Paladin}
import party.Party

import scala.collection.mutable.ArrayBuffer

class PartyTest extends munit.FunSuite{
  var party1: Party = null
  var party2: Party = null
  var party3: Party = null
  var party4: Party = null
  var paladin: Paladin = null
  var anotherpaladin: Paladin = null
  var guerrero: Warrior = null
  var ninja: Ninja = null
  var magonegro: BlackMage = null
  var magoblanco: WhiteMage = null
  var anothermagoblanco: WhiteMage = null
  var deadpaladin: Paladin = null
  var deadninja: Ninja = null

  override def beforeEach(context: BeforeEach): Unit = {
    paladin = new Paladin("Diego", 100, 90, 120)
    anotherpaladin = new Paladin("Saka",120,80,80)
    guerrero = new Warrior("Lucas", 80, 100, 100)
    ninja = new Ninja("Santiago", 60, 70, 60)
    magonegro = new BlackMage("Balbontin", 60, 50, 80, 100)
    magoblanco = new WhiteMage("Duarte", 70, 60, 80, 90)
    anothermagoblanco = new WhiteMage("Leo",60,50,40,100)
    deadpaladin = new Paladin("Duke",0,40,20)
    deadninja = new Ninja("Kai",0,50,70)

    party1 = new Party(ArrayBuffer(Some(paladin),Some(guerrero)))
    party2 = new Party(ArrayBuffer(Some(magoblanco)))
    party3 = new Party(ArrayBuffer(Some(deadpaladin),Some(deadninja)))
    party4 = new Party(ArrayBuffer.empty)
  }
  test("party creation"){
    var actual_member1 = party1.getMembers()(0)
    assertEquals(actual_member1.isDefined,true,"Member1 is None")
    assertEquals(actual_member1.get.isInstanceOf[Paladin],true,"Member 1 is not a Paladin")
    assertEquals(actual_member1.get,paladin, "Member1 is not the Paladin we gave it")

    var actual_member2 = party1.getMembers()(1)
    assertEquals(actual_member2.isDefined,true,"Member2 is None")
    assertEquals(actual_member2.get.isInstanceOf[Warrior],true,"Member 2 is not a Guerrero")
    assertEquals(actual_member2.get,guerrero,"Member2 is not the Guerrero we gave it")

    assertEquals(party1.getMembers().length,2,"There should only be 2 members in the party")

    var actual_members = party1.getMembers()
    assertEquals(actual_members,ArrayBuffer(actual_member1,actual_member2))

    var p2_member = party2.getMembers()(0)
    assertEquals(p2_member.isDefined,true,"Member1 is None")
    assertEquals(p2_member.get.isInstanceOf[WhiteMage],true,"Member 1 is not a Mago Blanco")
    assertEquals(p2_member.get,magoblanco,"Member1 is not the Mago Blanco we gave it")
    var actual_members2 = party2.getMembers()
    assertEquals(actual_members2,ArrayBuffer(p2_member))
  }

  test("party status"){
    assertEquals(2,party1.defeated())
    assertEquals(1,party2.defeated())
    assertEquals(0,party3.defeated())
  }

  test("adding members"){
    party1.addMember(ninja)
    var actual_member1 = party1.getMembers()(0)
    var actual_member2 = party1.getMembers()(1)
    var actual_member3 = party1.getMembers()(2)
    assertEquals(actual_member3.isDefined,true,"Member 3 was not added")
    assertEquals(actual_member3.get.isInstanceOf[Ninja],true,"Member 3 was not added as a Ninja")
    assertEquals(actual_member3.get,ninja,"Member 3 was not added as the Ninja we wanted")
    assertEquals(actual_member1.get,paladin,"Member 1 was changed from the original Paladin")
    assertEquals(actual_member2.get,guerrero,"Member 2 was changed from the orginial Guerrero")
    party4.addMember(magonegro)
    actual_member1 = party4.getMembers()(0)

    assertEquals(actual_member1.get,magonegro,"Member 1 should be the black mage")
    assertEquals(party4.getMembers().length,1,"Party should only have one member")

    party4.addMember(anothermagoblanco)
    actual_member1 = party4.getMembers()(0)
    actual_member2 = party4.getMembers()(1)

    assertEquals(actual_member1.get,magonegro,"Member 1 was changed from the original MagoNegro")
    assertEquals(actual_member2.get,anothermagoblanco,"Member 2 should be another MagoBlanco")
  }
}
