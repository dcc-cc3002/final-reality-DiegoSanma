package enemigo

import attributes.{Attributes, Mage}
import entity.{AEntidad, Entidad}
import exceptions.damage.SameClassAttackException
import turnos.ProgramadorDeTurnos
import weapons.MagicWeapon

/**An abstract class that extends from EnemigoAttributes
 * Includes the attack parameter for enemies
 *
 */

abstract class AEnemigo(name:String,hp:Int,defense:Int,weight:Int,private var attack:Int)
  extends AEntidad(name,hp,defense,weight) with EnemigoAttributes {


  /**Getter for attack parameter of an enemy
   *
   * @return this.attack // the attack value of the enemy
   */
  override def getAttack(): Int = {
    this.attack
  }

  /** Method for attacking another entity
   * Bare in mind, that an enemy can also inflict damage to another enemy
   *
   * @param victim the entity to whom damage will be dealt
   */

  /**Getter for max action bar
   * In the case for enemies, as they dont hold weapons, their max Action Bar will ALWAYS be their weight
   *
   * @return this.weight
   */
  override def getMaxActionBar(): Int = {
    this.getWeight()
  }
  override def attack(victim: Entidad): Unit = {
    victim.takedamageEnemy(this)
  }

  /** Method for when an enemy is being attacked by another enemy
   *
   * @param agresor the enemy that is attacking
   *
   * @throws SameClassAttackException
   */

  override def takedamageEnemy(agresor: EnemigoAttributes): Unit = {
    throw new SameClassAttackException("Cant attack an enemy as an enemy")
  }

  /**Method for when a player is attacking an enemy
   *
   *  If the enemy´s defense is greater than the attack, no damage is done
   *  If the enemy´s health drops below 0, it is fixated to 0 and therefore has been defeated
   *
   * @param agresor the player attacking the enemy
   *
   */
  override def takedamagePlayer(agresor: Attributes): Unit = {
    var damage:Int = agresor.getActiveWeapon().get.getAtkPts() - this.getDefense()
    this.checkHealth(damage)
    }

  /** Method for when taking damage from a spell
   *
   * @param mage mage using the spell
   */
  override def takeSpellDamage(mage: Mage): Unit = {
    var damage:Int = mage.getActiveWeapon().get.asInstanceOf[MagicWeapon].getMagicpts()
    this.checkHealth(damage)
  }

  /**Adds the enemy to the turn scheduler
   *
   * @param scheduler the turn scheduler the enemy is being added to
   */
  override def addToTurns(scheduler: ProgramadorDeTurnos): Unit = {
    scheduler.addEnemy(this)
  }

  /**Method for removing an enemy from the turn scheduler
   *
   * @param scheduler the turn scheduler they are being removed from
   */
  override def removeFromTurns(scheduler: ProgramadorDeTurnos): Unit = {
    scheduler.removeEnemy(this)
  }

}
