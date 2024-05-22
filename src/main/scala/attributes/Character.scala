package attributes

import enemigo.{Enemigo, EnemigoAttributes}
import entity.{AEntidad, Entidad}
import exceptions.{AlreadyOwnedException, FullInventoryException, FullPartyException, SameClassAttackException}
import weapons.{TWeapons, Weapon}

import scala.collection.mutable.ArrayBuffer

/**An abstract class for a character that includes the methods used for
 * different types of characters
 *
 * The abstract class is defined using the characteristics from the trait Attributes
 *
 * @author Diego San Martin
 */

abstract class Character(name:String,hp:Int,defense: Int, weight: Int,
                         private var inventory:ArrayBuffer[TWeapons],private var activeWeapon: Option[TWeapons])
  extends AEntidad(name,hp,defense,weight) with Attributes {
  /**Getter for inventory parameter
   * Will return all the weapons currently in the characters inventory
   *
   * @return this.inventory
   */
  override def getWeapons(): ArrayBuffer[TWeapons] = {
    this.inventory
  }

  /** Getter for the weapon that is currently equipped
   * Bare in mind that the character may not have a weapon currently equipped
   *
   * @return this.activeWeapon
   */

  override def getActiveWeapon(): Option[TWeapons] = {
    this.activeWeapon
  }

  /** Method for making handing/equipping a weapon as a character
   * If i want to equip a weapon that i already have equipped, the method does nothing
   * Also calls the giveToOwner, so the wepaon now has the correct owner associated
   *
   * @param weapon the weapon the character wants to receive
   */
  override def receiveWeapon(weapon:TWeapons): Unit = {
    if (this.inventory.length >=3) {
      throw new FullInventoryException("Inventory already has 3 weapons")
    }
    if (weapon.getOwner().isDefined){
      throw new AlreadyOwnedException("Weapon already has a current owner")
    }
    else if(!(this.inventory.contains(weapon))) {
      weapon.giveToOwner(this)
    }
  }

  /**Method for dropping a weapon, if there is one equipped currently
   * Also calls the weapon.leaveOwner, so that the weapon doesn´t keep the
   * character as the owner depsite dropping said weapon
   */
  override def dropWeapon(weapon:TWeapons): Unit = {
    var position = this.inventory.indexOf(weapon)
    if(position!= -1){
       this.inventory.remove(position)
       weapon.leaveOwner()
       if(this.getActiveWeapon().isDefined) {
         if (this.getActiveWeapon().get == weapon)
           this.activeWeapon = None
       }
    }
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
      victim.takedamagePlayer(this)
    }
  }
  /** Method for when an player is being attacked by another player
   *
   * @param agresor the player that is attacking
   *
   * @throws SameClassAttackException
   */
  override def takedamagePlayer(agresor: Attributes): Unit = {
    throw new SameClassAttackException("Player cant attack another Player")
  }

  /**Method for when an enemy is attacking an player
   *
   *  If the player´s defense is greater than the attack, no damage is done
   *  If the player´s health drops below 0, it is fixated to 0 and therefore has been defeated
   *
   * @param agresor the enemy attacking the player
   *
   */

  override def takedamageEnemy(agresor: EnemigoAttributes): Unit = {
    var damage = agresor.getAttack() - this.getDefense()
    if(damage<0){
      return
    }
    else{
      this.takedamage(damage)
    }
  }

  /**Method for changing the weapon that is currently equipped by the character
   * Position must be in between the values according to the inventory´s space
   *
   * @param position the position of the weapon in the inventory you wish to equip
   */
  override def changeWeapon(position: Int): Unit = {
    this.activeWeapon = Some(this.inventory(position))
  }


}
