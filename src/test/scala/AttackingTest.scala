import characters.{Warrior, WhiteMage, BlackMage, Ninja, Paladin}
import enemy.Enemy
import staff.Staff
import sword.Sword
import weapons.{MagicWeapon, Weapon}

/**Testing for when either enemies or characters/mages attack
 *
 */

class AttackingTest extends munit.FunSuite{
  var weapon: Sword = null
  var biggerWeapon:Sword = null
  var magicweapon: Staff = null
  var biggerMagicWeapon:Staff = null
  var paladin: Paladin = null
  var guerrero: Warrior = null
  var ninja: Ninja = null
  var magonegro: BlackMage = null
  var magoblanco: WhiteMage = null
  var enemigo1: Enemy = null
  var enemigo2: Enemy = null
  var enemigo3: Enemy = null

  override def beforeEach(context: BeforeEach): Unit = {
    paladin = new Paladin("Diego", 100, 90, 120)
    guerrero = new Warrior("Lucas", 80, 100, 100)
    ninja = new Ninja("Santiago", 60, 20, 60)
    magonegro = new BlackMage("Balbontin", 60, 50, 80, 100)
    magoblanco = new WhiteMage("Duarte", 70, 60, 80, 90)
    enemigo1 = new Enemy("Goblin",40,30,20,30)
    enemigo2 = new Enemy("Golem",120,50,80,45)
    enemigo3 = new Enemy("Boss",70,100,90,100)
    weapon = new Sword("Excalibur",60,70)
    magicweapon = new Staff("Palito",20,40,80)
    biggerWeapon = new Sword("Big Sword",80,120)
    biggerMagicWeapon = new Staff("Boom",90,20,40)
  }
  test("attacking and taking damage"){
    assertEquals(paladin.getHp(),100,"Paladin does not start with full health")
    assertEquals(enemigo1.getHp(),40,"First Enemies does not start with full health")

    paladin.receiveWeapon(weapon)
    paladin.attack(enemigo1)
    assertEquals(enemigo1.getHp(),40,"As paladin has no weapon, enemigo1 should not take damage")
    paladin.changeWeapon(0)
    paladin.attack(enemigo1)
    var expected_damage_taken: Int = 30
    assertEquals(paladin.getHp(),100,"Paladin took damage despite being the one who attacks")
    assertEquals(enemigo1.getHp(),40-30,"First Enemy did not take the correct amount of damage")

    paladin.attack(enemigo1)
    assertEquals(enemigo1.getHp(),0,"Enemy should have been defeated and left with 0 hp")

    assertEquals(guerrero.getHp(),80,"Guerrero does not start with full health")
    enemigo2.attack(guerrero)
    assertEquals(enemigo2.getHp(),120,"Second Enemy took damage despite being the one who attacks")
    expected_damage_taken = 0
    assertEquals(guerrero.getHp(),80,"Guerrero should not have taken damage, as his defense is too high")

    assertEquals(ninja.getHp(),60," Ninja does not start with full health")
    enemigo2.attack(ninja)
    expected_damage_taken = 25
    assertEquals(ninja.getHp(),60-expected_damage_taken,"Ninja did not take correct amount of damage")

    enemigo2.attack(ninja)
    assertEquals(ninja.getHp(),60-2*expected_damage_taken,"Ninja did not take correct amount of damage")
    enemigo2.attack(ninja)
    assertEquals(ninja.getHp(),0,"Ninja should be defeated, thus having 0 HP")

    enemigo2.attack(ninja)
    assertEquals(ninja.getHp(),0,"Ninja should be already defeated, thus having 0 HP")


    magoblanco.receiveWeapon(biggerMagicWeapon)
    magoblanco.changeWeapon(0)
    magoblanco.attack(enemigo2)
    expected_damage_taken = 40
    assertEquals(enemigo2.getHp(),120-expected_damage_taken,"Second enemy does not take the expected damage")


    ninja.receiveWeapon(biggerWeapon)
    ninja.changeWeapon(0)
    ninja.attack(enemigo2)
    expected_damage_taken = 30
    assertEquals(enemigo2.getHp(),80-expected_damage_taken,"Second enemy does not take the correct amount of damage")
    magoblanco.attack(enemigo2)
    magoblanco.attack(enemigo2)
    assertEquals(enemigo2.getHp(),0,"Second enemy should be defeated")
    magonegro.attack(enemigo2)
    assertEquals(enemigo2.getHp(),0,"Second enemy should already be defeated")

    enemigo3.attack(magonegro)
    assertEquals(magonegro.getHp(),10,"Black Mage did not take the correct amount of damage")
    enemigo1.attack(magonegro)
    assertEquals(magonegro.getHp(),10,"Black Mage should not take damage, as his defense is higher")
    enemigo3.attack(magonegro)
    assertEquals(magonegro.getHp(),0,"Black Mage should have been defeated")


  }
}
