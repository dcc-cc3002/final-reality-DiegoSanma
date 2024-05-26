package attributes

import entity.Entidad
import spells.{HechizoLuz, HechizoOscuro, IHechizo}

trait IMage{
  def getMana(): Int

  def throwSpell(spell:IHechizo,victim:Entidad)

  def throwDarkSpell(spell:HechizoOscuro,victim:Entidad)

  def throwLightSpell(spell:HechizoLuz,victim:Entidad)

  def useMana(use:Int): Unit

  def checkMana(amount:Int): Unit
}
