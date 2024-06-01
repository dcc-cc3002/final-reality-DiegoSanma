import attributes.Attributes
import axe.Axe
import characters.{Warrior, WhiteMage, BlackMage, Ninja, Paladin}
import enemy.{Enemy, EnemyAttributes}
import exceptions.turns.{AlreadyInSchedulerException, NotInSchedulerException}
import staff.Staff
import sword.Sword
import turnscheduler.TurnScheduler
import wand.Wand
import weapons.{AMagicWeapon, AWeapon, MagicWeapon, Weapon}

import scala.collection.mutable.ArrayBuffer

/**A class that test the funcionality of the ProgramadorDeTurnos class and its methods
 *
 */

class TurnosTest extends munit.FunSuite{
  var paladin: Paladin = null
  var guerrero: Warrior = null
  var ninja: Ninja = null
  var magonegro: BlackMage = null
  var magoblanco: WhiteMage = null
  var enemigo1: Enemy = null
  var enemigo2: Enemy = null
  var weapon: Sword = null
  var magicweapon: Wand = null
  var notnamew: Axe = null
  var notnamedw: Staff = null
  var turnos: TurnScheduler = null
  var jugadores : ArrayBuffer[Attributes] = null
  var enemigos: ArrayBuffer[EnemyAttributes] = null

  override def beforeEach(context: BeforeEach): Unit = {
    paladin = new Paladin("Diego", 100, 90, 120)
    guerrero = new Warrior("Lucas", 80, 100, 100)
    ninja = new Ninja("Santiago", 60, 70, 60)
    magonegro = new BlackMage("Balbontin", 60, 50, 80, 100)
    magoblanco = new WhiteMage("Duarte", 70, 60, 80, 90)
    weapon = new Sword("Excalibur",60,70)
    magicweapon = new Wand("Palito",20,40,80)
    notnamew = new Axe("",80,90)
    notnamedw = new Staff("",30,40,50)
    enemigo1 = new Enemy("Goblin",40,30,30,20)
    enemigo2 = new Enemy("Golem",120,60,110,80)
    jugadores = ArrayBuffer.empty
    enemigos = ArrayBuffer.empty
    turnos = new TurnScheduler(jugadores,enemigos)
    paladin.receiveWeapon(weapon)
    paladin.changeWeapon(0)
    magonegro.receiveWeapon(magicweapon)
    magonegro.changeWeapon(0)
    guerrero.receiveWeapon(notnamew)
    guerrero.changeWeapon(0)
    magoblanco.receiveWeapon(notnamedw)
    magoblanco.changeWeapon(0)
  }

  test("turnos creation"){
    assert(turnos.getPlayers().isEmpty,"Incorrect players array")
    assert(turnos.getEnemies().isEmpty,"Incorrect enemy array")
  }

