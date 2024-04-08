import attributes.Attributes
import guerrero.Guerrero
import magoblanco.MagoBlanco
import magonegro.MagoNegro
import ninja.Ninja
import paladin.Paladin
import party.Party

class PartyTest extends munit.FunSuite{
  var party1: Party = null
  var party2: Party = null
  var paladin: Paladin = null
  var guerrero: Guerrero = null
  var ninja: Ninja = null
  var magonegro: MagoNegro = null
  var magoblanco: MagoBlanco = null

  override def beforeEach(context: BeforeEach): Unit = {
    paladin = new Paladin("Diego", 100, 90, 120)
    guerrero = new Guerrero("Lucas", 80, 100, 100)
    ninja = new Ninja("Santiago", 60, 70, 60)
    magonegro = new MagoNegro("Balbontin", 60, 50, 80, Some(100))
    magoblanco = new MagoBlanco("Duarte", 70, 60, 80, Some(90))

    party1 = new Party(Some(paladin),Some(guerrero),None,None,None)
    party2 = new Party(Some(magoblanco),None,None,None,None)
  }
  test("party creation"){
    var actual_member1 = party1.member1
    assertEquals(actual_member1.isDefined,true,"Member1 is None")
    assertEquals(actual_member1.get.isInstanceOf[Paladin],true,"Member 1 is not a Paladin")
    assertEquals(actual_member1.get,paladin, "Member1 is not the Paladin we gave it")

  var actual_member2 = party1.member2
  assertEquals(actual_member2.isDefined,true,"Member2 is None")
  assertEquals(actual_member2.get.isInstanceOf[Guerrero],true,"Member 2 is not a Guerrero")
  assertEquals(actual_member2.get,guerrero,"Member2 is not the Guerrero we gave it")

  var actual_member3 = party1.member3
  assertEquals(actual_member3.isDefined,false,"There is a Member3")

  var p2_member = party2.member1
  assertEquals(p2_member.isDefined,true,"Member1 is None")
  assertEquals(p2_member.get.isInstanceOf[MagoBlanco],true,"Member 1 is not a Mago Blanco")
  assertEquals(p2_member.get,magoblanco,"Member1 is not the Mago Blanco we gave it")
  }

  test("party status"){
    assertEquals(2,party1.defeated())
    assertEquals(1,party2.defeated())
    party2.member1.get.hp = 0
    assertEquals(0,party2.defeated())
    party2.member1.get.hp = 60
    assertEquals(1,party2.defeated())
  }

}
