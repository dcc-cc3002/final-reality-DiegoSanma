package exceptions

import characters.{MagoBlanco, Paladin}
import weapons.{MagicWeapon, Weapon}

class OwnerTest extends munit.FunSuite {
  var paladin: Paladin = null
  var weaponPal: Weapon = null
  var magoblanco: MagoBlanco = null
  var magicweaponWM: MagicWeapon = null

  test("Owner Exceptions"){
    var weaponFound: Int = 0
    try{
      paladin = new Paladin("Diego",100,100,100)
      weaponPal = new Weapon("",80,70,Some(paladin))
    }
    catch{
      case e: InvalidOwnerException => weaponFound += 1
    }
    assertEquals(weaponFound,1,"Owner exception was not found")

    var magicweaponFound: Int = 0
    try{
      magoblanco = new MagoBlanco("Diego",100,100,100,150)
      magicweaponWM = new MagicWeapon("",80,70,70,Some(magoblanco))
    }
    catch{
      case e: InvalidOwnerException => magicweaponFound += 1
    }
    assertEquals(magicweaponFound,1,"Owner exception was not found")
  }
}