  test("adding and removing characters"){
    turnos.addTo(ninja)
    assertEquals(turnos.getPlayers().isEmpty,false,"Player array still is empty")
    assert(turnos.getPlayers()(0).isInstanceOf[Ninja],"Player added to the array is not a Ninja")
    assertEquals(turnos.getPlayers()(0).asInstanceOf[Ninja],ninja,"The first player is not the expected ninja")

    turnos.addTo(magonegro)
    assertEquals(turnos.getPlayers()(0).asInstanceOf[Ninja],ninja,"The first player is not the expected ninja")
    assert(turnos.getPlayers()(1).isInstanceOf[BlackMage],"Player added to the array is not a Mago Negro")
    assertEquals(turnos.getPlayers()(1).asInstanceOf[BlackMage],magonegro,"The second player is not the expected black mage")

    turnos.addTo(enemigo1)
    assertEquals(turnos.getEnemies().isEmpty,false,"Enemy array still is empty")
    assert(turnos.getEnemies()(0).isInstanceOf[Enemy],"Enemy added to the array is not a Enemigo")
    assertEquals(turnos.getEnemies()(0).asInstanceOf[Enemy],enemigo1,"The first enemy is not the expected enemy")

    var found: Int = 0
    try{
      turnos.addTo(ninja)
    }
    catch {
      case e: AlreadyInSchedulerException => found += 1
    }
    assertEquals(found,1,"Exception not thrown")
    assertEquals(turnos.getPlayers()(0).asInstanceOf[Ninja],ninja,"The first player is not the expected ninja")
    assertEquals(turnos.getPlayers()(1).asInstanceOf[BlackMage],magonegro,"The second player is not the expected black mage")
    assert(turnos.getPlayers().lift(2).isEmpty)

    turnos.removeEntity(ninja)
    assert(!(turnos.getPlayers().contains(ninja)),"The ninja was not removed")
    assertEquals(turnos.getPlayers()(0).asInstanceOf[BlackMage],magonegro,"The mage wasnt moved to the front")

    try{
      turnos.removeEntity(guerrero)
    }
    catch {
      case e: NotInSchedulerException => found += 1
    }
    assertEquals(found,2,"Exception not thrown")
    assert(!(turnos.getPlayers().contains(guerrero)),"The guerrero should not be added")
    assertEquals(turnos.getPlayers()(0).asInstanceOf[BlackMage],magonegro,"The mage was removed")

    turnos.addTo(enemigo2)
    assertEquals(turnos.getEnemies()(1).asInstanceOf[Enemy],enemigo2,"The second enemy is not the expected enemy")

    turnos.removeEntity(enemigo1)
    assert(!(turnos.getEnemies().contains(enemigo1)),"The enemigo1 was not removed")
    assertEquals(turnos.getEnemies()(0).asInstanceOf[Enemy],enemigo2,"The second enemy wasnt moved to the front")

    try{
      turnos.removeEntity(enemigo1)
    }
    catch {
      case e: NotInSchedulerException => found += 1
    }
    assertEquals(found,3,"Exception not thrown")
    assert(!(turnos.getEnemies().contains(enemigo1)),"The enemigo1 should not be added")
    assertEquals(turnos.getEnemies()(0).asInstanceOf[Enemy],enemigo2,"The second enemy was removed")

    try{
      turnos.addTo(enemigo2)
    }
    catch {
      case e: AlreadyInSchedulerException => found +=1
    }
    assertEquals(found,4,"Exception not thrown")
    assertEquals(turnos.getEnemies().length,1,"No enemies should´ve been added to the array")
  }

  test("cálculo barra de acción") {
    turnos.addTo(ninja)
    turnos.addTo(paladin)
    turnos.addTo(magoblanco)
    turnos.addTo(enemigo1)
    turnos.addTo(enemigo2)

    var barra_ninja: Int = 60
    var barra_paladin: Int = 120
    barra_paladin = (barra_paladin + 0.5*70).toInt
    var barra_magoblanco : Int = 80
    barra_magoblanco = (barra_magoblanco + 0.5*40).toInt
    var barra_en1: Int = 30
    var barra_en2: Int = 110

    assertEquals(barra_ninja,ninja.getMaxActionBar(),"MaxActionBar not correctly calcualted")
    assertEquals(barra_paladin,paladin.getMaxActionBar(),"MaxActionBar not correctly calcualted")
    assertEquals(barra_magoblanco,magoblanco.getMaxActionBar(),"MaxActionBar not correctly calcualted")
  }

  test("registro y suma de k"){
    turnos.addTo(ninja)
    turnos.addTo(guerrero)
    turnos.addTo(magoblanco)
    turnos.addTo(enemigo1)
    turnos.addTo(enemigo2)
    for(i <- turnos.getPlayers()) {
      assertEquals(i.getActionBar(),0,"All player action bars should be empty")
    }
    for(i <- turnos.getEnemies()) {
      assertEquals(i.getActionBar(),0,"All enemy action bars should be empty")
    }
    turnos.continue(10)
    for(i <- turnos.getPlayers()) {
      assertEquals(i.getActionBar(),10,"All player action bars should be 10")
    }
    for(i <- turnos.getEnemies()) {
      assertEquals(i.getActionBar(), 10, "All enemy action bars should be 10")
    }
    turnos.continue(120)
    for(i <- turnos.getPlayers()) {
      assertEquals(i.getActionBar(),130,"All player action bars should be 130")
    }
    for(i <- turnos.getEnemies()) {
      assertEquals(i.getActionBar(), 130, "All enemy action bars should be 130")
    }
  }

