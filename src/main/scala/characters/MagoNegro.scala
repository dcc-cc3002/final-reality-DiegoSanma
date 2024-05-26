package characters

import attributes.{Attributes, Mage}
import axe.Axe
import bow.Bow
import entity.Entidad
import exceptions.{InvalidInventoryException, InvalidWeaponException, InvalidWeaponTypeException, Require, WrongMageException}
import spells.{HechizoLuz, HechizoOscuro}
import staff.Staff
import sword.Sword
import wand.Wand
import weapons.TWeapons

import scala.collection.mutable.ArrayBuffer

/** A class representing a black mage (mago negro)
 *
 * A mago negro is defined by his name, hp, defense, weight and mana
 *
 * @constructor creates a new black mage with name, hp, defense, weight and mana
 * @param name the name of the mago negro
 * @param hp the hitpoints of the mago negro
 * @param defense the defense value of the mago negro
 * @param weight the weight of the mago negro
 * @param mana the mana/magic points of the mago negro
 * @param inventory the inventory where the mago negro hold his/her weapons
 * @param activeWeapon the weapon the mago negro is currently holding
 *
 * @constructor creates a new mago negro with a name, and values for hp, defense, weight and mana
 *
 * @author Diego San Martin
 *
 */

class MagoNegro(name: String,hp: Int,defense:Int,weight: Int,mana:Int ,
                inventory:ArrayBuffer[TWeapons]=ArrayBuffer(),activeWeapon:Option[TWeapons]=None)
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
  override def throwDarkSpell(spell: HechizoOscuro, victim: Entidad): Unit = {
    spell.finalInflict(this,victim)
  }

  override def throwLightSpell(spell: HechizoLuz, victim: Entidad): Unit = {
    throw new WrongMageException("Black Mage cant use a light spell")
  }

}