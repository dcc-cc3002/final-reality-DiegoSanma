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
  /**Getter for weapon parameter
   * Can get a None or a Some(TWeapons) type
   *
   * @return this.weapon
   */
  override def getWeapon(): Option[TWeapons] = {
    this.weapon
  }

  /** Method for making handing/equipping a weapon as a character
   * If i want to equip a wepaon that i already have equipped, the method does nothing
   * Also calls the giveToOwner, so the wepaon now has the correct owner associated
   *
   * @param weapon the weapon the character wants to receive
   */
  override def receiveWeapon(weapon:TWeapons): Unit = {
    if (this.weapon.isDefined) {
      if (this.weapon.get == weapon) {
        return
      }
    }
    this.weapon = Some(weapon)
    weapon.giveToOwner(this)
  }

  /**Method for dropping a weapon, if there is one equipped currently
   * Also calls the weapon.leaveOwner, so that the weapon doesn´t keep the
   * character as the owner depsite dropping said weapon
   */
  override def dropWeapon(): Unit = {
    if(this.weapon.isDefined){
      var aux_weapon: TWeapons = this.weapon.get
      this.weapon = None
      aux_weapon.leaveOwner()
    }
  }

  /**Method for inflicting damage through an attack
   * If no weapon is equipped, then no attack/damage is made
   * Bare in mind that one can also attack their own friends, not just enemies
   *
   * @param victim the entity to whom damage will be dealt
   */
  override def attack(victim: Entidad): Unit = {
    if(this.weapon.isEmpty){
      println(s"You currently have no weapon! The attack has failed :(")
    }
    else{
      victim.takedamage(this)
    }
  }


}
