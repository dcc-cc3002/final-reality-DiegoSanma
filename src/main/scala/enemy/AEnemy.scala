package enemy

import attributes.{Attributes, IMage, Mage}
import entity.{AEntity, Entity}
import exceptions.damage.{FriendlyFireException, SameClassAttackException, UnaliveDamagedException}
import spells.{HealingLightSpells, IDarkSpells, ISpells, StatusLightSpells}
import turnscheduler.TurnScheduler
import weapons.{AMagicWeapon, IMagicWeapon}

/**An abstract class that extends from EnemyAttributes
 *
 * Includes the attack parameter for enemies, as well as all the parameters defined for an entity
 *
 */

abstract class AEnemy(name:String, hp:Int, defense:Int, weight:Int, private var attack:Int)
  extends AEntity(name,hp,defense,weight) with EnemyAttributes {


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

  /**Method for when an enemy is attacking an entity
   *
   * Calls the function in the character/enemy being attacked, which is in charge of checking
   * if the enemy is attacking a foe, and not an ally
   *
   * @param victim the entity being attacked by the enemy
   *
   * @throws UnaliveDamagedException if entity being attacked is dead
   */
  override def attack(victim: Entity): Unit = {
    if(victim.isAlive()==0){
      throw new UnaliveDamagedException("Enemy is attacking a dead entity!")
    }
    victim.takedamageEnemy(this)
  }

  /** Method for when an enemy is being attacked by another enemy
   *
   * @param agresor the enemy that is attacking
   *
   * @throws SameClassAttackException
   */

  override def takedamageEnemy(agresor: EnemyAttributes): Unit = {
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
   * The damage is equivalent to the amount of magic points the weapon has
   *
   * @param mage mage using the spell
   * @param magicWeapon the magic weapon the mage is holding
   */
  override def takeSpellDamage(mage: IMage,magicWeapon: IMagicWeapon): Unit = {
    var damage: Int = magicWeapon.getMagicpts()
    this.checkHealth(damage)
  }

  /**Method for checking whether a dark spell can be inflicted on the enemy
   * Since it can, it calls the function in spell to effectively inflict the dark spell´s damage
   *
   * @param user the mage casting the dark spell
   * @param spell the dark spell being casted
   * @param magicWeapon the magic weapon the mage is holding
   */
  override def checkDarkInflictSpell(user: IMage, spell: IDarkSpells,magicWeapon: IMagicWeapon): Unit = {
    spell.throwFinalDarkAttack(user,this,magicWeapon)
  }

  /**Method for checking if an enemy can be healed by a mage´s spell
   *
   * @param user the mage using the spell
   * @param spell the healing spell being used
   *
   * @throws FriendlyFireException as an enemy cannot be healed by a spell
   */
  override def checkLightHealSpell(user: IMage, spell: HealingLightSpells): Unit = {
    throw new FriendlyFireException("Can´t heal an enemy!")
  }

  /**Method for checking if an enemy can be inflicted a status condition by a mage´s spell
   *
   * @param user the mage using the spell
   * @param spell the healing spell being used
   *
   */
  override def checkLightStatusSpell(user: IMage, spell: StatusLightSpells): Unit = {
    spell.finalStatusSpell(user,this)
  }

  /**Adds the enemy to the turn scheduler
   *
   * @param scheduler the turn scheduler the enemy is being added to
   */
  override def addToTurns(scheduler: TurnScheduler): Unit = {
    scheduler.addEnemy(this)
  }

  /**Method for removing an enemy from the turn scheduler
   *
   * @param scheduler the turn scheduler they are being removed from
   */
  override def removeFromTurns(scheduler: TurnScheduler): Unit = {
    scheduler.removeEnemy(this)
  }

}
