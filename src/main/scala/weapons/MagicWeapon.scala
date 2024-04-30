package weapons

import attributes.Attributes
import exceptions.{InvalidOwnerException, Require}
/**Class for a magic weapon
 * A magic weapon is defined by its name, attack points, weight, magic points and current owner
 *
 * A weapon is always created without an owner in the beginning, later one can be added
 * Bare in mind that having an owner does not necessarily imply that said weapon is equipped/ being used by that owner
 *
 * @param name the name of the weapon
 * @param atkpoints the attack points of the weapon
 * @param weight the weight of the weapon
 * @param magicpoints the magic points of the weapon
 * @param owner the current owner of the weapon
 *
 * @author Diego San Martín
 */

class MagicWeapon (name:String,atkpoints:Int,weight:Int,magicpoints:Int,owner:Option[Attributes]=None)
  extends AMagicWeapon(name,atkpoints,weight, magicpoints, owner) {

  Require.Stat(atkpoints,"Attack points not valid") in (0 to 150)
  Require.Stat(weight,"Weight not valid") in (0 to 150)
  Require.Stat(magicpoints,"Magic points not valid") in (0 to 350)
  if(owner.isDefined){
    throw new InvalidOwnerException("Weapon should not be initialized with an owner")
  }
}
