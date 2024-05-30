package exceptions

import bow.Bow
import characters.{MagoBlanco, MagoNegro}
import enemigo.Enemigo
import exceptions.damage.{FriendlyFireException, NotEnoughManaException, UnaliveDamagedException}
import exceptions.mage.WrongMageException
import exceptions.weaponexceptions.NotMagicWeaponException
import spells.{Curacion, Fuego, Paralisis, Trueno, Veneno}
import staff.Staff
import wand.Wand

class SpellExpectionTests extends munit.FunSuite {
  var magonegro: MagoNegro = null
  var magoblanco: MagoBlanco = null
  var mago1: MagoBlanco = null
  var mago2: MagoBlanco = null
  var enemy: Enemigo = null
  var wand: Wand = null
  var staff: Staff = null
  var bow: Bow = null
  var veneno: Veneno = null
  var curacion: Curacion = null
  var paralisis: Paralisis = null
  var fuego: Fuego = null
  var trueno: Trueno = null


  override def beforeEach(context: BeforeEach): Unit = {
    magoblanco = new MagoBlanco("Sanma",100,100,100,100)
    magonegro = new MagoNegro("Yamal",100,60,100,100)
    mago1 = new MagoBlanco("Steve",10,10,100,5)
    mago2 = new MagoBlanco("Bob",100,100,100,100)
    wand = new Wand("Stick",50,70,50)
    staff = new Staff("Bigger Stick",70,80,80)
    bow = new Bow("Bow",70,70)
    veneno = new Veneno()
    curacion = new Curacion()
    paralisis = new Paralisis()
    fuego = new Fuego()
    trueno = new Trueno()
    magoblanco.receiveWeapon(wand)
    magoblanco.changeWeapon(0)
    magonegro.receiveWeapon(staff)
    magonegro.changeWeapon(0)
    mago2.receiveWeapon(bow)
    mago2.changeWeapon(0)
    enemy = new Enemigo("Golem",10,10,150,100)
  }

  test("Wrong entity casting"){
    var found: Int = 0
    try{
      magoblanco.throwSpell(veneno,mago2)
    }
    catch{
      case e: FriendlyFireException => found +=1
    }
    assertEquals(found,1,"Exception not thrown")
    assertEquals(magoblanco.getMana(),100,"Magoblanco should not have used mana")

    try{
      magonegro.throwSpell(fuego,magoblanco)
    }
    catch {
      case e: FriendlyFireException => found +=1
    }
    assertEquals(found,2,"Exception not thrown")
    assertEquals(magonegro.getMana(),100,"Magonegro should not have used mana")
    assertEquals(magoblanco.getHp(),100,"Magoblanco should not have taken damage")

    try{
      magoblanco.throwSpell(paralisis,mago2)
    }
    catch{
      case e: FriendlyFireException => found +=1
    }
    assertEquals(found,3,"Exception not thrown")
    assertEquals(magoblanco.getMana(),100,"Magoblanco should not have used mana")

    try{
      magoblanco.throwSpell(curacion,enemy)
    }
    catch{
      case e: FriendlyFireException => found +=1
    }
    assertEquals(found,4,"Exception not thrown")
    assertEquals(magoblanco.getMana(),100,"Magoblanco should not have used mana")

    try{
      magonegro.throwSpell(trueno,magoblanco)
    }
    catch {
      case e: FriendlyFireException => found +=1
    }
    assertEquals(found,5,"Exception not thrown")
    assertEquals(magonegro.getMana(),100,"Magonegro should not have used mana")
    assertEquals(magoblanco.getHp(),100,"Magoblanco should not have taken damage")
  }

  test("Wrong Mage casting"){
    var found:Int = 0
    try{
      magonegro.throwSpell(curacion,mago1)
    }
    catch {
      case e: WrongMageException => found +=1
    }
    assertEquals(found,1,"Exception not thrown")
    assertEquals(magonegro.getMana(),100,"Magonegro should not have used mana")

    try{
      magoblanco.throwSpell(trueno,enemy)
    }
    catch {
      case e: WrongMageException => found +=1
    }
    assertEquals(found,2,"Exception not thrown")
    assertEquals(magoblanco.getMana(),100,"Magoblanco should not have used mana")
  }

  test("Not enough mana"){
    magonegro.dropWeapon(staff)
    mago1.receiveWeapon(staff)
    mago1.changeWeapon(0)
    var found: Int = 0
    try{
      mago1.throwSpell(paralisis,enemy)
    }
    catch {
      case e: NotEnoughManaException => found +=1
    }
    assertEquals(found,1,"Exception not thrown")
    assertEquals(mago1.getMana(),5,"Mage should not have used up his mana")
  }

  test("Attacking with normal or noWeapon"){
    var found:Int = 0
    try{
      mago2.throwSpell(paralisis,enemy)
    }
    catch {
      case e: NotMagicWeaponException => found +=1
    }
    assertEquals(found,1,"Exception not thrown")
    assertEquals(mago2.getMana(),100,"Mage should not have used his mana")

    mago2.dropWeapon(bow)
    mago2.throwSpell(veneno,enemy)
    assertEquals(mago2.getMana(),100,"Mage should not have used his mana")
  }

  test("Casting spell on defeated entity"){
    var found: Int = 0
    enemy.attack(mago1)
    assertEquals(mago1.isAlive(),0,"Mage should be defeated")
    try{
      magoblanco.throwSpell(curacion,mago1)
    }
    catch {
      case e: UnaliveDamagedException => found += 1
    }
    assertEquals(found,1,"Exception not thrown")
    assertEquals(magoblanco.getMana(),100,"Magoblanco should not have used his mana")
    assertEquals(mago1.isAlive(),0,"Mage should still be defeated")

    mago2.attack(enemy)
    assertEquals(enemy.isAlive(),0,"Enemy should be defeated")

    try{
      magonegro.throwSpell(trueno,enemy)
    }
    catch {
      case e: UnaliveDamagedException => found += 1
    }
    assertEquals(found,2,"Exception not thrown")
    assertEquals(enemy.isAlive(),0,"Enemy should still be defeated")
    assertEquals(magonegro.getMana(),100,"Magonegro should not have used mana")
  }

}
