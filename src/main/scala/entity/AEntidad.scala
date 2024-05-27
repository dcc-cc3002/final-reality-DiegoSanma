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

  override protected def checkHealth(damage:Int): Unit = {
    this.hp -= damage
    if(this.hp<0){
      this.hp = 0
    }
  }

  override protected def maxHeal(healing:Int,maxHealth: Int): Unit = {
    this.hp += healing
    if(this.hp>maxHealth){
      this.hp = maxHealth
    }
  }

}
