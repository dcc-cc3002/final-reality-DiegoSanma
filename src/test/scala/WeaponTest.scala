import characters.{BlackMage, Ninja, Paladin, Warrior, WhiteMage}
import exceptions.weaponexceptions.AlreadyNamedException
import sword.Sword
import wand.Wand
import weapons.{AMagicWeapon, AWeapon, TWeapons}


/**This test is for the weapons the characters hold
 *
 * It includes testing for Espada(Sword), Hacha(Axe), Arco(bow.Bow),
 * VaritaMagica(Wand) and Baston(Staff)
 *
 */

class WeaponTest extends munit.FunSuite {
  var weapon: Sword = null
  var magicweapon: Wand = null
  var notnamew: Sword = null
  var notnamedw: Wand = null
  var paladin: Paladin = null
  var guerrero: Warrior = null
  var ninja: Ninja = null
  var magonegro: BlackMage = null
  var magoblanco: WhiteMage = null

  override def beforeEach(context: BeforeEach): Unit = {
    paladin = new Paladin("Diego", 100, 90, 120)
    guerrero = new Warrior("Lucas", 80, 100, 100)
    ninja = new Ninja("Santiago", 60, 70, 60)
    magonegro = new BlackMage("Balbontin", 60, 50, 80, 100)
    magoblanco = new WhiteMage("Duarte", 70, 60, 80, 90)
    weapon = new Sword("Excalibur",60,70)
    magicweapon = new Wand("Palito",20,40,80)
    notnamew = new Sword("",80,90)
    notnamedw = new Wand("",30,40,50)
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
    var sword: Sword = new Sword("Blade of Truth",60,70)
    var wood: Wand = new Wand("Pinocchio",70,50,50)

    assert(ninja.getActiveWeapon().isEmpty,"Ninja should not have a weapon")
    assert(sword.getOwner().isEmpty,"Sword should have no owner")
    ninja.receiveWeapon(sword)
    assert(ninja.getWeapons().nonEmpty,"Ninja still has no weapon")
    assertEquals(ninja.getWeapons()(0),sword,"Ninja was not given the sword")
    assert(sword.getOwner().isDefined,"Sword should have an owner")
    assertEquals(sword.getOwner().get,ninja,"Sword does not have ninja as its owner")

    ninja.dropWeapon(0)
    assert(ninja.getActiveWeapon().isEmpty,"Ninja should not have a weapon")
    assert(sword.getOwner().isEmpty,"Sword should have no owner")


    assert(wood.getOwner().isEmpty)
    magonegro.receiveWeapon(wood)
    assert(magonegro.getWeapons().nonEmpty,"Mago negro currently has no weapon")
    assertEquals(magonegro.getWeapons()(0),wood,"Black Mage not given wood as weapon")
    assert(wood.getOwner().isDefined,"Wood should have an owner")
    assertEquals(wood.getOwner().get,magonegro,"Wood does not have Black Mage as its owner")
    magonegro.dropWeapon(0)
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
    var found: Int = 0
    try{
      notnamew.rename(nnw)
    }
    catch {
      case e: AlreadyNamedException => found +=1
    }
    assertEquals(found,1,"Exception not thrown")
    assertEquals("The Holy",notnamew.getWeaponName(),"The weapon should not be renamed")
    try{
      notnamedw.rename(nndw)
    }
    catch {
      case e: AlreadyNamedException => found += 1
    }
    assertEquals(found,2,"Exception not thrown")
    assertEquals("The Black Fairy",notnamedw.getWeaponName(),"The Magic Weapon should not be renamed")
  }


}
