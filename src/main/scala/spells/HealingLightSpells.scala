package spells

import attributes.{Attributes, IMage}

/**
 * Trait for light spells that heals
 */
trait HealingLightSpells {
  def finalHealSpell(user:IMage,victim:Attributes)
}
