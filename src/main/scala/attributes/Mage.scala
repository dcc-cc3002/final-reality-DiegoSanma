package attributes

import enemigo.Enemigo
import entity.{AEntidad, Entidad}
import weapons.TWeapons

import scala.collection.mutable.ArrayBuffer

/**An abstract class for a mage that includes the methods used for both
 * types of mages
 *
 * The abstract class is defined using the characteristics from the trait Attributes
 *
 * @author Diego San Martin
 */

abstract class Mage(name:String,hp:Int,defense: Int, weight: Int
                    ,private var mana:Int, private var inventory: ArrayBuffer[TWeapons]= ArrayBuffer(),private var activeWeapon: Option[TWeapons] = None)
  extends AEntidad(name,hp,defense,weight) with Attributes {
  def getMana(): Int = {
    this.mana
  }
  /**Getter for weapon parameter
   * Can get a None or a Some(TWeapons) type
   *
   * @return this.weapon
   */
  override def getWeapons(): ArrayBuffer[TWeapons] = {
    this.inventory
  }

  /** Getter for the weapon that is currently equipped
   * Bare in mind that the mage may not have a weapon currently equipped
   *
   * @return this.activeWeapon
   */

  override def getActiveWeapon(): Option[TWeapons] = {
    this.activeWeapon
  }
  /** Method for making handing/equipping a weapon as a character
   * If i want to equip a wepaon that i already have equipped, the method does nothing
   * Also calls the giveToOwner, so the wepaon now has the correct owner associated
   *
   * @param weapon the weapon the character wants to receive
   */

  override def receiveWeapon(weapon:TWeapons): Unit = {
      if (this.inventory.length >=3) {
        return
      }
      else if(!(this.inventory.contains(weapon))) {
        this.inventory += weapon
        weapon.giveToOwner(this)
      }
    }

  /**Method for dropping a weapon, if there is one equipped currently
   * Also calls the weapon.leaveOwner, so that the weapon doesn´t keep the
   * character as the owner despite dropping said weapon
   */
  override def dropWeapon(weapon:TWeapons): Unit = {
    var position = this.inventory.indexOf(weapon)
    if(position!= -1) {
      this.inventory.remove(position)
      weapon.leaveOwner()
      if (this.getActiveWeapon().isDefined) {
        if (this.getActiveWeapon().get == weapon)
          this.activeWeapon = None
      }
    }
  }

  /**Method for changing the weapon that is currently equipped by the mage
   * Position must be in between the values according to the inventory´s space
   *
   * @param position the position of the weapon in the inventory you wish to equip
   */
  override def changeWeapon(position: Int): Unit = {
    this.activeWeapon = Some(this.inventory(position))
  }

  /**Method for inflicting damage through an attack
   * If no weapon is equipped, then no attack/damage is made
   * Bare in mind that one can also attack their own friends, not just enemies
   *
   * @param victim the entity to whom damage will be dealt
   */
  override def attack(victim: Entidad): Unit = {
    if(getActiveWeapon().isEmpty){
      println(s"You currently have no weapon! The attack has failed :(")
    }
    else{
      victim.takedamage(this)
    }
  }


}
