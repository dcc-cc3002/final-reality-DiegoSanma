import guerrero.Guerrero
import magoblanco.MagoBlanco
import magonegro.MagoNegro
import ninja.Ninja
import paladin.Paladin

/**This test is for the weapons the characters hold
 *
 * It includes testing for Espada(Sword), Hacha(Axe), Arco(Bow),
 * VaritaMagica(Wand) and Baston(Staff)
 *
 */

class WeaponTest extends munit.FunSuite {
  var sword: Espada = null
  var axe: Hacha = null
  var bow: Arco = null
  var wand: VaritaMagica = null
  var staff: Baston = null

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

    sword = new Espada("Excalibur",100,60,paladin)
    axe = new Hacha("",70,80,guerrero)
    bow = new Arco("Ak",40,50,ninja)
    wand = new VaritaMagica("Boom",60,20,80,magonegro)
    staff = new Baston("Palito",80,55,50,magoblanco)
  }

  test("weapon creation"){
    assertEquals("Excalibur",sword.name,"Sword not given named")
    assertEquals("",axe.name,"Axe not given named")
    assertEquals("Ak",bow.name,"Bow not given named")
    assertEquals("Boom",wand.name,"Wand not given named")
    assertEquals("Palito",staff.name,"Staff not given named")

    assertEquals(100,sword.atkpoints,"Sword not given atkpoints")
    assertEquals(70,axe.atkpoints,"Axe not given atkpoints")
    assertEquals(40,bow.atkpoints,"Bow not given atkpoints")
    assertEquals(60,wand.atkpoints,"Wand not given atkpoints")
    assertEquals(80,staff.atkpoints,"Staff not given atkpoints")

    assertEquals(60,sword.weight,"Sword not given weight")
    assertEquals(80,axe.weight,"Axe not given weight")
    assertEquals(50,bow.weight,"Bow not given weight")
    assertEquals(20,wand.weight,"Wand not given weight")
    assertEquals(55,staff.weight,"Staff not given weight")

    assertEquals(80,wand.magicpts,"Wand not given magic points")
    assertEquals(50,staff.magicpts,"Staff not given magic points")

    assertEquals(paladin,sword.owner,"Sword not given owner")
    assertEquals(guerrero,axe.owner,"Axe not given owner")
    assertEquals(ninja,bow.owner,"Bow not given owner")
    assertEquals(magonegro,wand.owner,"Wand not given owner")
    assertEquals(magoblanco,staff.owner,"Staff not given owner")
  }


}
