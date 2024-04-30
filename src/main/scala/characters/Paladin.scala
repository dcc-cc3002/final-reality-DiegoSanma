package characters

import attributes.Attributes
import attributes.Character
import exceptions.{InvalidInventoryException, InvalidWeaponException, Require}
import weapons.TWeapons

import java.security.InvalidParameterException
import scala.collection.mutable.ArrayBuffer

/** A class representing a paladin
 *
 * A paladin is defined by his name, hp, defense and weight
 *
 * @param name the name of the paladin
 * @param hp the hitpoints of the paladin
 * @param defense the defense value of the paladin
 * @param weight the weight of the paladin
 * @param inventory the inventory where the paladin holds his/her weapons
 * @param activeWeapon the weapon the paladin is currently holding
 *
 * @constructor creates a new paladin with a name, and values for hp, defense and weight
 *
 * @author Diego San Martin
 *
 */


class Paladin(name: String,hp: Int,defense:Int,weight: Int,
              inventory:ArrayBuffer[TWeapons]=ArrayBuffer(),activeWeapon:Option[TWeapons]=None)
  extends Character(name,hp,defense,weight,inventory, activeWeapon) {

  Require.Stat(hp,"Hp not valid") in (0 to 150)
  Require.Stat(defense,"Defense not valid") in (0 to 175)
  Require.Stat(weight,"Weight not valid") in (0 to 200)
  if(inventory.nonEmpty){
    throw new InvalidInventoryException("Inventory should begin empty")
  }
  if(activeWeapon.isEmpty){
    throw new InvalidWeaponException("Active Weapon should begin empty")
  }
}
