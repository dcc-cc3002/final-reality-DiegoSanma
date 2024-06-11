package exceptions

import characters.Paladin
import enemy.Enemy
import exceptions.damage.AlreadyHasStatusException
import status.{Burnt, Poisoned}
import wand.Wand

class AlreadyStatusTest extends munit.FunSuite {
  var enemy: Enemy = null
  var magicWeapon: Wand = null
  var burnt: Burnt = null
  var poisoned: Poisoned = null

  override def beforeEach(context: BeforeEach): Unit = {
    enemy = new Enemy("Goblin",30,30,30,30)
    magicWeapon = new Wand("Stick",70,70,70)
    burnt = new Burnt(magicWeapon)
    poisoned = new Poisoned(magicWeapon)
  }

  test("Overriding status condition"){
    var found: Int = 0
    enemy.setStatus(Some(burnt))
    try{
      enemy.setStatus(Some(poisoned))
    }
    catch {
      case e: AlreadyHasStatusException => found += 1
    }
    assertEquals(found,1,"Exception not thrown")
    assert(enemy.getStatus().get.isInstanceOf[Burnt],"Statsu condition should still be burnt")
  }
}
