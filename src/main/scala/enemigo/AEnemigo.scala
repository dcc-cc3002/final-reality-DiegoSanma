package enemigo

import attributes.Attributes
import entity.{AEntidad, Entidad}

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

  /**Method for attacking another entity
   * Bare in mind, that an enemy can also inflict damage to another enemy
   *
   * @param victim the entity to whom damage will be dealt
   */

  override def attack(victim: Entidad): Unit = {
    victim.takedamageEnemy(this)
  }

  override def takedamageEnemy(agresor: EnemigoAttributes): Unit = {
    throw new SameClassAttackException
  }

}
