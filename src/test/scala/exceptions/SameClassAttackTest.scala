package exceptions

import characters.{WhiteMage, Paladin}
import enemy.Enemy
import exceptions.damage.SameClassAttackException
import staff.Staff
import sword.Sword

class SameClassAttackTest extends munit.FunSuite {
  var paladin: Paladin = null
  var mage: WhiteMage = null
  var sword: Sword = null
  var staff: Staff = null
  var enemy1: Enemy = null
  var enemy2: Enemy = null

  override def beforeEach(context: BeforeEach): Unit = {
    paladin = new Paladin("Sanma",80,80,80)
    mage = new WhiteMage("Gandalf",100,100,100,100)
    sword = new Sword("Excalibur",80,80)
    staff = new Staff("Staff",100,70,70)
    paladin.receiveWeapon(sword)
    paladin.changeWeapon(0)
    mage.receiveWeapon(staff)
    mage.changeWeapon(0)
    enemy1 = new Enemy("Goblin",30,50,50,50)
    enemy2 = new Enemy("Golem",100,70,70,100)
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
