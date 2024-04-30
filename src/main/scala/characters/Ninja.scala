package characters

import attributes.Attributes
import attributes.Character
import exceptions.{InvalidInventoryException, InvalidWeaponException, Require}
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
  if(activeWeapon.isEmpty){
    throw new InvalidWeaponException("Active Weapon should begin empty")
  }
}
