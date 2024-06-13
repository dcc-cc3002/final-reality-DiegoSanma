package exceptions

import axe.Axe
import characters.{Paladin, Warrior}
import enemy.Enemy
import exceptions.damage.UnaliveDamagedException
import sword.Sword

class UnaliveAttackedTest extends munit.FunSuite {
  var paladin: Paladin = null
  var enemy: Enemy = null
  var sword: Sword = null

  test("Attacking a dead enemy"){
    var found:Int = 0
    paladin = new Paladin("Diego",100,100,100)
    enemy = new Enemy("Goblin",0,30,30,30)
    sword = new Sword("Excalibur",150,100)
    paladin.receiveWeapon(sword)
    paladin.changeWeapon(0)
    try{
      paladin.attack(enemy)
    }
    catch {
      case e: UnaliveDamagedException => found +=1
    }
    assertEquals(found,1,"Exception not thrown")
    assertEquals(enemy.getHp(),0,"Enemy´s hp is still 0")
  }

  test("Attacking a dead character"){
    var found:Int = 0
    paladin = new Paladin("Diego",0,100,100)
    enemy = new Enemy("Goblin",100,30,30,30)
    try{
      enemy.attack(paladin)
    }
    catch {
      case e: UnaliveDamagedException => found +=1
    }
    assertEquals(found,1,"Exception not thrown")
    assertEquals(paladin.getHp(),0,"Paladin´s hp is still 0")
  }
}
