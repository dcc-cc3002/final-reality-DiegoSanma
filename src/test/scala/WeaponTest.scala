import guerrero.Guerrero
import magoblanco.MagoBlanco
import magonegro.MagoNegro
import ninja.Ninja
import paladin.Paladin
import weapons.{MagicWeapon, Weapon}


/**This test is for the weapons the characters hold
 *
 * It includes testing for Espada(Sword), Hacha(Axe), Arco(Bow),
 * VaritaMagica(Wand) and Baston(Staff)
 *
 */

class WeaponTest extends munit.FunSuite {
  var weapon: Weapon = null
  var magicweapon: MagicWeapon = null
  var notnamew: Weapon = null
  var notnamedw: MagicWeapon = null
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
    weapon = new Weapon("Excalibur",60,70,paladin)
    magicweapon = new MagicWeapon("Palito",20,40,80,magonegro)
    notnamew = new Weapon("",80,90,guerrero)
    notnamedw = new MagicWeapon("",30,40,50,magoblanco)
  }

  test("weapon creation"){
    assertEquals("Excalibur", weapon.name,"Weapon not given named")
    assertEquals("Palito",magicweapon.name,"Magic Weapon not given name")


    assertEquals(60,weapon.atkpoints,"Weapon not given atkpoints")
    assertEquals(20,magicweapon.atkpoints,"Magic Weapon not given atkpoints")

    assertEquals(70,weapon.weight,"Weapon not given weight")
    assertEquals(40,magicweapon.weight,"Magic Weapon not given weight")


    assertEquals(80,magicweapon.magicpts,"Magic Weapon not given magic points")

    assertEquals(paladin,weapon.owner,"Weapon not given owner")
    assertEquals(guerrero,magicweapon.owner,"Magic Weapon not given owner")
  }


  test("rename"){
    val newnamew = ("The Holy")
    val newnamedw = ("The Black Fairy")
    val nnw = ("Hell´s Blade")
    val nndw = ("The Stick of Truth")
    notnamew.rename(newnamew)
    notnamedw.rename(newnamedw)
    assertEquals(newnamew,notnamew.name,"The weapon was not renamed correctly")
    assertEquals(newnamedw,notnamedw.name,"The Magic Weapon was not renamed correctly")
    notnamew.rename(nnw)
    notnamedw.rename(nndw)
    assertEquals("The Holy",notnamew.name,"The weapon should not be renamed")
    assertEquals("The Black Fairy",notnamedw.name,"The Magic Weapon should not be renamed")
  }


}
