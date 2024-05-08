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
  override def takedamage(agresor: Entidad): Unit = {
    if(agresor.isInstanceOf[Enemigo]){
      if(agresor.asInstanceOf[Enemigo].getAttack()<this.getDefense()){
      }
      else {
        this.hp -= agresor.asInstanceOf[Enemigo].getAttack() - this.getDefense()
      }
    }
    else {
      if(agresor.asInstanceOf[Attributes].getActiveWeapon().get.getAtkPts()<this.getDefense()) {
      }
      else {
        this.hp -= agresor.asInstanceOf[Attributes].getActiveWeapon().get.getAtkPts() - this.getDefense()
      }
    }
    if(this.hp<0){
      this.hp = 0
    }
  }

}
