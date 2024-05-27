package entity

import attributes.Attributes
import enemigo.Enemigo

/**Absract class that extends from Entidad
 *
 *
 */
abstract class AEntidad(private var name:String,private var hp:Int,private var defense: Int,private var weight: Int) extends Entidad {
    override def getName(): String ={
      this.name
    }

    override def getHp(): Int = {
      this.hp
    }

    override def getDefense(): Int = {
      this.defense
    }

    override def getWeight(): Int = {
      this.weight
    }

  /**Method for inflicting damage on an entity
   *
   * Damage is the amount that their active hp will be decreased
   * If their healñth goes below 0, it is fixated to that value
   *
   * @param damage the amount of damage being done
   */

  override protected def checkHealth(damage:Int): Unit = {
    this.hp -= damage
    if(this.hp<0){
      this.hp = 0
    }
  }

  /**Method for healing an entity
   *
   * @param healing the amount the entity is being healed
   * @param maxHealth the max health an entity can reach
   */
  override protected def maxHeal(healing:Int,maxHealth: Int): Unit = {
    this.hp += healing
    if(this.hp>maxHealth){
      this.hp = maxHealth
    }
  }

  override protected def isAlive(): Int = {
    if(this.hp<0){
      return 0
    }
    return 1
  }

}
