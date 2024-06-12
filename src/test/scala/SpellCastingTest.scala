import characters.{BlackMage, WhiteMage}
import enemy.Enemy
import spells.{FireSpell, HealingSpell, ParalisisSpell, PoisonSpell, ThunderSpell}
import staff.Staff
import status.{Paralyzed, Poisoned}
import wand.Wand

/** Testing for casting spells */

class SpellCastingTest extends munit.FunSuite {
  var magoblanco: WhiteMage = null
  var magonegro: BlackMage = null
  var wand: Wand = null
  var staff: Staff = null
  var enemy: Enemy = null

  override def beforeEach(context: BeforeEach): Unit = {
    magoblanco = new WhiteMage("Sanma",100,100,100,100)
    magonegro = new BlackMage("Yamal",100,60,100,100)
    wand = new Wand("Stick",50,70,50)
    staff = new Staff("Bigger Stick",70,80,80)
    magoblanco.receiveWeapon(wand)
    magoblanco.changeWeapon(0)
    magonegro.receiveWeapon(staff)
    magonegro.changeWeapon(0)
    enemy = new Enemy("Golem",300,150,150,100)

  }
  test("Casting Light"){
    val veneno: PoisonSpell = new PoisonSpell()
    magoblanco.throwSpell(veneno,enemy)
    assertEquals(magoblanco.getMana(),70,"Not corrent amount of mana used")
    assert(enemy.getStatus().get.isInstanceOf[Poisoned],"Enemy was not poisoned")

    enemy.removeStatus()

    val paralisis: ParalisisSpell = new ParalisisSpell()
    magoblanco.throwSpell(paralisis,enemy)
    assertEquals(magoblanco.getMana(),45,"Not correct amount of mana used")
    assert(enemy.getStatus().get.isInstanceOf[Paralyzed],"Enemy was not paralyzed")

    enemy.attack(magonegro)
    assertEquals(magonegro.getHp(),60,"Magonegro should take 40 damage")

    val curacion: HealingSpell = new HealingSpell()
    magoblanco.throwSpell(curacion,magonegro)
    assertEquals(magonegro.getHp(),90,"Magonegro shouldve been healed by 33")

    magoblanco.throwSpell(curacion,magonegro)
    assertEquals(magonegro.getHp(),100,"Magonegro should now be max Health")
  }

  test("Casting Darkness"){
    val trueno: ThunderSpell = new ThunderSpell()
    magonegro.throwSpell(trueno,enemy)
    assertEquals(magonegro.getMana(),80,"Magonegro should use 20 mana")
    assertEquals(enemy.getHp(),220,"Enemy should take 80 damage")

    enemy.removeStatus()

    val fuego: FireSpell = new FireSpell()
    magonegro.throwSpell(fuego,enemy)
    assertEquals(magonegro.getMana(),65,"Magonegro should use 15 mana")
    assertEquals(enemy.getHp(),140,"Enemy should take 80 damage again")
  }

}
