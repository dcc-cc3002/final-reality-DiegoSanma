package characters

import attributes.{Attributes, Mage}
import exceptions.{InvalidInventoryException, InvalidWeaponException, Require}
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
  if(activeWeapon.isEmpty){
    throw new InvalidWeaponException("Active Weapon should begin empty")
  }
}