/** A class that tests the functionality of the playable characters
 *
 * The test is defined by each of the possible characters you may use,
 * Paladin, Guerrero, Ninja, MagoNegro and MagoBlanco
 *
 */



class PersonajeTest extends munit.FunSuite {
    var paladin: Paladin = null
    var guerrero: Guerrero = null
    var ninja: Ninja = null
    var magonegro: MagoNegro = null
    var magoblanco: MagoBlanco = null

    override def beforeEach(context: BeforeEach): Unit = {
        paladin = new Paladin("Diego",100,90,120)
        guerrero = new Guerrero("Lucas",80,100,100)
        ninja = new Ninja("Santiago",60,75,60)
        magonegro = new MagoNegro("Balbontin",60,50,80,100)
        magoblanco = new MagoBlanco("Duarte",70,60,80,90)
    }
}
