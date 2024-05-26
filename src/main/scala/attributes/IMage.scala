package attributes

import entity.Entidad
import spells.IHechizo

trait IMage extends Character{
  def getMana(): Unit

  def throwSpell(spell:IHechizo)

  def throwDarkSpell(spell:HechizoOscuro,victim:Entidad)

  def throwLightSpell(spell:HechizoLuz,victim:Entidad)
}
