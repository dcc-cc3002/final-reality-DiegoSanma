package exceptions

import characters.{Guerrero, MagoBlanco, MagoNegro, Ninja, Paladin}
import exceptions.weaponexceptions.InvalidWeaponException
import sword.Sword
import wand.Wand
import weapons.{MagicWeapon, Weapon}

import scala.collection.mutable.ArrayBuffer

class ActiveWeaponTest extends munit.FunSuite {
  var paladin: Paladin = null
  var guerrero: Guerrero = null
  var ninja: Ninja = null
  var magonegro: MagoNegro = null
  var magoblanco: MagoBlanco = null
  var weaponPal: Sword = null
  var magicweaponWM: Wand = null
  var weaponGue : Sword = null
  var weaponNin : Weapon = null
  var magicWeaponBM: Wand = null

  test("Active Weapon Test"){
    var foundPal: Int = 0
    try{
      weaponPal = new Sword("",70,80)
      paladin = new Paladin("Diego", 100, 90, 120,ArrayBuffer(),Some(weaponPal))
    }
    catch{
      case e: InvalidWeaponException => foundPal += 1
    }
    assertEquals(foundPal,1,"The active weapon exception was not found")
    var foundGue: Int = 0
    try{
      weaponGue = new Sword("",70,80)
      guerrero = new Guerrero("Diego", 100, 90, 120,ArrayBuffer(),Some(weaponGue))
    }
    catch{
      case e: InvalidWeaponException => foundGue += 1
    }
    assertEquals(foundGue,1,"The active weapon exception was not found")

    var foundNin: Int = 0
    try{
      weaponNin = new Sword("",70,80)
      ninja = new Ninja("Diego", 100, 90, 80,ArrayBuffer(),Some(weaponNin))
    }
    catch{
      case e: InvalidWeaponException => foundNin += 1
    }
    assertEquals(foundGue,1,"The active weapon exception was not found")

    var foundWM: Int = 0
    try{
      magicweaponWM = new Wand("",70,80,90)
      magoblanco = new MagoBlanco("Diego", 100, 90, 120,70,ArrayBuffer(),Some(magicweaponWM))
    }
    catch{
      case e: InvalidWeaponException => foundWM += 1
    }
    assertEquals(foundWM,1,"The active weapon exception was not found")

    var foundBM: Int = 0
    try{
      magicWeaponBM = new Wand("",70,80,90)
      magonegro = new MagoNegro("Diego", 100, 90, 120,70,ArrayBuffer(),Some(magicWeaponBM))
    }
    catch{
      case e: InvalidWeaponException => foundBM += 1
    }
    assertEquals(foundBM,1,"The active weapon exception was not found")
  }
}
