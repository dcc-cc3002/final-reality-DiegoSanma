import guerrero.Guerrero
import magoblanco.MagoBlanco
import magonegro.MagoNegro
import ninja.Ninja
import paladin.Paladin

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
    magonegro = new MagoNegro("Balbontin", 60, 50, 80, 100)
    magoblanco = new MagoBlanco("Duarte", 70, 60, 80, 90)

    party1 = new Party(Some(paladin),Some(guerrero))
    party2 = new Party(Some(ninja),Some(magoblanco))
  }
  test("party creation"){

  }

}
