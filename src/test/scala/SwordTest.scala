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
    sword2 = new Sword("The holy",50,70,null)
    paladin = new Paladin("Diego",100,90,120)
    guerrero = new Guerrero("Lucas",80,100,100)
    ninja = new Ninja("Santiago",60,70,60)
  }

  test("sword stats"){
    val sword3 = new Sword("",40,50,null)
    assertEquals(40,sword1.atkpoints,"the attack points weren´t assigned correctly")
    assertEquals(50,sword1.weight,"the weight wasn´t assigned correctly")
    assertEquals("",sword1.name,"the name wasnt assigned correctly")
    assertEquals(null,sword1.owner,"the owner wasn´t assigned correctly")

    assertEquals(sword3.atkpoints,sword1.atkpoints,"the attack points weren´t assigned correctly")
    assertEquals(sword3.weight,sword1.weight,"the weight wasn´t assigned correctly")
    assertEquals(sword3.name,sword1.name,"the name wasnt assigned correctly")
    assertEquals(sword3.owner,sword1.owner,"the owner wasn´t assigned correctly")
  }

  test("obtaining sword"){
    assertEquals(null,paladin.weapon,"the paladin has a weapon assigned")
    assertEquals(null,guerrero.weapon,"the guerrero has a weapon assigned")
    assertEquals(null,ninja.weapon,"the ninja has a weapon assigned")

  }
}
