package exceptions

import characters.{Guerrero, MagoBlanco, MagoNegro, Ninja, Paladin}
import weapons.{MagicWeapon, Weapon}

import scala.collection.mutable.ArrayBuffer

class InventoryTest extends munit.FunSuite {
  var paladin: Paladin = null
  var guerrero: Guerrero = null
  var ninja: Ninja = null
  var magonegro: MagoNegro = null
  var magoblanco: MagoBlanco = null
  var weaponPal: Weapon = null
  var magicweaponWM: MagicWeapon = null
  var weaponGue : Weapon = null
  var weaponNin : Weapon = null
  var magicWeaponBM: MagicWeapon = null

  override def beforeEach(context: BeforeEach): Unit = {
    weaponPal = new Weapon("Excalibur",60,70)
    weaponGue = new Weapon("Excalibur",60,70)
    weaponNin = new Weapon("Excalibur",60,70)
    magicWeaponBM = new MagicWeapon("Palito",20,40,80)
    magicweaponWM = new MagicWeapon("Palito",20,40,80)

    paladin = new Paladin("Diego", 100, 90, 120, ArrayBuffer(weaponPal))
    guerrero = new Guerrero("Lucas", 80, 100, 100, ArrayBuffer(weaponGue))
    ninja = new Ninja("Santiago", 60, 70, 60, ArrayBuffer(weaponNin))
    magonegro = new MagoNegro("Balbontin", 60, 50, 80, 100, ArrayBuffer(magicWeaponBM))
    magoblanco = new MagoBlanco("Duarte", 70, 60, 80, 90, ArrayBuffer(magicweaponWM))
  }
  /**test("Inventory Exception"){
    interceptMessage[InvalidInventoryException](
      s"Invalid Inventory was found"
    )
  }*/

}
