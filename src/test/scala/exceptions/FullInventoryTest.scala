package exceptions

import bow.Bow
import characters.{MagoNegro, Ninja}
import staff.Staff
import sword.Sword
import wand.Wand

class FullInventoryTest extends munit.FunSuite {
  var ninja: Ninja = null
  var mage: MagoNegro = null
  var weapon: Sword = null
  var magicweapon: Wand = null
  var magicweapon2: Staff = null
  var bow: Bow = null
  var weapon2: Sword = null

  override def beforeEach(context: BeforeEach): Unit = {
    ninja = new Ninja("Sanma",70,50,50)
    mage = new MagoNegro("Gandalf",80,100,90,100)
    weapon = new Sword("Excalibur",60,70)
    magicweapon = new Wand("Palito",20,40,80)
    magicweapon2 = new Staff("Palo",80,90,100)
    bow = new Bow("Ak",60,90)
    weapon2 = new Sword("",80,90)
  }
  test("Full Inventory Character"){
    ninja.receiveWeapon(weapon)
    ninja.receiveWeapon(magicweapon)
    ninja.receiveWeapon(bow)
    assertEquals(ninja.getWeapons().length,3,"Not all 3 weapons were added to inventory")
    var found: Int = 0
    try{
      ninja.receiveWeapon(weapon2)
    }
    catch {
      case e: FullInventoryException => found +=1
    }
    assertEquals(found,1,"Exception not thrown")
    assertEquals(ninja.getWeapons().length,3,"Inventory should not have changed")
  }
  test("Full Inventory Mage"){
    mage.receiveWeapon(weapon)
    mage.receiveWeapon(magicweapon)
    mage.receiveWeapon(magicweapon2)
    assertEquals(mage.getWeapons().length,3,"Not all 3 weapons were added to inventory")
    var found: Int = 0
    try{
      mage.receiveWeapon(weapon2)
    }
    catch {
      case e: FullInventoryException => found +=1
    }
    assertEquals(found,1,"Exception not thrown")
    assertEquals(mage.getWeapons().length,3,"Inventory should not have changed")
  }
}
