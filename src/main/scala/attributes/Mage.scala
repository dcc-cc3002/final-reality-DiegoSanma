package attributes

import enemy.{Enemy, EnemyAttributes}
import entity.{AEntity, Entity}
import exceptions.damage.{NotEnoughManaException, SameClassAttackException, UnaliveDamagedException}
import exceptions.weaponexceptions.{AlreadyOwnedException, FullInventoryException, NoWeaponException}
import spells.ISpells
import weapons.{IMagicWeapon, TWeapons}

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
  override def throwSpell(spell: ISpells, victim: Entity): Unit = {
    if(getActiveWeapon().isEmpty) {
      throw new NoWeaponException("Cant throw spell, you have no weapon equipped!")
    }
    var magicWeapon: IMagicWeapon = getActiveWeapon().get.checkifMagic()
    if(victim.isAlive() == 0){
      throw new UnaliveDamagedException("Cant cast a spell on a dead entity")
    }
    spell.inflict(this,victim,magicWeapon)
  }

  /**Method for when a Mage uses a spell and spends a certain amount of mana
   *
   * @param use amount of mana the mage is using
   */
  override def useMana(use: Int): Unit = {
    this.mana -= use
  }

  /**Method for checking whether or not a mage has enough mana to cast a spell
   *
   * @param amount the amount of mana the spell that is being casted costs
   *
   * @throws NotEnoughManaException if the mage does not have enough mana
   */

  override def checkMana(amount: Int): Unit = {
    if(amount>this.mana){
      throw new NotEnoughManaException("Mage does not have enough mana to use that spell")
    }
  }


}
