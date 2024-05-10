import attributes.Attributes
import characters.{Guerrero, MagoBlanco, MagoNegro, Ninja, Paladin}
import party.Party

import scala.collection.mutable.ArrayBuffer

class PartyTest extends munit.FunSuite{
  var party1: Party = null
  var party2: Party = null
  var party3: Party = null
  var paladin: Paladin = null
  var anotherpaladin: Paladin = null
  var guerrero: Guerrero = null
  var ninja: Ninja = null
  var magonegro: MagoNegro = null
  var magoblanco: MagoBlanco = null
  var anothermagoblanco: MagoBlanco = null
  var deadpaladin: Paladin = null
  var deadninja: Ninja = null

  override def beforeEach(context: BeforeEach): Unit = {
    paladin = new Paladin("Diego", 100, 90, 120)
    anotherpaladin = new Paladin("Saka",120,80,80)
    guerrero = new Guerrero("Lucas", 80, 100, 100)
    ninja = new Ninja("Santiago", 60, 70, 60)
    magonegro = new MagoNegro("Balbontin", 60, 50, 80, 100)
    magoblanco = new MagoBlanco("Duarte", 70, 60, 80, 90)
    anothermagoblanco = new MagoBlanco("Leo",60,50,40,100)
    deadpaladin = new Paladin("Duke",0,40,20)
    deadninja = new Ninja("Kai",0,50,70)

    party1 = new Party(Some(paladin),Some(guerrero))
    party2 = new Party(Some(magoblanco))
    party3 = new Party(Some(deadpaladin),Some(deadninja))
  }
  test("party creation"){
    var actual_member1 = party1.getMembers()(0)
    assertEquals(actual_member1.isDefined,true,"Member1 is None")
    assertEquals(actual_member1.get.isInstanceOf[Paladin],true,"Member 1 is not a Paladin")
    assertEquals(actual_member1.get,paladin, "Member1 is not the Paladin we gave it")

    var actual_member2 = party1.getMembers()(1)
    assertEquals(actual_member2.isDefined,true,"Member2 is None")
    assertEquals(actual_member2.get.isInstanceOf[Guerrero],true,"Member 2 is not a Guerrero")
    assertEquals(actual_member2.get,guerrero,"Member2 is not the Guerrero we gave it")

    var actual_member3 = party1.getMembers()(2)
    assertEquals(actual_member3.isDefined,false,"There is a Member3")

    var actual_members = party1.getMembers()
    assertEquals(actual_members,ArrayBuffer(actual_member1,actual_member2,actual_member3,None,None))

    var p2_member = party2.getMembers()(0)
    assertEquals(p2_member.isDefined,true,"Member1 is None")
    assertEquals(p2_member.get.isInstanceOf[MagoBlanco],true,"Member 1 is not a Mago Blanco")
    assertEquals(p2_member.get,magoblanco,"Member1 is not the Mago Blanco we gave it")
    var actual_members2 = party2.getMembers()
    assertEquals(actual_members2,ArrayBuffer(p2_member,None,None,None,None))
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
    var actual_member4 = party1.getMembers()(3)
    var actual_member5 = party1.getMembers()(4)
    assertEquals(actual_member3.isDefined,true,"Member 3 was not added")
    assertEquals(actual_member3.get.isInstanceOf[Ninja],true,"Member 3 was not added as a Ninja")
    assertEquals(actual_member3.get,ninja,"Member 3 was not added as the Ninja we wanted")
    assertEquals(actual_member1.get,paladin,"Member 1 was changed from the original Paladin")
    assertEquals(actual_member2.get,guerrero,"Member 2 was changed from the orginial Guerrero")
    assertEquals(actual_member4.isDefined,false,"Someone was added as Member 4")
    assertEquals(actual_member5.isDefined,false,"Someone was added as Member 5")
    party1.addMember(magonegro)
    actual_member1 = party1.getMembers()(0)
    actual_member2 = party1.getMembers()(1)
    actual_member3 = party1.getMembers()(2)
    actual_member4 = party1.getMembers()(3)
    actual_member5 = party1.getMembers()(4)

    assertEquals(actual_member4.get,magonegro,"Member 4 is not the Mago Negro we wanted")
    assertEquals(actual_member3.get,ninja,"Member 3 was not added as the Ninja we wanted")
    assertEquals(actual_member1.get,paladin,"Member 1 was changed from the original Paladin")
    assertEquals(actual_member2.get,guerrero,"Member 2 was changed from the orginial Guerrero")
    assertEquals(actual_member5.isDefined,false,"Someone was added as Member 5")
    party1.addMember(anothermagoblanco)
    actual_member1 = party1.getMembers()(0)
    actual_member2 = party1.getMembers()(1)
    actual_member3 = party1.getMembers()(2)
    actual_member4 = party1.getMembers()(3)
    actual_member5 = party1.getMembers()(4)

    assertEquals(actual_member4.get,magonegro,"Member 4 is not the Mago Negro we wanted")
    assertEquals(actual_member3.get,ninja,"Member 3 was not added as the Ninja we wanted")
    assertEquals(actual_member1.get,paladin,"Member 1 was changed from the original Paladin")
    assertEquals(actual_member2.get,guerrero,"Member 2 was changed from the orginial Guerrero")
    assertEquals(actual_member5.get,anothermagoblanco,"Member 5 is not the Mago Blanco we wanted")
    party1.addMember(anotherpaladin)
    actual_member1 = party1.getMembers()(0)
    actual_member2 = party1.getMembers()(1)
    actual_member3 = party1.getMembers()(2)
    actual_member4 = party1.getMembers()(3)
    actual_member5 = party1.getMembers()(4)

    assertEquals(actual_member4.get,magonegro,"Member 4 is not the Mago Negro we wanted")
    assertEquals(actual_member3.get,ninja,"Member 3 was not added as the Ninja we wanted")
    assertEquals(actual_member1.get,paladin,"Member 1 was changed from the original Paladin")
    assertEquals(actual_member2.get,guerrero,"Member 2 was changed from the orginial Guerrero")
    assertEquals(actual_member5.get,anothermagoblanco,"Member 5 is not the Mago Blanco we wanted")


  }
}
