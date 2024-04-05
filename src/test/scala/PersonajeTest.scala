/** A class that tests the functionality of the playable characters
 *
 * The test is defined by each of the possible characters you may use,
 * Paladin, Guerrero, Ninja, MagoNegro and MagoBlanco
 *
 */
import paladin.Paladin
import guerrero.Guerrero
import ninja.Ninja
import magonegro.MagoNegro
import magoblanco.MagoBlanco


class PersonajeTest extends munit.FunSuite {
    var paladin: Paladin = null
    var guerrero: Guerrero = null
    var ninja: Ninja = null
    var magonegro: MagoNegro = null
    var magoblanco: MagoBlanco = null

    override def beforeEach(context: BeforeEach): Unit = {
        paladin = new Paladin("Diego", 100, 90, 120)
        guerrero = new Guerrero("Lucas", 80, 100, 100)
        ninja = new Ninja("Santiago", 60, 70, 60)
        magonegro = new MagoNegro("Balbontin", 60, 50, 80, 100)
        magoblanco = new MagoBlanco("Duarte", 70, 60, 80, 90)
    }

    test("equal character") {
        val expected_paladin = new Paladin("Diego", 100, 90, 120)
        val expected_guerrero = new Guerrero("Lucas", 80, 100, 100)
        val expected_ninja = new Ninja("Santiago", 60, 70, 60)
        val expected_magonegro = new MagoNegro("Balbontin", 60, 50, 80, 100)
        val expected_magoblanco = new MagoBlanco("Duarte", 70, 60, 80, 90)

        assertEquals(expected_guerrero.name, guerrero.name, "Not the same name")
        assertEquals(expected_paladin.name, paladin.name, "Not the same name")
        assertEquals(expected_ninja.name, ninja.name, "Not the same name")
        assertEquals(expected_magonegro.name, magonegro.name, "Not the same name")
        assertEquals(expected_magoblanco.name, magoblanco.name, "Not the same name")

        assertEquals("Lucas", guerrero.name, "Not the same name")
        assertEquals("Diego", paladin.name, "Not the same name")
        assertEquals("Santiago".name, ninja.name, "Not the same name")
        assertEquals("Balbontin".name, magonegro.name, "Not the same name")
        assertEquals("Duarte".name, magoblanco.name, "Not the same name")

        assertEquals(expected_guerrero.hp, guerrero.hp, "Not the same hp")
        assertEquals(expected_guerrero.weight, guerrero.weight, "Not the same weight")
        assertEquals(expected_guerrero.defense, guerrero.defense, "Not the same defense")

        assertEquals(80, guerrero.hp, "Not the same hp")
        assertEquals(100, guerrero.weight, "Not the same weight")
        assertEquals(100, guerrero.defense, "Not the same defense")

        assertEquals(expected_magonegro.hp, magonegro.hp, "Not the same hp")
        assertEquals(expected_magonegro.defense, magonegro.defense, "Not the same defense")
        assertEquals(expected_magonegro.weight, magonegro.weight, "Not the same weight")
        assertEquals(expected_magonegro.mana, magonegro.mana, "Not the same mana")

        assertEquals(60, magonegro.hp, "Not the same hp")
        assertEquals(50, magonegro.defense, "Not the same defense")
        assertEquals(80, magonegro.weight, "Not the same weight")
        assertEquals(100, magonegro.mana, "Not the same mana")
    }

    test("simple character comparison") {
        val statg_paladin = new Paladin("Lucas", 80, 100, 100)
        val statmn_magoblanco = new MagoBlanco("Balbontin", 60, 50, 80, 100)

        assert(!magonegro.equals(magoblanco))
        assert(!paladin.equals(guerrero))
        assert(!ninja.equals(paladin))
        assert(!magoblanco.equals(ninja))

        assert(!guerrero.equals(statg_paladin))
        assert(!magonegro.equals(statmn_magoblanco))
    }

    test("character stat comparison") {
        var paladin_stat_guerrero = new Guerrero("Diego", 100, 90, 120)
        var mblanco_stat_magonegro = new MagoNegro("Duarte", 70, 60, 80, 90)
        var guerrero_stat_ninja = new Ninja("Lucas", 80, 100, 100)

        assert(!paladin_stat_guerrero.equals(paladin))
        assertEquals(paladin_stat_guerrero.name, paladin.name)
        assertEquals(paladin_stat_guerrero.hp, paladin.hp)
        assertEquals(paladin_stat_guerrero.defense, paladin.defense)
        assertEquals(paladin_stat_guerrero.weight, paladin.weight)

        assert(!mblanco_stat_magonegro.equals(magoblanco))
        assertEquals(mblanco_stat_magonegro.mana, magoblanco.mana)

        assertEquals(ninja.hp, magonegro.hp)
        assertEquals(ninja.weight, magoblanco.defense)
        assertEquals(paladin.hp, guerrero.weight)
        assertNotEquals(ninja.hp, paladin.hp)
        assertNotEquals(magoblanco.mana, magonegro.mana)
        assertNotEquals(paladin.defense, magonegro.hp)
    }

    test("stat changing") {
        val first_paladin = new Paladin("Diego", 90, 100, 130)
        val first_magonegro = new MagoNegro("Santiago", 50, 60, 85, 110)

        assertNotEquals(first_paladin.hp, paladin.hp)
        assertNotEquals(first_paladin.defense, paladin.defense)
        assertNotEquals(first_paladin.weight, paladin.weight)

        first_paladin.hp = 100
        first_paladin.defense = 90
        first_paladin.weight = 120
        assertEquals(first_paladin.hp, paladin.hp)
        assertEquals(first_paladin.defense, paladin.defense)
        assertEquals(first_paladin.weight, paladin.weight)

        assertNotEquals(first_magonegro.hp, magonegro.hp)
        assertNotEquals(first_magonegro.defense, magonegro.defense)
        assertNotEquals(first_magonegro.weight, magonegro.weight)

        first_magonegro.hp = 60
        first_magonegro.defense = 50
        first_magonegro.weight = 80
        first_magonegro.mana = 100
        assertEquals(first_magonegro.hp, magonegro.hp)
        assertEquals(first_magonegro.defense, magonegro.defense)
        assertEquals(first_magonegro.weight, magonegro.weight)
    }
    test("weapon") {
        assert(paladin.weapon.isEmpty, "No weapon is held")
        assert(guerrero.weapon.isEmpty, "No weapon is held")
        assert(ninja.weapon.isEmpty, "No weapon is held")
        assert(magonegro.weapon.isEmpty, "No weapon is held")
        assert(magoblanco.weapon.isEmpty, "No weapon is held")
    }
}
