package attributes

import entity.Entity
import spells.{LightSpells, DarkSpells, ISpells}

trait IMage{
  def getMana(): Int

  def throwSpell(spell:ISpells, victim:Entity)

  def throwDarkSpell(spell:DarkSpells, victim:Entity)

  def throwLightSpell(spell:LightSpells, victim:Entity)

  def useMana(use:Int): Unit

  def checkMana(amount:Int): Unit
}
