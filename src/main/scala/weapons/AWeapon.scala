package weapons

import attributes.Attributes
import characters.{Guerrero, MagoNegro, Ninja, Paladin}
import exceptions.NotMagicWeaponException

/**A  class for a weapon
 *
 *A weapon is defined by its name, atkpoints, weight and owner
 *
 * TO CONSIDER: as the weapon inherits the trait Weapons, it also has mana defined for it,
 * nevertheless, this attribute is defined as None, so it may not be considered for normal
 * weapons
 *
 * @tparam name the name of the weapon
 * @tparam atkpoints the attack points of the weapon
 * @tparam weight the weight of the weapon
 * @tparam owner the owner of the weapon
 *
 * @constructor creates a new weapon with a name(could be ""), attack points, weight and an owner
 *
 * @author Diego San Martin
 */

abstract class AWeapon(private var name:String, private var atkpoints:Int, private var weight:Int, private var owner:Option[Attributes]) extends TWeapons {

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
   * @example
   * val weapon = new Weapon("Sword",70,70)
   * println(s"Current owner is ${weapon.getOwner()}"= --> should print None
   * paladin.receiveWeapon(weapon) --> calls this method from Character class
   * println(s"Current owner is ${weapon.getOwner()}"= --> should print paladin
   * paladin.dropWeapon(0) ---> calls this method to drop first weapon in their inventory, in this case weapon
   * println(s"Current owner is ${weapon.getOwner()}"= --> should print None
   *
   */

  /**Method for changing the owner of a weapon
   *
   * Will only be called upon, when the weapon´s type matches those that the player can hold
   *
   * @param receiver the new owner of the weapon
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
     println(s"This weapon has already been named ${name}" )
     return
   }
   this.name = named
   println(s"This weapon has been named ${name}")
 }

  /**Method for checking whether or not a wepaon is magical or not
   * As this class is for weapons, that still dont extend to magical weapons, an exception is thrown
   *
   * @throws NotMagicWeaponException
   */

  override def checkifMagic(): Unit = {
    throw new NotMagicWeaponException("Cant cast a spell with a non magic weapon equipped")
  }

}
