/**A class that test the funcionality of the Sword class
 *
 * At the moment, this test does not test when a weapon is equipped to a
 * character who can´t weild it, as that is for the next homework
 */


class SwordTest extends munit.FunSuite {
  var sword1: Sword = null
  var sword2: Sword = null
  var sword4: Sword = null
  var paladin: Paladin = null
  var guerrero: Guerrero = null
  var ninja: Ninja = null
  var magonegro: MagoNegro = null

  override def beforeEach(context: BeforeEach): Unit = {
    sword1 = new Sword("",40,50)
    sword2 = new Sword("The holy",50,70)
    sword4 = new Sword("The best",60,40)
    paladin = new Paladin("Diego",100,90,120)
    guerrero = new Guerrero("Lucas",80,100,100)
    ninja = new Ninja("Santiago",60,70,60)
    magonegro = new MagoNegro("Balbontin",60,50,80,100)
  }

  test("sword stats"){
    val sword3 = new Sword("",40,50)
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
    assertEquals(null,magonegro.weapon,"the mago negro has a weapon assigned")

    paladin.obtain(sword1)
    guerrero.obtain(sword2)
    magonegro.obtain(sword4)

    assertEquals(sword1,paladin.weapon,"the paladin did not obtain the weapon")
    assertNotEquals(sword2,paladin.weapon,"the paladin was given the wrong weapon")
    assertEquals(sword2,guerrero.weapon,"the guerrero did not obtain the weapon")
    assertNotEquals(sword1,guerrero.weapon,"the guerrero was given the wrong weapon")
    assertEquals(sword4,magonegro.weapon,"the mago negro did not obtain the weapon")
    assertNotEquals(sword1,magonegro.weapon,"the mago negro was given the wrong weapon")
  }

  test("naming"){
    sword1.rename("The Unholy")
    assertEquals(("The Unholy",sword1.name(),"the sword was not renamed correctly"))
    assertNotEquals(null,sword1.rename("The Holiest"),"the sword should not be renamed")
    assertNotEquals(null,sword4.rename("The Not Best"),"the sword should not be renamed")
  }
}
