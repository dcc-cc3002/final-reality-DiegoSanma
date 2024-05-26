package spells
import attributes.{Attributes, Mage}
import entity.Entidad
import exceptions.FriendlyFireException

class Veneno extends HechizoLuz {
  override def finalInflict(user: Mage, victim: Entidad): Unit = {

  }

  override def friendlyFire(user: Mage, victim: Entidad): Unit = {
    if(victim.isInstanceOf[Attributes]){
      throw new FriendlyFireException("Cant poison an ally")
    }
  }
}
