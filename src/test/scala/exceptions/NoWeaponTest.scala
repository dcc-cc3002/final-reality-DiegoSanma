package exceptions

import characters.{Paladin, WhiteMage}
import enemy.Enemy
import exceptions.weaponexceptions.NoWeaponException
import spells.PoisonSpell

class NoWeaponTest extends munit.FunSuite {
  var paladin: Paladin = null
  var magoblanco: WhiteMage = null
  var veneno: PoisonSpell = null
  var enemigo: Enemy = null
  var found: Int = 0

  override def beforeEach(context: BeforeEach): Unit = {
    paladin = new Paladin("Diego",90,90,90)
    magoblanco = new WhiteMage("Lucas",90,90,90,90)
    veneno = new PoisonSpell()
    enemigo = new Enemy("Benja",70,70,70,70)
  }

  test("Attacking with no weapon"){
    try{
      paladin.attack(enemigo)
    }
    catch {
      case e: NoWeaponException => found += 1
    }
    assertEquals(found,1,"Exception not thrown")
    assertEquals(enemigo.getHp(),70,"Enemy should not have been attacked")

    try{
      magoblanco.throwSpell(veneno,enemigo)
    }
    catch {
      case e: NoWeaponException => found += 1
    }
    assertEquals(found,2,"Exception not thrown")
    assertEquals(magoblanco.getMana(),90,"White Mage should not have used mana")
  }
}
