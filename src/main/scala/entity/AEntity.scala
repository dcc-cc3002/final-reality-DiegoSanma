package entity

import attributes.Attributes
import enemy.Enemy

/**Absract class that extends from Entity */
abstract class AEntity(private var name:String, private var hp:Int, private var defense: Int, private var weight: Int) extends Entity {

  /** Constant parameter that holds the max health of a character */
  private val maxHealth: Int = hp

  /** Bar starts at 0 for all entities*/
  var actionBar: Int = 0

  /**Getter for name
   *
   * @return this.name
   */
  override def getName(): String ={
      this.name
    }

  /**Getter for Hp
   *
   * @return this.hp
   */
    override def getHp(): Int = {
      this.hp
    }

  /**Getter for defense
   *
   * @return this.defense
   */
    override def getDefense(): Int = {
      this.defense
    }

  /**Getter for weight
   *
   * @return this.weight
   */
    override def getWeight(): Int = {
      this.weight
    }

  /**Getter for action bar
   *
   * @return this.actionBar
   */
  override def getActionBar(): Int = {
    this.actionBar
  }

  /** Getter for max Health if a Character
   *
   * @return this.maxHealth
   */


  override def getMaxHealth(): Int = {
    this.maxHealth
  }

  /**Getter for Max action bar
   *
   * @return this.maxActionBar
   */

  /**Method for adding a constant value into the action bar meter
   *
   * @param k the amount being added
   */
  override def addToActionBar(k: Int): Unit = {
    this.actionBar += k
  }

  /**Method for inflicting damage on an entity
   *
   * Damage is the amount that their active hp will be decreased
   * If their health goes below 0, it is fixated to that value
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

  /**Method for checking if an entity is alive or not
   *
   * @return 1 if they are alive
   *         0 if not
   */

  override def isAlive(): Int = {
    if(this.hp==0){
      return 0
    }
    return 1
  }

  /**Method for when an entity is being healed by a certain amount
   *
   * @param amountPercentage the percentage of health that is being healed
   */

  override def heal(amountPercentage:Double): Unit = {
    val healing: Int = (amountPercentage*this.getMaxHealth()).toInt
    this.maxHeal(healing,getMaxHealth())
  }
}
