package spells
import attributes.Mage
import entity.Entidad

abstract class HechizoOscuro extends IHechizo {
  override def inflict(user: Mage, victim: Entidad): Unit = {
    user.throwDarkSpell(this,victim)
  }
}
