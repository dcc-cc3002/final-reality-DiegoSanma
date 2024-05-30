package attributes

import enemigo.{Enemigo, EnemigoAttributes}
import entity.{AEntidad, Entidad}
import exceptions.weaponexceptions.{AlreadyOwnedException, FullInventoryException}
import exceptions.damage.SameClassAttackException
import exceptions.party.FullPartyException
import turnos.ProgramadorDeTurnos
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
  /** Constant parameter that holds the max health of a character */

  private val maxHealth: Int = hp

  /** Parameters for holding onto the values of current action bar, and action bar maximum */
  private var actionBar: Int = 0
  /** Max action bar starts as weight, as a character cannot begin with a weapon held */
  private var maxActionBar = this.weight

  /** Getter for max Health if a Character
   *
   * @return this.maxHealth
   */

  override def getMaxHealth(): Int = {
    this.maxHealth
  }
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

  /**Getter for action bar
   *
   * @return this.actionBar
   */
  override def getActionBar(): Int = {
    this.actionBar
  }

  /**Getter for Max action bar
   *
   * @return this.maxActionBar
   */
  override def getMaxActionBar: Int = {
    this.maxActionBar
  }
  override def getActiveWeapon(): Option[TWeapons] = {
    this.activeWeapon
  }

  /**Method for updating the action bar whenever a weapon is switched/dropped, that is why it is protected
   *
   * If no weapon is held, it just is the character´s weight, if else
   * it adds uo half of the held weapon´s weight
   */
  override protected def updateMaxActionBar(): Unit = {
    if(getActiveWeapon().isEmpty){
      this.maxActionBar = this.getWeight()
    }
    else{
      this.maxActionBar = this.getWeight() + (0.5 * getActiveWeapon().get.getWeaponWeight()).toInt
    }
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
         if (this.getActiveWeapon().get == weapon) {
           this.activeWeapon = None
           updateMaxActionBar()
         }
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
    if(damage>0) {
      this.checkHealth(damage)
    }
  }

  /**Method for changing the weapon that is currently equipped by the character
   * Position must be in between the values according to the inventory´s space
   *
   * @param position the position of the weapon in the inventory you wish to equip
   */
  override def changeWeapon(position: Int): Unit = {
    this.activeWeapon = Some(this.inventory(position))
    updateMaxActionBar()
  }

  /**Method for adding a character into the turn scheduler
   *
   * @param scheduler the turn scheduler the character is being added to
   */
  override def addToTurns(scheduler: ProgramadorDeTurnos): Unit = {
    scheduler.addCharacter(this)
  }

  /** Method for healing a character by a certain percentage
   *
   * This percentage is according to their max health, not the health they currently have
   *
   */

  /**Method for removing a character from the turn scheduler
   *
   * @param scheduler the turn scheduler he is being removed from
   */
  override def removeFromTurns(scheduler: ProgramadorDeTurnos): Unit = {
    scheduler.removeCharacter(this)
  }
  override def heal(amountPercentage:Double): Unit = {
    val healing: Int = (amountPercentage*this.getMaxHealth()).toInt
    this.maxHeal(healing,getMaxHealth())
  }

}
