/** A class that tests the functionality of the playable characters
 *
 * The test is defined by each of the possible characters you may use,
 * Paladin, Guerrero, Ninja, MagoNegro and MagoBlanco
 *
 */

import characters.{Guerrero, MagoBlanco, MagoNegro, Ninja, Paladin}
import enemigo.Enemigo
import weapons.{AMagicWeapon, AWeapon, MagicWeapon, Weapon}

import scala.collection.mutable.ArrayBuffer


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

    test("inventory and active weapon"){
        assert(paladin.getWeapons().isEmpty,"Inventory should be empty")
        assert(guerrero.getWeapons().isEmpty,"Inventory should be empty")
        assert(ninja.getWeapons().isEmpty,"Inventory should be empty")
        assert(magoblanco.getWeapons().isEmpty,"Inventory should be empty")
        assert(magonegro.getWeapons().isEmpty,"Inventory should be empty")

        assertEquals(paladin.getActiveWeapon(),None, "No weapon should be held")
        assertEquals(guerrero.getActiveWeapon(),None, "No weapon should held")
        assertEquals(ninja.getActiveWeapon(),None, "No weapon should be held")
        assertEquals(magonegro.getActiveWeapon(),None, "No weapon should be held")
        assertEquals(magoblanco.getActiveWeapon(),None, "No weapon should be held")

        var bow: Weapon = new Weapon("Legolas",50,30)
        var knife: Weapon = new Weapon("Slick",60,70)
        var wand: MagicWeapon = new MagicWeapon("Giggidy",60,70,90)
        var staff: MagicWeapon = new MagicWeapon("Stick of Truth",45,60,70)

        ninja.receiveWeapon(bow)
        assert(ninja.getWeapons().nonEmpty,"Weapon should´ve been added to the inventory")
        assertEquals(ninja.getWeapons()(0),bow,"Bow not added to inventory")
        assertEquals(ninja.getActiveWeapon(),None, "No weapon should be held")
        ninja.changeWeapon(0)
        assertEquals(ninja.getActiveWeapon().get,bow, "Bow should be equipped")

        guerrero.receiveWeapon(bow)
        assert(ninja.getWeapons().nonEmpty,"The bow should not have been removed from ninja´s inventory")
        assert(guerrero.getWeapons().isEmpty,"The bow should not have been given to the guerrero")

        ninja.receiveWeapon(knife)
        assertEquals(ninja.getWeapons()(1),knife,"Knife not added to inventory")
        assertEquals(ninja.getActiveWeapon().get,bow, "Bow should be equipped")
        ninja.changeWeapon(1)
        assertEquals(ninja.getActiveWeapon().get,knife, "Knife should be equipped")

        ninja.receiveWeapon(wand)
        assertEquals(ninja.getWeapons()(2),wand,"Wand not added to inventory")

        ninja.receiveWeapon(staff)
        assertEquals(ninja.getWeapons().length,3,"No more weapon should be added to the inventory")

        ninja.dropWeapon(staff)
        assertEquals(ninja.getWeapons().length,3,"No weapon should be removed to the inventory")

        ninja.dropWeapon(knife)
        assertEquals(ninja.getWeapons().length,2,"A weapon should´ve be removed to the inventory")
        assert(ninja.getActiveWeapon().isEmpty,"There should be no active weapon held")
        assert(!(ninja.getWeapons().contains(knife)),"Knife should no longer be in the inventory")
    }
    test("mage inventory and weapon"){
        var bow: Weapon = new Weapon("Legolas",50,30)
        var knife: Weapon = new Weapon("Slick",60,70)
        var wand: MagicWeapon = new MagicWeapon("Giggidy",60,70,90)
        var staff: MagicWeapon = new MagicWeapon("Stick of Truth",45,60,70)

        magoblanco.receiveWeapon(bow)
        assert(magoblanco.getWeapons().nonEmpty,"Weapon should´ve been added to the inventory")
        assertEquals(magoblanco.getWeapons()(0),bow,"Bow not added to inventory")
        assertEquals(magoblanco.getActiveWeapon(),None, "No weapon should be held")
        magoblanco.changeWeapon(0)
        assertEquals(magoblanco.getActiveWeapon().get,bow, "Bow should be equipped")

        guerrero.receiveWeapon(bow)
        assert(magoblanco.getWeapons().nonEmpty,"The bow should not have been removed from magoblanco´s inventory")
        assert(guerrero.getWeapons().isEmpty,"The bow should not have been given to the guerrero")

        magoblanco.receiveWeapon(knife)
        assertEquals(magoblanco.getWeapons()(1),knife,"Knife not added to inventory")
        assertEquals(magoblanco.getActiveWeapon().get,bow, "Bow should be equipped")
        magoblanco.changeWeapon(1)
        assertEquals(magoblanco.getActiveWeapon().get,knife, "Knife should be equipped")

        magoblanco.receiveWeapon(wand)
        assertEquals(magoblanco.getWeapons()(2),wand,"Wand not added to inventory")

        magoblanco.receiveWeapon(staff)
        assertEquals(magoblanco.getWeapons().length,3,"No more weapon should be added to the inventory")

        magoblanco.dropWeapon(staff)
        assertEquals(magoblanco.getWeapons().length,3,"No weapon should be removed to the inventory")

        magoblanco.dropWeapon(knife)
        assertEquals(magoblanco.getWeapons().length,2,"A weapon should´ve be removed to the inventory")
        assert(magoblanco.getActiveWeapon().isEmpty,"There should be no active weapon held")
        assert(!(magoblanco.getWeapons().contains(knife)),"Knife should no longer be in the inventory")
    }
}
