package characters

import attributes.Attributes
import attributes.Character
import axe.Axe
import bow.Bow
import exceptions.weaponexceptions.{InvalidWeaponException, InvalidWeaponTypeException}
import exceptions.initializing.{InvalidInventoryException, Require}
import staff.Staff
import sword.Sword
import wand.Wand
import weapons.TWeapons

import scala.collection.mutable.ArrayBuffer

/** A class representing a warrior(guerrero)
 *
 * A guerrero is defined by his name, hp, defense and weight
 *
 * @param name the name of the guerrero
 * @param hp the hitpoints of the guerrero
 * @param defense the defense value of the guerrero
 * @param weight the weight of the guerrero
 * @param inventory the inventory where the guerrero holds his weapons
 * @param activeWeapon the weapon the guerrero is currently holding
 *
 * @constructor creates a new guerrero with a name, and values for hp, defense and weight
 *
 * @author Diego San Martin
 *
 */
class Warrior(name: String, hp: Int, defense:Int, weight: Int,
              inventory:ArrayBuffer[TWeapons]=ArrayBuffer(), activeWeapon:Option[TWeapons]=None) extends
  Character(name,hp,defense,weight,inventory,activeWeapon){

  /**Checks whether the values used to create the guerrero are valid  */

  Require.Stat(hp,"Hp not valid") in (0 to 200)
  Require.Stat(defense,"Defense not valid") in (0 to 300)
  Require.Stat(weight,"Weight not valid") in (0 to 200)
  if(inventory.nonEmpty){
    throw new InvalidInventoryException("Inventory should begin empty")
  }
  if(activeWeapon.nonEmpty){
    throw new InvalidWeaponException("Active Weapon should begin empty")
  }

  /**Method for receiving a sword
   * Adds it to slot in inventory, and calls to change the weapon´s owner to whom is receiving it
   *
   * @param weapon sword being received
   */
  override def receiveSword(weapon: Sword): Unit = {
    this.inventory.addOne(weapon)
    weapon.changeOwner(this)
  }
  /**Method for receiving a axe
   * Adds it to slot in inventory, and calls to change the weapon´s owner to whom is receiving it
   *
   * @param weapon axe being received
   */
  override def receiveAxe(weapon: Axe): Unit = {
    this.inventory.addOne(weapon)
    weapon.changeOwner(this)
  }
  /**Method for receiving a bow
   * Adds it to slot in inventory, and calls to change the weapon´s owner to whom is receiving it
   *
   * @param weapon bow being received
   */

  override def receiveBow(weapon: Bow): Unit = {
    this.inventory.addOne(weapon)
    weapon.changeOwner(this)
  }

  /**Method for receiving a wand
   *
   * As a guerrero can´t hold one, this method throws an exception
   *
   * @param weapon wand being received
   *
   * @throws InvalidWeaponTypeException
   */
  override def receiveWand(weapon: Wand): Unit = {
    throw new InvalidWeaponTypeException("Guerrero cannot hold a wand")
  }

  /**Method for receiving a staff
   *
   * As a guerrero can´t hold one, this method throws an exception
   *
   * @param weapon staff being received
   *
   * @throws InvalidWeaponTypeException
   */

  override def receiveStaff(weapon: Staff): Unit = {
    throw new InvalidWeaponTypeException("Guerrero cannot hold a staff")
  }
}
