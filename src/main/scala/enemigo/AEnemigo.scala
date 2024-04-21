package enemigo

import entity.AEntidad

/**An abstract class that extends form Enemigo Attributes
 * Includes the attack parameter for enemies
 */

abstract class AEnemigo(name:String,hp:Int,defense:Int,weight:Int,private var attack:Int)
  extends AEntidad(name,hp,defense,weight) with EnemigoAttributes {

  override def getAttack(): Int = {
    this.attack
  }

}
