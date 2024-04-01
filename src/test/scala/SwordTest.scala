/**A class that test the funcionality of the Sword class
 *
 * At the moment, this test does not test when a weapon is equipped to a
 * character who can´t weild it, as that is for the next homework
 */


class SwordTest extends munit.FunSuite {
  var sword1: Sword = null
  var sword2: Sword = null
  var paladin: Paladin = null
  var guerrero: Guerrero = null
  var ninja: Ninja = null

  override def beforeEach(context: BeforeEach): Unit = {
    sword1 = new Sword("",40,50,null)
    paladin = new Paladin("Diego",100,90,120)
    guerrero = new Guerrero("Lucas",80,100,100)
    ninja = new Ninja("Santiago",60,70,60)
  }

  test("sword stats"){
    assertEquals(40,sword1.atkpoints,"the attack points weren´t assigned correctly")
    assertEquals(50,sword1.weight,"the weight wasn´t assigned correctly")

  }
}
