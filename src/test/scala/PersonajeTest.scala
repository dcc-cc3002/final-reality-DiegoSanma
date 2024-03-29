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

    test("character stat comparison"){
        val paladin_stat_guerrero = new Guerrero("Diego",100,90,120)
        val mblanco_stat_magonegro = new MagoNegro("Duarte",70,60,80,90)
        val guerrero_stat_ninja = new Ninja("Lucas",80,100,100)

        assert(!paladin_stat_guerrero.equals(paladin))
        assertEquals(paladin_stat_guerrero.name(),paladin.name())
        assertEquals(paladin_stat_guerrero.hp(),paladin.hp())
        assertEquals(paladin_stat_guerrero.defense(),paladin.defense())
        assertEquals(paladin_stat_guerrero.weight(),paladin.weight())

        assert(!mblanco_stat_magonegro.equals(magoblanco))
        assertEquals(mblanco_stat_magonegro.mana(),magonegro.mana())

        assertEquals(ninja.hp(),magonegro.hp())
        assertEquals(ninja.weight(),magoblanco.defense())
        assertEquals(paladin.hp(),guerrero.weight())
        assert(!ninja.hp().equals(paladin.hp()))
        assert(!magoblanco.mana(),equals(magonegro.mana()))
        assert(!paladin.defense().equals(magonegro.hp()))
    }

    test("stat changing"){
        val first_paladin = new Paladin("Diego",90,100,130)
        val first_magonegro = new MagoNegro("Santiago",50,60,85,110)

        assert(!first_paladin.hp().equals(paladin.hp()))
        assert(!first_paladin.defense().equals(paladin.defense()))
        assert(!first_paladin.weight().equals(paladin.weight()))

        first_paladin.hp() = 100
        first_paladin.defense() = 90
        first_paladin.weight() = 120
        assertEquals(first_paladin,paladin)

        assert(!first_magonegro.hp().equals(magonegro.hp()))
        assert(!first_magonegro.defense().equals(magonegro.defense()))
        assert(!first_magonegro.weight().equals(magonegro.weight()))

        first_magonegro.hp() = 60
        first_magonegro.defense() = 50
        first_magonegro.weight() = 80
        first_magonegro.mana() = 100
        assertEquals(first_magonegro,magonegro)
    }
}