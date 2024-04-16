import characters.{Guerrero, MagoBlanco, MagoNegro, Ninja, Paladin}
import weapons.{MagicWeapon, Weapon}


/**This test is for the weapons the characters hold
 *
 * It includes testing for Espada(Sword), Hacha(Axe), Arco(Bow),
 * VaritaMagica(Wand) and Baston(Staff)
 *
 */

class WeaponTest extends munit.FunSuite {
  var weapon: Weapon = null
  var magicweapon: MagicWeapon = null
  var notnamew: Weapon = null
  var notnamedw: MagicWeapon = null
  var paladin: Paladin = null
  var guerrero: Guerrero = null
  var ninja: Ninja = null
  var magonegro: MagoNegro = null
  var magoblanco: MagoBlanco = null

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
  }

  test("weapon creation"){
    assertEquals("Excalibur", weapon.name,"Weapon not given named")
    assertEquals("Palito",magicweapon.name,"Magic Weapon not given name")


    assertEquals(60,weapon.atkpoints,"Weapon not given atkpoints")
    assertEquals(20,magicweapon.atkpoints,"Magic Weapon not given atkpoints")

    assertEquals(70,weapon.weight,"Weapon not given weight")
    assertEquals(40,magicweapon.weight,"Magic Weapon not given weight")


    assertEquals(80,magicweapon.magicpoints.getOrElse(0),"Magic Weapon not given magic points")

    val supposed_owner = weapon.owner
    val supposed_mowner = magicweapon.owner
    assertEquals(paladin.hp,supposed_owner.hp,"Weapon not given owner")
    assertEquals(magonegro.hp,supposed_mowner.hp,"Magic Weapon not given owner")
    assertEquals(paladin.defense,supposed_owner.defense,"Weapon not given owner")
    assertEquals(magonegro.defense,supposed_mowner.defense,"Magic Weapon not given owner")
    assertEquals(paladin.name,supposed_owner.name,"Weapon not given owner")
    assertEquals(magonegro.name,supposed_mowner.name,"Magic Weapon not given owner")
    assertEquals(paladin.weight,supposed_owner.weight,"Weapon not given owner")
    assertEquals(magonegro.weight,supposed_mowner.weight,"Magic Weapon not given owner")
    assertEquals(magonegro.mana,supposed_mowner.mana,"Magic Weapon not given owner")

    assertEquals(paladin.weapon.get,weapon,"Paladin does not have expected weapon")
    assertEquals(magonegro.weapon.get,magicweapon,"Black Mage not given expected weapon")


  }


  test("rename"){
    val newnamew = "The Holy"
    val newnamedw = "The Black Fairy"
    val nnw = "Hell´s Blade"
    val nndw = "The Stick of Truth"
    notnamew.rename(newnamew)
    notnamedw.rename(newnamedw)
    assertEquals(newnamew,notnamew.name,"The weapon was not renamed correctly")
    assertEquals(newnamedw,notnamedw.name,"The Magic Weapon was not renamed correctly")
    notnamew.rename(nnw)
    notnamedw.rename(nndw)
    assertEquals("The Holy",notnamew.name,"The weapon should not be renamed")
    assertEquals("The Black Fairy",notnamedw.name,"The Magic Weapon should not be renamed")
  }


}
