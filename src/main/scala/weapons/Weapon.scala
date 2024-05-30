package weapons

import attributes.Attributes
import exceptions.initializing.{InvalidOwnerException, Require}

/**Class for a weapon
 * A weapon is defined by its name, attack points, weight and current owner
 *
 * A weapon is always created without an owner in the beginning, later one can be added
 * Bare in mind that having an owner does not necessarily imply that said weapon is equipped/ being used by that owner
 *
 * @param name the name of the weapon
 * @param atkpoints the attack points of the weapon
 * @param weight the weight of the weapon
 * @param owner the current owner of the weapon
 * @author Diego San Martín
 */

abstract class Weapon(name:String,atkpoints:Int,weight:Int,owner:Option[Attributes])
  extends AWeapon(name,atkpoints,weight,owner) {

  /** Checks whether a magic weapon was initialized with the correct parameters */
  Require.Stat(atkpoints,"Attack points not valid") in (0 to 350)
  Require.Stat(weight,"Weight not valid") in (0 to 150)
  if(owner.isDefined){
    throw new InvalidOwnerException("Weapon should not be initialized with an owner")
  }

}
