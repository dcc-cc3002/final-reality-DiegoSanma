package attributes

import enemigo.Enemigo
import entity.{AEntidad, Entidad}
import weapons.TWeapons

/**An abstract class for a mage that includes the methods used for both
 * types of mages
 *
 * The abstract class is defined using the characteristics from the trait Attributes
 *
 * @author Diego San Martin
 */

abstract class Mage(name:String,hp:Int,defense: Int, weight: Int,private var mana:Int, private var weapon:Option[TWeapons]=None) extends AEntidad(name,hp,defense,weight) with Attributes {
  def getMana(): Int = {
    this.mana
  }
  override def getWeapon(): Option[TWeapons] = {
    this.weapon
  }

  override def receiveWeapon(weapon:TWeapons): Unit = {
    if (this.weapon.isDefined) {
      if (this.weapon.get == weapon) {
        return
      }
    }
    this.weapon = Some(weapon)
    weapon.giveToOwner(this)
  }
  override def dropWeapon(): Unit = {
    if(this.weapon.isDefined){
      var aux_weapon: TWeapons = this.weapon.get
      this.weapon = None
      aux_weapon.leaveOwner()
    }
  }

  override def attack(victim: Entidad): Unit = {
    if(this.weapon.isEmpty){
      println(s"You currently have no weapon! The attack has failed :(")
      return
    }
    else{
      victim.takedamage(this)
    }
  }

  override def takedamage(agresor: Entidad): Unit = {
    if(agresor.isInstanceOf[Enemigo]){
      this.hp -= agresor.asInstanceOf[Enemigo].getAttack()-this.getDefense()
    }
    else {
      println(s"Be careful, you´ve just attacked your friend!")
      this.hp -= agresor.asInstanceOf[Attributes].getWeapon().get.getAtkPts()-this.getDefense()
    }
  }

}
