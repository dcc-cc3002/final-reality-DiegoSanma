package exceptions

import characters.{MagoBlanco, Paladin}
import enemigo.Enemigo
import staff.Staff
import sword.Sword

class SameClassAttackTest extends munit.FunSuite {
  var paladin: Paladin = null
  var mage: MagoBlanco = null
  var sword: Sword = null
  var staff: Staff = null
  var enemy1: Enemigo = null
  var enemy2: Enemigo = null

  override def beforeEach(context: BeforeEach): Unit = {
    paladin = new Paladin("Sanma",80,80,80)
    mage = new MagoBlanco("Gandalf",100,100,100,100)
    sword = new Sword("Excalibur",80,80)
    staff = new Staff("Staff",100,70,70)
    paladin.receiveWeapon(sword)
    paladin.changeWeapon(0)
    mage.receiveWeapon(staff)
    mage.changeWeapon(0)
    enemy1 = new Enemigo("Goblin",30,50,50,50)
    enemy2 = new Enemigo("Golem",100,70,70,100)
  }

  test("Attacking Same Class"){
    var found: Int = 0
    try {
      paladin.attack(mage)
    }
    catch {
      case e: SameClassAttackException => found +=1
    }
    assertEquals(found,1,"Exception not thrown")
    assertEquals(mage.getHp(),100,"Mage should not have taken damage")

    try{
      mage.attack(paladin)
    }
    catch {
      case e: SameClassAttackException => found += 1
    }
    assertEquals(found,2,"Exception not thrown")
    assertEquals(paladin.getHp(),80,"Paladin should not have taken damage")

    try{
      enemy2.attack(enemy1)
    }
    catch {
      case e: SameClassAttackException => found += 1
    }
    assertEquals(found,3,"Exception not thrown")
    assertEquals(enemy1.getHp(),30,"Enemy2 should not have taken damage")
  }



}
