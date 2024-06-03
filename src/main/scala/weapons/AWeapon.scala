package weapons

import attributes.Attributes
import characters.{BlackMage, Ninja, Paladin, Warrior}
import exceptions.initializing.{InvalidOwnerException, Require}
import exceptions.weaponexceptions.{AlreadyNamedException, NotMagicWeaponException}

/**Abstract  class for a weapon
 *
 *A weapon is defined by its name, atkpoints, weight and owner
 *
 *
 * @tparam name the name of the weapon
 * @tparam atkpoints the attack points of the weapon
 * @tparam weight the weight of the weapon
 * @tparam owner the owner of the weapon
 * @author Diego San Martin
 */

abstract class AWeapon(private var name:String, private var atkpoints:Int, private var weight:Int, private var owner:Option[Attributes]) extends TWeapons {

  /** Checks whether a magic weapon was initialized with the correct parameters */
  Require.Stat(atkpoints,"Attack points not valid") in (0 to 350)
  Require.Stat(weight,"Weight not valid") in (0 to 150)
  if(owner.isDefined){
    throw new InvalidOwnerException("Weapon should not be initialized with an owner")
  }

  /**A getter for the weapon´s name
   *
   * @return this.name
   */

  override def getWeaponName(): String = {
    this.name
  }

  /**A getter for the weapon´s attack points
   *
   * @return this.atkpoints
   */
  override def getAtkPts(): Int = {
    this.atkpoints
  }

  /** A getter for the weapon´s weight
   *
   * @return this.weight
   */
  override def getWeaponWeight(): Int = {
    this.weight
  }

  /** A getter for the weapon´s owner
   *
   * @return this.owner
   */
  override def getOwner(): Option[Attributes] = {
    this.owner
  }

  /**Method for changing the owner of the weapon
   *
   * Is called when all previous checks are made for whether they can be the owner of the weapon
   * These checks include, space in the player´s inventory, no current owner of the weapon and
   * that the weapon type matches those that said player´s class can hold
   *
   * @param receiver the new owner of the weapon
   */

  override def changeOwner(receiver: Attributes): Unit = {
    this.owner = Some(receiver)
  }


  /** Method for leaving an owner
   *
   * Does not take into account if there is an owner or not, leaves it as a None in both cases
   *
   * This method should not be called directly, but instead from the character/mage who is going to drop this weapon
   *
   */

  override def leaveOwner(): Unit = {
      this.owner = None
  }
  /**Renames the weapon to the name(named) of choice
   *
   * If the weapon already has a name assigned to it, that one is permanent, and cannot be changed
   *
   * @example
   * val weapon = new Weapon("",70,70)
   * weapon.rename("Excalibur")
   * println(s"The current name of the weapon is ${weapon.getName()}")---> should print Excalibur
   */

 override def rename(named: String): Unit ={
   if (this.name != ""){
     throw new AlreadyNamedException("Weapon already has a name")
   }
   this.name = named
   println(s"This weapon has been named ${name}")
 }

  /**Method for checking whether or not a weapon is magical or not
   * As this class is for weapons, that still dont extend to magical weapons, an exception is thrown
   *
   * @throws NotMagicWeaponException
   */

  override def checkifMagic(): Unit = {
    throw new NotMagicWeaponException("Cant cast a spell with a non magic weapon equipped")
  }

}
