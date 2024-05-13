package characters

import attributes.{Attributes, Mage}
import axe.Axe
import bow.Bow
import exceptions.{InvalidInventoryException, InvalidWeaponException, InvalidWeaponTypeException, Require}
import staff.Staff
import sword.Sword
import wand.Wand
import weapons.TWeapons

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


class MagoBlanco(name: String,hp: Int,defense:Int,weight: Int,mana:Int ,
                 inventory:ArrayBuffer[TWeapons]=ArrayBuffer(),activeWeapon:Option[TWeapons]=None)
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

  override def receiveSword(weapon: Sword): Unit = {
    throw new InvalidWeaponTypeException("White Mage cannot hold a sword")
  }

  override def receiveAxe(weapon: Axe): Unit = {
    throw new InvalidWeaponTypeException("White Mage cannot hold a axe")
  }

  override def receiveBow(weapon: Bow): Unit = {
    this.inventory.addOne(weapon)
    weapon.changeOwner(this)
  }
  override def receiveWand(weapon: Wand): Unit = {
    this.inventory.addOne(weapon)
    weapon.changeOwner(this)
  }
  override def receiveStaff(weapon: Staff): Unit = {
    this.inventory.addOne(weapon)
    weapon.changeOwner(this)
  }
}
