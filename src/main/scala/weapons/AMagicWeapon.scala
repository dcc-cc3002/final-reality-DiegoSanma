package weapons

import attributes.{Attributes, IMage}
import exceptions.initializing.{InvalidOwnerException, Require}

/**Abstract  class for a magic weapon
 *
 *A magic weapon is defined by its name, atkpoints, weight, magic points and owner
 *
 * @param name the name of the magic weapon
 * @param atkpoints the attack points of the magic weapon
 * @param weight the weight of the magic weapon
 * @param magicpoints the magic points of the magic weapon
 * @param owner the owner of the magic weapon
 *
 * @author Diego San Martin
 */
abstract class AMagicWeapon(name:String, atkpoints:Int, weight:Int, private var magicpoints:Int,owner:Option[Attributes])
  extends AWeapon(name,atkpoints,weight,owner) with IMagicWeapon{

  /** Checks whether a magic weapon was initialized with the correct parameters */
  Require.Stat(atkpoints,"Attack points not valid") in (0 to 150)
  Require.Stat(weight,"Weight not valid") in (0 to 150)
  Require.Stat(magicpoints,"Magic points not valid") in (0 to 350)
  if(owner.isDefined){
    throw new InvalidOwnerException("Weapon should not be initialized with an owner")
  }

  /**Getter for magic points of a magic Weapon
   *
   * @return this.magicpoints
   */
  def getMagicpts(): Int ={
    this.magicpoints
  }

  /**Method for checking if the weapon is magical
   *
   * As this class is specifically for magic weapons(despite extending from weapons, think of it
   * as an "evolution" of the normal weapons", this function throws no exception, it passes the check
   *
   */
  override def checkifMagic(): Unit = {
  }

}
