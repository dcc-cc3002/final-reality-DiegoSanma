import characters.{Guerrero, MagoBlanco, MagoNegro, Ninja, Paladin}
import weapons.{AMagicWeapon, AWeapon, MagicWeapon, Weapon}


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
    magonegro = new MagoNegro("Balbontin", 60, 50, 80, 100)
    magoblanco = new MagoBlanco("Duarte", 70, 60, 80, 90)
    weapon = new Weapon("Excalibur",60,70,Some(paladin))
    magicweapon = new MagicWeapon("Palito",20,40,80,Some(magonegro))
    notnamew = new Weapon("",80,90,Some(guerrero))
    notnamedw = new MagicWeapon("",30,40,50,Some(magoblanco))
  }

  test("weapon creation"){
    assertEquals("Excalibur", weapon.getWeaponName(),"Weapon not given named")
    assertEquals("Palito",magicweapon.getWeaponName(),"Magic Weapon not given name")


    assertEquals(60,weapon.getAtkPts(),"Weapon not given atkpoints")
    assertEquals(20,magicweapon.getAtkPts(),"Magic Weapon not given atkpoints")

    assertEquals(70,weapon.getWeaponWeight(),"Weapon not given weight")
    assertEquals(40,magicweapon.getWeaponWeight(),"Magic Weapon not given weight")


    assertEquals(80,magicweapon.getMagicpts(),"Magic Weapon not given magic points")

    val supposed_owner = weapon.getOwner()
    val supposed_mowner = magicweapon.getOwner()
    assert(supposed_owner.isDefined,"Weapon has no owner")
    assert(supposed_mowner.isDefined,"Magic Weapon has no owner")
    assertEquals(paladin,supposed_owner.get.asInstanceOf[Paladin],"Weapon not given owner")
    assertEquals(magonegro,supposed_mowner.get.asInstanceOf[MagoNegro],"Magic Weapon not given owner")


    assertEquals(paladin.getWeapon().get,weapon,"Paladin does not have expected weapon")
    assertEquals(magonegro.getWeapon().get,magicweapon,"Black Mage not given expected weapon")

  }


  test("rename"){
    val newnamew = "The Holy"
    val newnamedw = "The Black Fairy"
    val nnw = "Hell´s Blade"
    val nndw = "The Stick of Truth"
    notnamew.rename(newnamew)
    notnamedw.rename(newnamedw)
    assertEquals(newnamew,notnamew.getWeaponName(),"The weapon was not renamed correctly")
    assertEquals(newnamedw,notnamedw.getWeaponName(),"The Magic Weapon was not renamed correctly")
    notnamew.rename(nnw)
    notnamedw.rename(nndw)
    assertEquals("The Holy",notnamew.getWeaponName(),"The weapon should not be renamed")
    assertEquals("The Black Fairy",notnamedw.getWeaponName(),"The Magic Weapon should not be renamed")
  }


}
