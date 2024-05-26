package spells
import attributes.{Attributes, Mage}
import entity.Entidad
import exceptions.FriendlyFireException

abstract class HechizoOscuro extends IHechizo {
  override def inflict(user: Mage, victim: Entidad): Unit = {
    user.throwDarkSpell(this,victim)
  }
  override def friendlyFire(user: Mage, victim: Entidad): Unit = {
    if (victim.isInstanceOf[Attributes]) {
      throw new FriendlyFireException("Cant inflict damage/effect to an ally")
    }
  }
}
