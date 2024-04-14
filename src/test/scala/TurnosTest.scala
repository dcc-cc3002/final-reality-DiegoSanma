import attributes.Attributes
import characters.{Guerrero, MagoBlanco, MagoNegro, Ninja, Paladin}
import enemigo.Enemigo
import turnos.ProgramadorDeTurnos
import weapons.{MagicWeapon, Weapon}

import scala.collection.mutable.ArrayBuffer

/**A class that test the funcionality of the ProgramadorDeTurnos class and its methods
 *
 */

class TurnosTest extends munit.FunSuite{
  var paladin: Paladin = null
  var guerrero: Guerrero = null
  var ninja: Ninja = null
  var magonegro: MagoNegro = null
  var magoblanco: MagoBlanco = null
  var enemigo1: Enemigo = null
  var enemigo2: Enemigo = null
  var weapon: Weapon = null
  var magicweapon: MagicWeapon = null
  var notnamew: Weapon = null
  var notnamedw: MagicWeapon = null
  var turnos: ProgramadorDeTurnos = null
  var jugadores : ArrayBuffer[Attributes] = null
  var enemigos: ArrayBuffer[Enemigo] = null

  override def beforeEach(context: BeforeEach): Unit = {
    paladin = new Paladin("Diego", 100, 90, 120)
    guerrero = new Guerrero("Lucas", 80, 100, 100)
    ninja = new Ninja("Santiago", 60, 70, 60)
    magonegro = new MagoNegro("Balbontin", 60, 50, 80, Some(100))
    magoblanco = new MagoBlanco("Duarte", 70, 60, 80, Some(90))
    weapon = new Weapon("Excalibur",60,70,paladin)
    magicweapon = new MagicWeapon("Palito",20,40,Some(80),magonegro)
    notnamew = new Weapon("",80,90,guerrero)
    notnamedw = new MagicWeapon("",30,40,Some(50),magoblanco)
    enemigo1 = new Enemigo("Goblin",40,30,20,30)
    enemigo2 = new Enemigo("Golem",120,60,80,110)
    jugadores = ArrayBuffer.empty
    enemigos = ArrayBuffer.empty
    turnos = new ProgramadorDeTurnos(jugadores,enemigos)
  }

  test("turnos creation"){
    assert(turnos.players.isEmpty,"Incorrect players array")
    assert(turnos.enemies.isEmpty,"Incorrect enemy array")
  }

  test("adding and removing characters"){
    turnos.agregar(ninja)
    assertNotEquals(turnos.players.isEmpty,false,"Player array still is empty")
    assert(turnos.players(0).isInstanceOf[Ninja],"Player added to the array is not a Ninja")
    assertEquals(turnos.players(0).asInstanceOf[Ninja],ninja,"The first player is not the expected ninja")
    turnos.agregar(magonegro)
    assertEquals(turnos.players(0).asInstanceOf[Ninja],ninja,"The first player is not the expected ninja")
    assert(turnos.players(1).isInstanceOf[MagoNegro],"Player added to the array is not a Mago Negro")
    assertEquals(turnos.players(1).asInstanceOf[MagoNegro],magonegro,"The second player is not the expected black mage")
    turnos.agregar(enemigo1)
    assertNotEquals(turnos.enemies.isEmpty,false,"Enemy array still is empty")
    assert(turnos.enemies(0).isInstanceOf[Enemigo],"Enemy added to the array is not a Enemigo")
    assertEquals(turnos.enemies(0).asInstanceOf[Enemigo],enemigo1,"The first enemy is not the expected enemy")
    turnos.agregar(ninja)
    assertEquals(turnos.players(0).asInstanceOf[Ninja],ninja,"The first player is not the expected ninja")
    assertEquals(turnos.players(1).asInstanceOf[MagoNegro],magonegro,"The second player is not the expected black mage")
    assert(turnos.players.lift(2).isEmpty)
    turnos.agregar(enemigo1)
    assertEquals(turnos.enemies(0).asInstanceOf[Enemigo],enemigo1,"The first enemy is not the expected enemy")
    assert(turnos.enemies.lift(1).isEmpty)

    turnos.sacar(ninja)
    assert(!(turnos.players.contains(ninja)),"The ninja was not removed")
    assertEquals(turnos.players(0).asInstanceOf[MagoNegro],magonegro,"The mage wasnt moved to the front")
    turnos.sacar(guerrero)
    assert(!(turnos.players.contains(guerrero)),"The guerrero should not be added")
    assertEquals(turnos.players(0).asInstanceOf[MagoNegro],magonegro,"The mage was removed")
    turnos.agregar(enemigo2)
    assertEquals(turnos.enemies(1).asInstanceOf[Enemigo],enemigo2,"The second enemy is not the expected enemy")
    turnos.sacar(enemigo1)
    assert(!(turnos.enemies.contains(enemigo1)),"The enemigo1 was not removed")
    assertEquals(turnos.enemies(0).asInstanceOf[Enemigo],enemigo2,"The second enemy wasnt moved to the front")
    turnos.sacar(enemigo1)
    assert(!(turnos.enemies.contains(enemigo1)),"The enemigo1 should not be added")
    assertEquals(turnos.enemies(0).asInstanceOf[Enemigo],enemigo2,"The second enemy was removed")
  }

  test("cálculo barra de acción") {
    val barraInicial = turnos.Barra()
    assert(barraInicial.isEmpty)
    turnos.agregar(ninja)
    turnos.agregar(paladin)
    turnos.agregar(magoblanco)
    turnos.agregar(enemigo1)
    turnos.agregar(enemigo2)

    val barraAccion = turnos.Barra()
    var barra_ninja: Double = 60
    var barra_paladin: Double = 120
    barra_paladin = barra_paladin + 0.5*70
    var barra_magoblanco : Double = 80
    barra_magoblanco = barra_magoblanco + 0.5*40
    var barra_en1: Double = 30
    var barra_en2: Double = 110

    assertEquals(barraAccion(0)._1,ninja)
    assertEquals(barraAccion(1)._1,paladin)
    assertEquals(barraAccion(2)._1,magoblanco)
    assertEquals(barraAccion(3)._1,enemigo1)
    assertEquals(barraAccion(4)._1,enemigo2)
    assertEquals(barraAccion(0)._2,barra_ninja)
    assertEquals(barraAccion(1)._2,barra_paladin)
    assertEquals(barraAccion(2)._2,barra_magoblanco)
    assertEquals(barraAccion(3)._2,barra_en1)
    assertEquals(barraAccion(4)._2,barra_en2)







  }

}
