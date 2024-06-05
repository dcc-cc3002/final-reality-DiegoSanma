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

/** A class representing a white mage (mago blanco)
 *
 * A mago blanco is defined by his name, hp, defense, weight and mana
 *
 * @constructor creates a new white mage with name, hp, defense, weight and mana
 *
 * @param name the name of the mago blanco
 * @param hp the hitpoints of the mago blanco
 * @param defense the defense value of the mago blanco
 * @param weight the weight of the mago blanco
 * @param mana the mana/magic points of the mago blanco
 * @param inventory the inventory where the mago blanco hold his/her weapons
 * @param activeWeapon the weapon the mago blanco is currently holding
 *
 * @constructor creates a new mago blanco with a name, and values for hp, defense, weight and mana
 *
 * @author Diego San Martin
 *
 */


class WhiteMage(name: String, hp: Int, defense:Int, weight: Int, mana:Int,
                inventory:ArrayBuffer[TWeapons]=ArrayBuffer(), activeWeapon:Option[TWeapons]=None)
  extends Mage(name,hp,defense,weight,mana,inventory, activeWeapon) {

  Require.Stat(hp,"Hp not valid") in (0 to 125)
  Require.Stat(defense,"Defense not valid") in (0 to 150)
  Require.Stat(weight,"Weight not valid") in (0 to 200)
  Require.Stat(mana,"Mana not valid") in (0 to 300)
  if(inventory.nonEmpty){
    throw new InvalidInventoryException("Inventory should begin empty")
  }
  if(activeWeapon.nonEmpty){
    throw new InvalidWeaponException("Active Weapon should begin empty")
  }

  /**Method for receiving a sword
   *
   * As a white mage can´t hold one, this method throws an exception
   *
   * @param weapon sword being received
   *
   * @throws InvalidWeaponTypeException
   */
  override def receiveSword(weapon: Sword): Unit = {
    throw new InvalidWeaponTypeException("White Mage cannot hold a sword")
  }

  /**Method for receiving an axe
   *
   * As a white mage can´t hold one, this method throws an exception
   *
   * @param weapon axe being received
   *
   * @throws InvalidWeaponTypeException
   */
  override def receiveAxe(weapon: Axe): Unit = {
    throw new InvalidWeaponTypeException("White Mage cannot hold a axe")
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

  /**Method for checking if a white mage can cast a light spell
   *
   * @param spell the dark spell being casted
   * @param victim the entity the spell is being casted on
   * @param magicWeapon the magic weapon the mage is holding
   *
   * @throws WrongMageException as a white mage cannot cast a dark spell
   */

  override def throwDarkSpell(spell: DarkSpells, victim: Entity,magicWeapon: IMagicWeapon): Unit = {
    throw new WrongMageException("White Mage cant use a dark spell")
  }

  /**Method for checking if a white mage can cast a light spell
   *
   * @param spell the light spell being casted
   * @param victim the entity the spell is being casted on
   * @param magicWeapon the magic weapon the white mage is holding
   */
  override def throwLightSpell(spell: LightSpells, victim: Entity,magicWeapon:IMagicWeapon): Unit = {
    spell.finalCheck(this,victim,magicWeapon)
  }
}
