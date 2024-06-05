package characters

import attributes.{Attributes, Mage}
import axe.Axe
import bow.Bow
import entity.Entity
import exceptions.initializing.{InvalidInventoryException, Require}
import exceptions.mage.WrongMageException
import exceptions.weaponexceptions.{InvalidWeaponException, InvalidWeaponTypeException}
import spells.{DarkSpells, LightSpells}
import staff.Staff
import sword.Sword
import wand.Wand
import weapons.{IMagicWeapon, TWeapons}

import scala.collection.mutable.ArrayBuffer

/** A class representing a black mage (mago negro)
 *
 * A black mage is defined by his name, hp, defense, weight and mana
 *
 * @constructor creates a new black mage with name, hp, defense, weight and mana
 * @param name the name of the black mage
 * @param hp the hitpoints of the black mage
 * @param defense the defense value of the black mage
 * @param weight the weight of the black mage
 * @param mana the mana/magic points of the black mage
 * @param inventory the inventory where the black mage hold his/her weapons
 * @param activeWeapon the weapon the black mage is currently holding
 *
 * @constructor creates a new black mage with a name, and values for hp, defense, weight and mana
 *
 * @author Diego San Martin
 *
 */

class BlackMage(name: String, hp: Int, defense:Int, weight: Int, mana:Int,
                inventory:ArrayBuffer[TWeapons]=ArrayBuffer(), activeWeapon:Option[TWeapons]=None)
  extends Mage(name,hp,defense,weight,mana,inventory, activeWeapon) {

  Require.Stat(hp,"Hp not valid") in (0 to 150)
  Require.Stat(defense,"Defense not valid") in (0 to 200)
  Require.Stat(weight,"Weight not valid") in (0 to 250)
  Require.Stat(mana,"Mana not valid") in (0 to 200)
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
  /**Method for receiving an axe
   *
   * As a black mage can´t hold one, this method throws an exception
   *
   * @param weaponn axe being received
   *
   * @throws InvalidWeaponTypeException
   */
  override def receiveAxe(weapon: Axe): Unit = {
    throw new InvalidWeaponTypeException("Black Mage cannot hold an Axe")
  }
  /**Method for receiving a bow
   *
   * As a black mage can´t hold one, this method throws an exception
   *
   * @param weapon bow being received
   *
   * @throws InvalidWeaponTypeException
   */
  override def receiveBow(weapon: Bow): Unit = {
    throw new InvalidWeaponTypeException("Black Mage cannot hold a Bow")
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
   * Adds it to slot in inventory, and calls to change the weapon´s owner to whom is receiving it
   *
   * @param weapon staff being received
   */
  override def receiveStaff(weapon: Staff): Unit = {
    this.inventory.addOne(weapon)
    weapon.changeOwner(this)
  }

  /**Method for checking if a black mage can throw a dark spell
   *
   * @param spell the dark spell being casted
   * @param victim the entity the spell is being casted on
   * @param magicWeapon the magic weapon the mage is weilding
   */
  override def throwDarkSpell(spell: DarkSpells, victim: Entity,magicWeapon: IMagicWeapon): Unit = {
    spell.finalCheck(this,victim,magicWeapon)
  }

  /**Method for checking if a black mage can throw a light spell
   *
   * @param spell the light spell being casted
   * @param victim the entity the spell is being casted on
   *
   * @throws WrongMageException as a black mage cannot use a light spell
   */
  override def throwLightSpell(spell: LightSpells, victim: Entity,magicWeapon:IMagicWeapon): Unit = {
    throw new WrongMageException("Black Mage cant use a light spell")
  }

}