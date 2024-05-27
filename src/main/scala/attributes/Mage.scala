package attributes

import enemigo.{Enemigo, EnemigoAttributes}
import entity.{AEntidad, Entidad}
import exceptions.{AlreadyOwnedException, FullInventoryException, NotEnoughManaException, SameClassAttackException, UnaliveDamagedException}
import spells.IHechizo
import weapons.TWeapons

import scala.collection.mutable.ArrayBuffer

/**An abstract class for a mage that includes the methods used for both
 * types of mages
 *
 * The abstract class is defined using the characteristics from the trait Attributes
 *
 * @author Diego San Martin
 */

abstract class Mage(name:String,hp:Int,defense: Int, weight: Int,
                    private var mana:Int, inventory: ArrayBuffer[TWeapons],activeWeapon: Option[TWeapons])
  extends Character(name,hp,defense,weight,inventory, activeWeapon) with IMage {

  /**Getter for mana
   *
   * @return this.mana
   */
  override def getMana(): Int = {
    this.mana
  }

  /**Method for when a mage wants to use a spell
   * Does to main checks here, checks if the spell is being used on the right type of entity and
   * if a mage has a magic weapon equipped
   *
   * @param spell the spell the mage is trying to use
   * @param victim the victim of the spell
   */
  override def throwSpell(spell: IHechizo, victim: Entidad): Unit = {
    spell.friendlyFire(this,victim)
    if(getActiveWeapon().isEmpty) {
      return
    }
    getActiveWeapon().get.checkifMagic()
    if(victim.isAlive() == 0){
      throw new UnaliveDamagedException("Cant cast a spell on a dead entity")
    }
    spell.inflict(this,victim)
  }

  /**Method for when a Mage uses a spell and spends a certain amount of mana
   * If
   *
   * @param use amount of mana the mage is using
   */
  override def useMana(use: Int): Unit = {
    this.mana -= use
  }

  override def checkMana(amount: Int): Unit = {
    if(amount>this.mana){
      throw new NotEnoughManaException("Mage does not have enough mana to use that spell")
    }
  }


}
