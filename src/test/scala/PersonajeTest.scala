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
        ninja = new Ninja("Santiago",60,70,60)
        magonegro = new MagoNegro("Balbontin",60,50,80,100)
        magoblanco = new MagoBlanco("Duarte",70,60,80,90)
    }

    test("equal character"){
        val expected_paladin = new Paladin("Diego",100,90,120)
        val expected_guerrero = new Guerrero("Lucas",80,100,100)
        val expected_ninja = new Ninja("Santiago",60,70,60)
        val expected_magonegro = new MagoNegro("Balbontin",60,50,80,100)
        val expected_magoblanco = new MagoBlanco("Duarte",70,60,80,90)

        assertEquals(expected_guerrero,guerrero,"Not the same class and stats")
        assertEquals(expected_paladin,paladin,"Not the same class and stats")
        assertEquals(expected_ninja,ninja,"Not the same class and stats")
        assertEquals(expected_magonegro,magonegro,"Not the same class and stats")
        assertEquals(expected_magoblanco,magoblanco,"Not the same class and stats")
    }

    test("simple character comparison"){
        val statg_paladin = new Paladin("Lucas",80,100,100)
        val statmn_magoblanco = new MagoBlanco("Balbontin",60,50,80,100)

        assert(!magonegro.equals(magoblanco))
        assert(!paladin.equals(guerrero))
        assert(!ninja.equals(paladin))
        assert(!magoblanco.equals(ninja))

        assert(!guerrero.equals(statg_paladin))
        assert(!magonegro.equals(statmn_magoblanco))
    }
}
