package characters

import attributes.Attributes
import attributes.Character
import axe.Axe
import bow.Bow
import exceptions.{InvalidInventoryException, InvalidWeaponException, InvalidWeaponTypeException, Require}
import staff.Staff
import sword.Sword
import wand.Wand
import weapons.TWeapons

import scala.collection.mutable.ArrayBuffer

/** A class representing a ninja
 *
 * A ninja is defined by his name, hp, defense and weight
 *
 * @param name the name of the ninja
 * @param hp the hitpoints of the ninja
 * @param defense the defense value of the ninja
 * @param weight the weight of the ninja
 * @param inventory the inventory of the ninja where he/she holds their weapons
 * @param activeWeapon the weapon the ninja is currently holding
 *
 * @constructor creates a new ninja with a name, and values for hp, defense and weight
 *
 * @author Diego San Martin
 *
 */

class Ninja(name: String,hp: Int,defense:Int,weight: Int,
            inventory:ArrayBuffer[TWeapons]=ArrayBuffer(),activeWeapon:Option[TWeapons]=None)
  extends Character(name,hp,defense,weight,inventory,activeWeapon){
  Require.Stat(hp,"Hp not valid") in (0 to 100)
  Require.Stat(defense,"Defense not valid") in (0 to 150)
  Require.Stat(weight,"Weight not valid") in (0 to 100)
  if(inventory.nonEmpty){
    throw new InvalidInventoryException("Inventory should begin empty")
  }
  if(activeWeapon.nonEmpty){
    throw new InvalidWeaponException("Active Weapon should begin empty")
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

  /**Method for receiving a sword
   * Adds it to slot in inventory, and calls to change the weapon´s owner to whom is receiving it
   *
   * @param weapon sword being received
   */
  override def receiveSword(weapon: Sword): Unit = {
    this.inventory.addOne(weapon)
    weapon.changeOwner(this)
  }

  /**Method for receiving a wand
   * Adds it to slot in inventory, and calls to change the weapon´s owner to whom is receiving it
   *
   * @param weapon wand being received
   */
  override def receiveWand(weapon: Wand): Unit = {
    this.inventory.addOne(weapon)
    weapon.changeOwner(this)
  }
  /**Method for receiving a staff
   *
   * As a ninja can´t hold one, this method throws an exception
   *
   * @param weapon staff being received
   *
   * @throws InvalidWeaponTypeException
   */

  override def receiveStaff(weapon: Staff): Unit = {
    throw new InvalidWeaponTypeException("Staff cannot be held by ninja")
  }

  /**Method for receiving a axe
   *
   * As a ninja can´t hold one, this method throws an exception
   *
   * @param weapon axe being received
   *
   * @throws InvalidWeaponTypeException
   */
  override def receiveAxe(weapon: Axe): Unit = {
    throw new InvalidWeaponTypeException("Axe cannot be held by ninja")
  }
}