  test("revision de turnos"){
    turnos.addTo(ninja)
    turnos.addTo(paladin)
    turnos.addTo(magonegro)
    turnos.addTo(enemigo1)
    turnos.addTo(enemigo2)

    var barra_ninja: Double = 60
    var barra_paladin: Double = 155
    var barra_magonegro: Double = 100
    var barra_en1: Double = 30
    var barra_en2: Double = 110

    assert(turnos.getTurnsLine().isEmpty,"Nobody should have already completed their action bar meter")
    turnos.continue(25)
    turnos.checkTurn()
    assert(turnos.getTurnsLine().isEmpty,"Nobody should have already completed their action bar meter")

    turnos.continue(25)
    turnos.checkTurn()
    assert(turnos.getTurnsLine().nonEmpty,"Someone should have already surpassed their action abr")
    assertEquals(turnos.getTurnsLine()(0).asInstanceOf[Enemy],enemigo1,"Enemigo1 should be the first in the waiting line")

    turnos.continue(30)
    turnos.checkTurn()
    assertEquals(turnos.getTurnsLine()(0).asInstanceOf[Enemy],enemigo1,"Enemigo1 should still be first in line")
    assertEquals(turnos.getTurnsLine()(1).asInstanceOf[Ninja],ninja,"Ninja should be second in line")
    assertEquals(turnos.getTurnsLine()(2).asInstanceOf[Enemy],enemigo1,"Enemigo1 should be third in line")

    turnos.continue(50)
    turnos.checkTurn()
    assertEquals(turnos.getTurnsLine()(0).asInstanceOf[Enemy],enemigo1)
    assertEquals(turnos.getTurnsLine()(1).asInstanceOf[Ninja],ninja)
    assertEquals(turnos.getTurnsLine()(2).asInstanceOf[Enemy],enemigo1)
    assertEquals(turnos.getTurnsLine()(3).asInstanceOf[BlackMage],magonegro,"Now Magonegro should be fourth in line")
    assertEquals(turnos.getTurnsLine()(4).asInstanceOf[Enemy],enemigo2,"Enemigo2 should be fifth")
    assertEquals(turnos.getTurnsLine()(5).asInstanceOf[Enemy],enemigo1,"Enemigo1 should be sixth")
  }

  test("next in line"){
    turnos.addTo(ninja)
    turnos.addTo(paladin)
    turnos.addTo(magonegro)
    turnos.addTo(enemigo1)
    turnos.addTo(enemigo2)
    turnos.continue(120)
    turnos.checkTurn()
    assertEquals(turnos.getTurnsLine()(0),enemigo1,"Turnos array doesnt have en1 first")
    assertEquals(turnos.getTurnsLine()(1),ninja,"Turnos array doesnt have ninja second")
    assertEquals(turnos.getTurnsLine()(2),magonegro,"Turnos array doesnt have black mage third")
    assertEquals(turnos.getTurnsLine()(3),enemigo2,"Turnos array doesnt have  en2 fourth")

    val first = turnos.nextTurn()
    assert(first.isDefined)
    assert(first.get.isInstanceOf[Enemy])
    assertEquals(first.get,enemigo1)

    val first2 = turnos.nextTurn()
    assert(first2.isDefined)
    assert(first2.get.isInstanceOf[Attributes])
    assertEquals(first2.get,ninja)

    val first3 = turnos.nextTurn()
    assert(first3.isDefined)
    assert(first3.get.isInstanceOf[Attributes])
    assertEquals(first3.get,magonegro)

    val first4 = turnos.nextTurn()
    assert(first4.isDefined)
    assert(first4.get.isInstanceOf[Enemy])
    assertEquals(first4.get,enemigo2)
    assert(turnos.getTurnsLine().isEmpty)

    val first5 = turnos.nextTurn()
    assert(first5.isEmpty)
    assert(turnos.getTurnsLine().isEmpty)
  }

}
