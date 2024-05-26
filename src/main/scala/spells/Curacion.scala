package spells
import attributes.{Attributes, Mage}
import enemigo.EnemigoAttributes
import entity.Entidad
import exceptions.FriendlyFireException

class Curacion extends HechizoLuz {
  override def finalInflict(user: Mage, victim: Entidad): Unit = {

  }
  override def friendlyFire(user: Mage, victim: Entidad): Unit = {
    if(victim.isInstanceOf[EnemigoAttributes]){
      throw new FriendlyFireException("Cant heal an enemy")
    }
  }
}
