import axe.Axe
import bow.Bow
import characters.{Warrior, WhiteMage, BlackMage, Ninja, Paladin}
import exceptions.weaponexceptions.InvalidWeaponTypeException
import staff.Staff
import sword.Sword
import wand.Wand

/**Test for different type of weapon holding
 *
 */

class WeaponHoldingTest extends munit.FunSuite {
  var paladin: Paladin = null
  var guerrero: Warrior = null
  var ninja: Ninja = null
  var magonegro: BlackMage = null
  var magoblanco: WhiteMage = null
  var sword: Sword = null
  var axe: Axe = null
  var bow: Bow = null
  var wand: Wand = null
  var staff: Staff = null

  override def beforeEach(context: BeforeEach): Unit = {
    sword = new Sword("Excalibur",90,90)
    axe = new Axe("Betty",100,120)
    bow = new Bow("AK",50,50)
    wand = new Wand("Stick of Truth",70,20,90)
    staff = new Staff("Ponder",80,120,100)
  }

  test("Paladin wielding") {
    paladin = new Paladin("Diego", 70, 70, 70)
    paladin.receiveWeapon(sword)
    assertEquals(paladin.getWeapons()(0), sword, "Sword not added to paladin´s inventory")
    paladin.receiveWeapon(axe)
    assertEquals(paladin.getWeapons()(1), axe, "Axe not added to paladin´s inventory")
    var notGiven: Int = 0
    try {
      paladin.receiveWeapon(bow)
    }
    catch {
      case e: InvalidWeaponTypeException => notGiven += 1
    }
    assertEquals(notGiven, 1, "Exception not thrown")
    assertEquals(paladin.getWeapons().length, 2, "Nothing should´ve been added to the inventory")
    try {
      paladin.receiveWeapon(wand)
    }
    catch {
      case e: InvalidWeaponTypeException => notGiven += 1
    }
    assertEquals(notGiven, 2, "Exception not thrown")
    assertEquals(paladin.getWeapons().length, 2, "Nothing should´ve been added to the inventory")

    try {
      paladin.receiveWeapon(staff)
    }
    catch {
      case e: InvalidWeaponTypeException => notGiven += 1
    }
    assertEquals(notGiven, 3, "Exception not thrown")
    assertEquals(paladin.getWeapons().length, 2, "Nothing should´ve been added to the inventory")

  }
  test("Guerrero wielding") {
    guerrero = new Warrior("Diego", 70, 70, 70)
    guerrero.receiveWeapon(sword)
    assertEquals(guerrero.getWeapons()(0), sword, "Sword not added to guerrero´s inventory")
    guerrero.receiveWeapon(axe)
    assertEquals(guerrero.getWeapons()(1), axe, "Axe not added to guerrero´s inventory")
    guerrero.dropWeapon(axe)
    guerrero.receiveWeapon(bow)
    assertEquals(guerrero.getWeapons()(1), bow, "Bow not added to guerrero´s inventory")

    var notGiven: Int = 0
    try {
      guerrero.receiveWeapon(wand)
    }
    catch {
      case e: InvalidWeaponTypeException => notGiven += 1
    }
    assertEquals(notGiven, 1, "Exception not thrown")
    assertEquals(guerrero.getWeapons().length, 2, "Nothing should´ve been added to the inventory")

    try {
      guerrero.receiveWeapon(staff)
    }
    catch {
      case e: InvalidWeaponTypeException => notGiven += 1
    }
    assertEquals(notGiven, 2, "Exception not thrown")
    assertEquals(guerrero.getWeapons().length, 2, "Nothing should´ve been added to the inventory")

  }
  test("Ninja wielding") {
    ninja = new Ninja("Diego", 70, 70, 70)
    ninja.receiveWeapon(sword)
    assertEquals(ninja.getWeapons()(0), sword, "Sword not added to ninja´s inventory")
    ninja.receiveWeapon(wand)
    assertEquals(ninja.getWeapons()(1), wand, "Wand not added to ninja´s inventory")
    ninja.dropWeapon(wand)
    ninja.receiveWeapon(bow)
    assertEquals(ninja.getWeapons()(1), bow, "Bow not added to ninja´s inventory")

    var notGiven: Int = 0
    try {
      ninja.receiveWeapon(axe)
    }
    catch {
      case e: InvalidWeaponTypeException => notGiven += 1
    }
    assertEquals(notGiven, 1, "Exception not thrown")
    assertEquals(ninja.getWeapons().length, 2, "Nothing should´ve been added to the inventory")

    try {
      ninja.receiveWeapon(staff)
    }
    catch {
      case e: InvalidWeaponTypeException => notGiven += 1
    }
    assertEquals(notGiven, 2, "Exception not thrown")
    assertEquals(ninja.getWeapons().length, 2, "Nothing should´ve been added to the inventory")

  }
  test("MagoBlanco wielding") {
    magoblanco = new WhiteMage("Diego", 70, 70, 70,70)
    magoblanco.receiveWeapon(staff)
    assertEquals(magoblanco.getWeapons()(0), staff, "Staff not added to magoblanco´s inventory")
    magoblanco.receiveWeapon(wand)
    assertEquals(magoblanco.getWeapons()(1), wand, "Wand not added to magoblanco´s inventory")
    magoblanco.dropWeapon(wand)
    magoblanco.receiveWeapon(bow)
    assertEquals(magoblanco.getWeapons()(1), bow, "Bow not added to magoblanco´s inventory")

    var notGiven: Int = 0
    try {
      magoblanco.receiveWeapon(axe)
    }
    catch {
      case e: InvalidWeaponTypeException => notGiven += 1
    }
    assertEquals(notGiven, 1, "Exception not thrown")
    assertEquals(magoblanco.getWeapons().length, 2, "Nothing should´ve been added to the inventory")

    try {
      magoblanco.receiveWeapon(sword)
    }
    catch {
      case e: InvalidWeaponTypeException => notGiven += 1
    }
    assertEquals(notGiven, 2, "Exception not thrown")
    assertEquals(magoblanco.getWeapons().length, 2, "Nothing should´ve been added to the inventory")

  }
  test("MagoNegro wielding") {
    magonegro = new BlackMage("Diego", 70, 70, 70,70)
    magonegro.receiveWeapon(staff)
    assertEquals(magonegro.getWeapons()(0), staff, "Staff not added to magonegro´s inventory")
    magonegro.receiveWeapon(wand)
    assertEquals(magonegro.getWeapons()(1), wand, "Wand not added to magonegro´s inventory")
    magonegro.dropWeapon(wand)
    magonegro.receiveWeapon(sword)
    assertEquals(magonegro.getWeapons()(1), sword, "Sword not added to magonegro´s inventory")

    var notGiven: Int = 0
    try {
      magonegro.receiveWeapon(axe)
    }
    catch {
      case e: InvalidWeaponTypeException => notGiven += 1
    }
    assertEquals(notGiven, 1, "Exception not thrown")
    assertEquals(magonegro.getWeapons().length, 2, "Nothing should´ve been added to the inventory")

    try {
      magonegro.receiveWeapon(bow)
    }
    catch {
      case e: InvalidWeaponTypeException => notGiven += 1
    }
    assertEquals(notGiven, 2, "Exception not thrown")
    assertEquals(magonegro.getWeapons().length, 2, "Nothing should´ve been added to the inventory")

  }
}
