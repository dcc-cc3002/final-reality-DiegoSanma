package spells
import attributes.Mage
import entity.Entidad

abstract class HechizoLuz extends IHechizo {
  override def inflict(user:Mage,victim: Entidad): Unit = {
    user.throwLightSpell(this,victim)
  }
}
