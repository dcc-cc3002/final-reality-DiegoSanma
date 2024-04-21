/** A class that tests the functionality of the playable characters
 *
 * The test is defined by each of the possible characters you may use,
 * Paladin, Guerrero, Ninja, MagoNegro and MagoBlanco
 *
 */

import characters.{Guerrero, MagoBlanco, MagoNegro, Ninja, Paladin}
import weapons.{AMagicWeapon, AWeapon, MagicWeapon, Weapon}


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
        var pal_name: String = "Diego"
        var guerrero_name : String = "Lucas"
        var ninja_name : String = "Santiago"
        var magonegro_name: String = "Balbontin"
        var magoblanco_name: String = "Duarte"
        assertEquals(pal_name,paladin.getName(),"Name not given correctly")
        assertEquals(guerrero_name,guerrero.getName(),"Name not given correctly")
        assertEquals(ninja_name,ninja.getName(),"Name not given correctly")
        assertEquals(magonegro_name,magonegro.getName(),"Name not given correctly")
        assertEquals(magoblanco_name,magoblanco.getName(),"Name not given correctly")

        var pal_hp: Int = 100
        var guerrero_hp : Int =80
        var ninja_hp : Int = 60
        var magonegro_hp: Int = 60
        var magoblanco_hp: Int = 70
        assertEquals(pal_hp,paladin.getHp(),"HP not given correctly")
        assertEquals(guerrero_hp,guerrero.getHp(),"HP not given correctly")
        assertEquals(ninja_hp,ninja.getHp(),"HP not given correctly")
        assertEquals(magonegro_hp,magonegro.getHp(),"HP not given correctly")
        assertEquals(magoblanco_hp,magoblanco.getHp(),"HP not given correctly")

        var pal_defense: Int = 90
        var guerrero_defense : Int = 100
        var ninja_defense : Int = 70
        var magonegro_defense: Int = 50
        var magoblanco_defense: Int = 60
        assertEquals(pal_defense,paladin.getDefense(),"Defense not given correctly")
        assertEquals(guerrero_defense,guerrero.getDefense(),"Defense not given correctly")
        assertEquals(ninja_defense,ninja.getDefense(),"Defense not given correctly")
        assertEquals(magonegro_defense,magonegro.getDefense(),"Defense not given correctly")
        assertEquals(magoblanco_defense,magoblanco.getDefense(),"Defense not given correctly")

        var pal_weight: Int = 120
        var guerrero_weight : Int = 100
        var ninja_weight : Int = 60
        var magonegro_weight: Int = 80
        var magoblanco_weight: Int = 80

        assertEquals(pal_weight,paladin.getWeight(),"Weight not given correctly")
        assertEquals(guerrero_weight,guerrero.getWeight(),"Weight not given correctly")
        assertEquals(ninja_weight,ninja.getWeight(),"Weight not given correctly")
        assertEquals(magonegro_weight,magonegro.getWeight(),"Weight not given correctly")
        assertEquals(magoblanco_weight,magoblanco.getWeight(),"Weight not given correctly")

        var magonegro_mana: Int = 100
        var magoblanco_mana: Int = 90
        assertEquals(magonegro_mana,magonegro.getMana(),"Mana not given correctly")
        assertEquals(magoblanco_mana,magoblanco.getMana(),"Mana not given correctly")
    }

    test("weapon") {
        assertEquals(paladin.getWeapon(),None, "No weapon should be held")
        assertEquals(guerrero.getWeapon(),None, "No weapon should held")
        assertEquals(ninja.getWeapon(),None, "No weapon should be held")
        assertEquals(magonegro.getWeapon(),None, "No weapon should be held")
        assertEquals(magoblanco.getWeapon(),None, "No weapon should be held")

        var bow: Weapon = new Weapon("Legolas",50,30,Some(ninja))
        var staff: MagicWeapon = new MagicWeapon("Stick of Truth",45,60,70,Some(magoblanco))
        var actual_ninjaW = ninja.getWeapon()
        var actual_magoblancoW = magoblanco.getWeapon()
        assertEquals(actual_ninjaW.isDefined,true,"Weapon was not given")
        assertEquals(actual_magoblancoW.isDefined,true,"Weapon was not given")
        assertEquals(actual_ninjaW.get.isInstanceOf[Weapon],true,"Weapon given is not a Weapon")
        assertEquals(actual_magoblancoW.get.isInstanceOf[MagicWeapon],true,"Weapon given is not a Weapon")
        assertEquals(actual_ninjaW.get,bow,"Weapon given to ninja is not the bow")
        assertEquals(actual_magoblancoW.get,staff,"Magic Weapon given to white mage is not a staff")

        var knife:Weapon = new Weapon("Slick",60,70)
        ninja.receiveWeapon(knife)
        var ninja_newW = ninja.getWeapon()
        assertEquals(ninja_newW.get,knife,"Ninja did not receive his new weapon")
        assertEquals(knife.getOwner().get,ninja,"Knife doesnt have correct owner")

        var wand: MagicWeapon = new MagicWeapon("Giggidy",60,70,90)
        magoblanco.receiveWeapon(wand)
        var magoblanco_newW = magoblanco.getWeapon()
        assertEquals(magoblanco_newW.get,wand,"White Mage did not receive his new weapon")
        assertEquals(wand.getOwner().get,magoblanco,"Wand doesnt have correct owner")

        ninja.dropWeapon()
        var ninja_last = ninja.getWeapon()
        assert(ninja_last.isEmpty,"Ninja did not drop his weapon")

        magoblanco.dropWeapon()
        var magoblanco_last = magoblanco.getWeapon()
        assert(magoblanco_last.isEmpty,"White Mage did not drop his weapon")
    }
}
