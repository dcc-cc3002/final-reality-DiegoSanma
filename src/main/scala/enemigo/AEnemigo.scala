package enemigo

import attributes.Attributes
import entity.{AEntidad, Entidad}

/**An abstract class that extends form Enemigo Attributes
 * Includes the attack parameter for enemies
 */

abstract class AEnemigo(name:String,hp:Int,defense:Int,weight:Int,private var attack:Int)
  extends AEntidad(name,hp,defense,weight) with EnemigoAttributes {

  override def getAttack(): Int = {
    this.attack
  }

  override def attack(victim: Entidad): Unit = {
    victim.takedamage(this)
  }

  override def takedamage(agresor: Entidad): Unit = {
    if(agresor.isInstanceOf[Enemigo]){
      this.hp -= agresor.asInstanceOf[Enemigo].getAttack()-this.getDefense()
    }
    else {
      println(s"Be careful, you´ve just attacked your friend!")
      this.hp -= agresor.asInstanceOf[Attributes].getWeapon().get.getAtkPts()-this.getDefense()
    }
  }

}
