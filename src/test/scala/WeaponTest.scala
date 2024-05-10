import characters.{Guerrero, MagoBlanco, MagoNegro, Ninja, Paladin}
import weapons.{AMagicWeapon, AWeapon, MagicWeapon, TWeapons, Weapon}


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
    weapon = new Weapon("Excalibur",60,70)
    magicweapon = new MagicWeapon("Palito",20,40,80)
    notnamew = new Weapon("",80,90)
    notnamedw = new MagicWeapon("",30,40,50)
  }

  test("weapon creation"){
    assertEquals("Excalibur", weapon.getWeaponName(),"Weapon not given named")
    assertEquals("Palito",magicweapon.getWeaponName(),"Magic Weapon not given name")


    assertEquals(60,weapon.getAtkPts(),"Weapon not given atkpoints")
    assertEquals(20,magicweapon.getAtkPts(),"Magic Weapon not given atkpoints")

    assertEquals(70,weapon.getWeaponWeight(),"Weapon not given weight")
    assertEquals(40,magicweapon.getWeaponWeight(),"Magic Weapon not given weight")


    assertEquals(80,magicweapon.getMagicpts(),"Magic Weapon not given magic points")

    assert(weapon.getOwner().isEmpty,"Should have no owner")
    assert(magicweapon.getOwner().isEmpty,"Should have no owner")
  }

  test("having and leaving owner"){
    var sword: Weapon = new Weapon("Blade of Truth",60,70)
    var wood: MagicWeapon = new MagicWeapon("Pinocchio",70,50,50)

    assert(ninja.getActiveWeapon().isEmpty,"Ninja should not have a weapon")
    assert(sword.getOwner().isEmpty,"Sword should have no owner")
    ninja.receiveWeapon(sword)
    assert(ninja.getWeapons().nonEmpty,"Ninja still has no weapon")
    assertEquals(ninja.getWeapons()(0),sword,"Ninja was not given the sword")
    assert(sword.getOwner().isDefined,"Sword should have an owner")
    assertEquals(sword.getOwner().get,ninja,"Sword does not have ninja as its owner")

    ninja.dropWeapon(sword)
    assert(ninja.getActiveWeapon().isEmpty,"Ninja should not have a weapon")
    assert(sword.getOwner().isEmpty,"Sword should have no owner")


    assert(wood.getOwner().isEmpty)
    magonegro.receiveWeapon(wood)
    assert(magonegro.getWeapons().nonEmpty,"Mago negro currently has no weapon")
    assertEquals(magonegro.getWeapons()(0),wood,"Black Mage not given wood as weapon")
    assert(wood.getOwner().isDefined,"Wood should have an owner")
    assertEquals(wood.getOwner().get,magonegro,"Wood does not have Black Mage as its owner")
    magonegro.dropWeapon(wood)
    assert(magonegro.getActiveWeapon().isEmpty,"Black Mage should not have a weapon")
    assert(wood.getOwner().isEmpty,"Wood should have no owner")
  }


  test("rename"){
    val newnamew = "The Holy"
    val newnamedw = "The Black Fairy"
    val nnw = "Hell´s Blade"
    val nndw = "The Stick of Truth"
    notnamew.rename(newnamew)
    notnamedw.rename(newnamedw)
    assertEquals(newnamew,notnamew.getWeaponName(),"The weapon was not renamed correctly")
    assertEquals(newnamedw,notnamedw.getWeaponName(),"The Magic Weapon was not renamed correctly")
    notnamew.rename(nnw)
    notnamedw.rename(nndw)
    assertEquals("The Holy",notnamew.getWeaponName(),"The weapon should not be renamed")
    assertEquals("The Black Fairy",notnamedw.getWeaponName(),"The Magic Weapon should not be renamed")
  }


}
