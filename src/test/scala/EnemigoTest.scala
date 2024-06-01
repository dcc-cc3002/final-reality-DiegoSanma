import enemy.Enemy

class EnemigoTest extends munit.FunSuite {
  var enemigo1: Enemy = null
  var enemigo2: Enemy = null

  override def beforeEach(context: BeforeEach): Unit = {
    enemigo1 = new Enemy("Goblin",40,30,20,30)
    enemigo2 = new Enemy("Golem",120,60,80,110)
  }
  test("enemigo creation"){
    assertEquals(enemigo1.getName(),"Goblin","The name was not given correctly")
    assertEquals(enemigo1.getHp(),40,"The hit points was not given correctly")
    assertEquals(enemigo1.getDefense(),30,"The attack was not given correctly")
    assertEquals(enemigo1.getWeight(),20,"The defense was not given correctly")
    assertEquals(enemigo1.getAttack(),30,"The weight was not given correctly")
  }
}
