import attributes.Attributes
import characters.{Guerrero, MagoBlanco, MagoNegro, Ninja, Paladin}
import enemigo.Enemigo
import turnos.ProgramadorDeTurnos
import weapons.{AMagicWeapon, AWeapon, MagicWeapon, Weapon}

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
  var weapon: AWeapon = null
  var magicweapon: AMagicWeapon = null
  var notnamew: AWeapon = null
  var notnamedw: AMagicWeapon = null
  var turnos: ProgramadorDeTurnos = null
  var jugadores : ArrayBuffer[Attributes] = null
  var enemigos: ArrayBuffer[Enemigo] = null

  override def beforeEach(context: BeforeEach): Unit = {
    paladin = new Paladin("Diego", 100, 90, 120)
    guerrero = new Guerrero("Lucas", 80, 100, 100)
    ninja = new Ninja("Santiago", 60, 70, 60)
    magonegro = new MagoNegro("Balbontin", 60, 50, 80, 100)
    magoblanco = new MagoBlanco("Duarte", 70, 60, 80, 90)
    weapon = new Weapon("Excalibur",60,70)
    magicweapon = new MagicWeapon("Palito",20,40,80)
    notnamew = new Weapon("",80,90)
    notnamedw = new MagicWeapon("",30,40,50)
    enemigo1 = new Enemigo("Goblin",40,30,30,20)
    enemigo2 = new Enemigo("Golem",120,60,110,80)
    jugadores = ArrayBuffer.empty
    enemigos = ArrayBuffer.empty
    turnos = new ProgramadorDeTurnos(jugadores,enemigos)
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
    assert(turnos.players.isEmpty,"Incorrect players array")
    assert(turnos.enemies.isEmpty,"Incorrect enemy array")
  }

  test("adding and removing characters"){
    turnos.agregar(ninja)
    assertEquals(turnos.players.isEmpty,false,"Player array still is empty")
    assert(turnos.players(0).isInstanceOf[Ninja],"Player added to the array is not a Ninja")
    assertEquals(turnos.players(0).asInstanceOf[Ninja],ninja,"The first player is not the expected ninja")
    turnos.agregar(magonegro)
    assertEquals(turnos.players(0).asInstanceOf[Ninja],ninja,"The first player is not the expected ninja")
    assert(turnos.players(1).isInstanceOf[MagoNegro],"Player added to the array is not a Mago Negro")
    assertEquals(turnos.players(1).asInstanceOf[MagoNegro],magonegro,"The second player is not the expected black mage")
    turnos.agregar(enemigo1)
    assertEquals(turnos.enemies.isEmpty,false,"Enemy array still is empty")
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

  test("registro y suma de k"){
    assertEquals(0,turnos.registro.length,"Registro was not initialized correctly")
    turnos.agregar(ninja)
    assertEquals(1,turnos.registro.length,"Registro nos as long as wanted")
    turnos.agregar(guerrero)
    turnos.agregar(magoblanco)
    turnos.agregar(enemigo1)
    turnos.agregar(enemigo2)
    assertEquals(turnos.registro.length,5,"Registro is not as long as the amount of characters in combat")
    for(i<-0 until turnos.registro.length-1) {
      assertEquals(0, turnos.registro(i), "The i action bar was not initialized correctly")
    }
    turnos.continuar(10)
    for(i<-0 until turnos.registro.length-1){
      assertEquals(10,turnos.registro(i),"The i action bar was not added k correctly")
    }
    turnos.continuar(120)
    for(i<-0 until turnos.registro.length){
      assertEquals(130,turnos.registro(i),"The i action bar was not added k correctly")
    }

  }

  test("revision de turnos"){
    turnos.agregar(ninja)
    turnos.agregar(paladin)
    turnos.agregar(magonegro)
    turnos.agregar(enemigo1)
    turnos.agregar(enemigo2)

    var barra_ninja: Double = 60
    var barra_paladin: Double = 155
    var barra_magonegro: Double = 100
    var barra_en1: Double = 30
    var barra_en2: Double = 110

    assert(turnos.turnos.isEmpty)
    turnos.continuar(25)
    turnos.revisionTurno()
    assert(turnos.turnos.isEmpty)

    turnos.continuar(25)
    turnos.revisionTurno()
    assert(turnos.turnos.nonEmpty)
    assertEquals(turnos.turnos(0).asInstanceOf[Enemigo],enemigo1)
    assertEquals(turnos.registro(3),0)

    turnos.continuar(30)
    turnos.revisionTurno()
    assert(turnos.turnos(0).isInstanceOf[Enemigo])
    assertEquals(turnos.turnos(0).asInstanceOf[Enemigo],enemigo1)
    assert(turnos.turnos(1).isInstanceOf[Ninja])
    assertEquals(turnos.turnos(1).asInstanceOf[Ninja],ninja)
    assertEquals(turnos.registro(0),0)
    assertEquals(turnos.turnos(2).asInstanceOf[Enemigo],enemigo1)
    assertEquals(turnos.registro(3),0)

    turnos.continuar(50)
    turnos.revisionTurno()
    assertEquals(turnos.turnos(0).asInstanceOf[Enemigo],enemigo1)
    assertEquals(turnos.turnos(1).asInstanceOf[Ninja],ninja)
    assertEquals(turnos.turnos(2).asInstanceOf[Enemigo],enemigo1)
    assertEquals(turnos.turnos(3).asInstanceOf[MagoNegro],magonegro)
    assertEquals(turnos.registro(2),0)
    assertEquals(turnos.turnos(4).asInstanceOf[Enemigo],enemigo2)
    assertEquals(turnos.turnos(5).asInstanceOf[Enemigo],enemigo1)
  }

  test("next in line"){
    turnos.agregar(ninja)
    turnos.agregar(paladin)
    turnos.agregar(magonegro)
    turnos.agregar(enemigo1)
    turnos.agregar(enemigo2)
    turnos.continuar(120)
    turnos.revisionTurno()
    assertEquals(turnos.turnos(0),enemigo1,"Turnos array doesnt have en1 first")
    assertEquals(turnos.turnos(1),ninja,"Turnos array doesnt have ninja second")
    assertEquals(turnos.turnos(2),magonegro,"Turnos array doesnt have black mage third")
    assertEquals(turnos.turnos(3),enemigo2,"Turnos array doesnt have  en2 fourth")

    val first = turnos.next_turn()
    assert(first.isDefined)
    assert(first.get.isInstanceOf[Enemigo])
    assertEquals(first.get,enemigo1)

    val first2 = turnos.next_turn()
    assert(first2.isDefined)
    assert(first2.get.isInstanceOf[Attributes])
    assertEquals(first2.get,ninja)

    val first3 = turnos.next_turn()
    assert(first3.isDefined)
    assert(first3.get.isInstanceOf[Attributes])
    assertEquals(first3.get,magonegro)

    val first4 = turnos.next_turn()
    assert(first4.isDefined)
    assert(first4.get.isInstanceOf[Enemigo])
    assertEquals(first4.get,enemigo2)
    assert(turnos.turnos.isEmpty)
  }

}
