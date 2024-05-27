import characters.{MagoBlanco, MagoNegro}
import enemigo.Enemigo
import spells.{Curacion, Fuego, Paralisis, Trueno, Veneno}
import staff.Staff
import wand.Wand

/** Testing for casting spells */

class SpellCastingTest extends munit.FunSuite {
  var magoblanco: MagoBlanco = null
  var magonegro: MagoNegro = null
  var wand: Wand = null
  var staff: Staff = null
  var enemy: Enemigo = null

  override def beforeEach(context: BeforeEach): Unit = {
    magoblanco = new MagoBlanco("Sanma",100,100,100,100)
    magonegro = new MagoNegro("Yamal",100,60,100,100)
    wand = new Wand("Stick",50,70,50)
    staff = new Staff("Bigger Stick",70,80,80)
    magoblanco.receiveWeapon(wand)
    magoblanco.changeWeapon(0)
    magonegro.receiveWeapon(staff)
    magonegro.changeWeapon(0)
    enemy = new Enemigo("Golem",300,150,150,100)

  }
  test("Casting Light"){
    val veneno: Veneno = new Veneno()
    magoblanco.throwSpell(veneno,enemy)
    assertEquals(magoblanco.getMana(),70,"Not corrent amount of mana used")

    val paralisis: Paralisis = new Paralisis()
    magoblanco.throwSpell(paralisis,enemy)
    assertEquals(magoblanco.getMana(),45,"Not correct amount of mana used")

    enemy.attack(magonegro)
    assertEquals(magonegro.getHp(),60,"Magonegro should take 40 damage")

    val curacion: Curacion = new Curacion()
    magoblanco.throwSpell(curacion,magonegro)
    assertEquals(magonegro.getHp(),90,"Magonegro shouldve been healed by 33")

    magoblanco.throwSpell(curacion,magonegro)
    assertEquals(magonegro.getHp(),100,"Magonegro should now be max Health")
  }

  test("Casting Darkness"){
    val trueno: Trueno = new Trueno()
    magonegro.throwSpell(trueno,enemy)
    assertEquals(magonegro.getMana(),80,"Magonegro should use 20 mana")
    assertEquals(enemy.getHp(),220,"Enemy should take 80 damage")

    val fuego: Fuego = new Fuego()
    magonegro.throwSpell(fuego,enemy)
    assertEquals(magonegro.getMana(),65,"Magonegro should use 15 mana")
    assertEquals(enemy.getHp(),140,"Enemy should take 80 damage again")
  }

}
