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

}
