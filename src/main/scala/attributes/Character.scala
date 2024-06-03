package attributes

import enemy.{Enemy, EnemyAttributes}
import entity.{AEntity, Entity}
import exceptions.weaponexceptions.{AlreadyOwnedException, FullInventoryException, NoWeaponException}
import exceptions.damage.{FriendlyFireException, SameClassAttackException}
import exceptions.partyexc.FullPartyException
import turnscheduler.TurnScheduler
import weapons.TWeapons

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
  extends AEntity(name,hp,defense,weight) with Attributes {

  /** Parameters for holding onto the values of current action bar maximum, starts of as just the characters weight*/
    private var maxActionBar = this.weight

  /**Getter for maxActionBar
   *
   * @return this.maxActionBar
   */
  override def getMaxActionBar(): Int = {
    this.maxActionBar
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
   * Also calls the giveToOwner, so the weapon now has the correct owner associated
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
   * Also calls the weapon.leaveOwner, so that the weapon doe not keep the
   * character as the owner despite dropping said weapon
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
  override def attack(victim: Entity): Unit = {
    if(getActiveWeapon().isEmpty){
      throw new NoWeaponException("You can´t attack, you have no weapon!")
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

  override def takedamageEnemy(agresor: EnemyAttributes): Unit = {
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
  override def addToTurns(scheduler: TurnScheduler): Unit = {
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
  override def removeFromTurns(scheduler: TurnScheduler): Unit = {
    scheduler.removeCharacter(this)
  }


  /**Method for checking if character is an enemy
   *
   * @throws FriendlyFireException as character is not an enemy
   */
  override def checkifEnemy(): Unit = {
    throw new FriendlyFireException("Cant inflict damage/effect to an ally")
  }

  /**Method for checking if character is a character
   *
   */
  override def checkifCharacter(): Unit = {
  }
}
