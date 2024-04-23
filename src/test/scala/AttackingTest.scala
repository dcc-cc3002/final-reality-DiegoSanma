import characters.{Guerrero, MagoBlanco, MagoNegro, Ninja, Paladin}
import enemigo.Enemigo
import weapons.{MagicWeapon, Weapon}

/**Testing for when either enemies or characters/mages attack
 *
 */

class AttackingTest extends munit.FunSuite{
  var weapon: Weapon = null
  var biggerWeapon:Weapon = null
  var magicweapon: MagicWeapon = null
  var biggerMagicWeapon:MagicWeapon = null
  var paladin: Paladin = null
  var guerrero: Guerrero = null
  var ninja: Ninja = null
  var magonegro: MagoNegro = null
  var magoblanco: MagoBlanco = null
  var enemigo1: Enemigo = null
  var enemigo2: Enemigo = null

  override def beforeEach(context: BeforeEach): Unit = {
    paladin = new Paladin("Diego", 100, 90, 120)
    guerrero = new Guerrero("Lucas", 80, 100, 100)
    ninja = new Ninja("Santiago", 60, 70, 60)
    magonegro = new MagoNegro("Balbontin", 60, 50, 80, 100)
    magoblanco = new MagoBlanco("Duarte", 70, 60, 80, 90)
    enemigo1 = new Enemigo("Goblin",40,30,20,30)
    enemigo2 = new Enemigo("Golem",120,100,80,50)
    weapon = new Weapon("Excalibur",60,70,Some(paladin))
    magicweapon = new MagicWeapon("Palito",20,40,80,Some(magonegro))
    biggerWeapon = new Weapon("Big Sword",80,120)
    biggerMagicWeapon = new MagicWeapon("Boom",40,20,90)
  }
  test("attacking and taking damage"){
    assertEquals(paladin.getHp(),100,"Paladin does not start with full health")
    assertEquals(enemigo1.getHp(),40,"First Enemies does not start with full health")
    paladin.attack(enemigo1)
    var expected_damage_taken: Int = 30
    assertEquals(paladin.getHp(),100,"Paladin took damage despite being the one who attacks")
    assertEquals(enemigo1.getHp(),40-30,"First Enemy did not take the correct amount of damage")
    paladin.attack(enemigo1)
    assertEquals(enemigo1.getHp(),0,"Enemy should have been defeated and left with 0 hp")
  }
}
