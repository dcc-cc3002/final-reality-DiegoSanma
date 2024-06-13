import enemy.Enemy
import status.{Burnt, Paralyzed, Poisoned}
import wand.Wand

class StatusTest extends munit.FunSuite {
  var burnt: Burnt = null
  var poisoned: Poisoned = null
  var paralyzed: Paralyzed = null
  var enemy1: Enemy = null
  var enemy2: Enemy = null
  var enemy3: Enemy = null
  var magicWeapon: Wand = null

  override def beforeEach(context: BeforeEach): Unit = {
    magicWeapon = new Wand("Stick",70,70,30)
    burnt = new Burnt(magicWeapon)
    poisoned = new Poisoned(magicWeapon)
    paralyzed = new Paralyzed
    enemy1 = new Enemy("Goblin",90,70,70,70)
    enemy2 = new Enemy("Goblin",90,70,70,70)
    enemy3 = new Enemy("Goblin",90,70,70,70)
  }

  test("Correct amount of turns"){
    assertEquals(burnt.getTurnsLeft(),3,"Should have 3 turns left")
    assertEquals(paralyzed.getTurnsLeft(),1,"Should have 1 turn left")
    assertEquals(poisoned.getTurnsLeft(),4,"Should have 4 turns left")
  }

  test("Setting and Applying Effects"){
    assert(enemy1.getStatus().isEmpty,"Enemy1 should not have a status condition")
    enemy1.setStatus(burnt)
    assert(enemy1.getStatus().get.isInstanceOf[Burnt],"Enemy1 should be burnt")
    assertEquals(burnt.getEnemy().get.asInstanceOf[Enemy],enemy1,"Burnt should have enemy1 as burnt")
    enemy1.getStatus().get.doEffect()
    assertEquals(enemy1.getHp(),75,"Not correct amount of damage taken")
    assertEquals(burnt.getTurnsLeft(),2,"Should have 2 turns left")

    enemy2.setStatus(paralyzed)
    assert(enemy2.getStatus().get.isInstanceOf[Paralyzed],"Enemy2 should be paralyzed")
    enemy2.getStatus().get.doEffect()
    assertEquals(paralyzed.getTurnsLeft(),0,"Should have no turns left")
    assert(enemy2.getStatus().isEmpty,"As no more turns are left, enemy2 should have no status condition")

    enemy3.setStatus(poisoned)
    assert(enemy3.getStatus().get.isInstanceOf[Poisoned],"Enemy3 should be poisoned")
    assertEquals(poisoned.getEnemy().get.asInstanceOf[Enemy],enemy3,"Poisoned should have enemy3 as poisoned")
    enemy3.getStatus().get.doEffect()
    assertEquals(enemy3.getHp(),80,"Not correct amount of damage taken")
    assertEquals(poisoned.getTurnsLeft(),3,"Should have 3 turns left")
  }

}
