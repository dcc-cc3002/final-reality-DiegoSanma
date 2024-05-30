package exceptions

import characters.{MagoNegro, Paladin}
import exceptions.weaponexceptions.AlreadyOwnedException
import sword.Sword

class AlreadyOwnedTest extends munit.FunSuite {
  var paladin: Paladin = null
  var mage: MagoNegro = null
  var sword: Sword = null

  override def beforeEach(context: BeforeEach): Unit = {
    paladin = new Paladin("Diego",90,90,90)
    mage = new MagoNegro("Gandalf",100,90,70,100)
    sword = new Sword("Excalibur",100,100)
  }
  test("Already Owned Character"){
    paladin.receiveWeapon(sword)
    assert(sword.getOwner().isDefined,"Sword should now have an owner")
    var found:Int = 0
    try{
      mage.receiveWeapon(sword)
    }
    catch {
      case e: AlreadyOwnedException => found += 1
    }
    assertEquals(found,1,"Exception not thrown")
    assertEquals(sword.getOwner().get,paladin,"Owner should still be paladin")
    assertEquals(paladin.getWeapons()(0),sword,"Sword is still in paladin´s inventory")
    assert(mage.getWeapons().isEmpty,"Mage should not receive ant weapon")
  }

  test("Already Owned Mage"){
    mage.receiveWeapon(sword)
    assert(sword.getOwner().isDefined,"Sword should now have an owner")
    var found:Int = 0
    try{
      paladin.receiveWeapon(sword)
    }
    catch {
      case e: AlreadyOwnedException => found += 1
    }
    assertEquals(found,1,"Exception not thrown")
    assertEquals(sword.getOwner().get,mage,"Owner should still be mage")
    assertEquals(mage.getWeapons()(0),sword,"Sword is still in mage´s inventory")
    assert(paladin.getWeapons().isEmpty,"Paladin should not receive any weapon")
  }


}
