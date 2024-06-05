package attributes

import entity.Entity
import spells.{DarkSpells, ISpells, LightSpells}
import weapons.IMagicWeapon

trait IMage{
  def getMana(): Int

  def throwSpell(spell:ISpells, victim:Entity)

  def throwDarkSpell(spell:DarkSpells, victim:Entity,magicWeapon: IMagicWeapon)

  def throwLightSpell(spell:LightSpells, victim:Entity,magicWeapon:IMagicWeapon)

  def useMana(use:Int): Unit

  def checkMana(amount:Int): Unit
}
