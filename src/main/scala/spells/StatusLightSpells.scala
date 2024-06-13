package spells

import attributes.IMage
import characters.WhiteMage
import enemy.EnemyAttributes
import weapons.IMagicWeapon

/**
 * Trait for spells that inflict a status condition on an enemy
 */
trait StatusLightSpells {
    def finalStatusSpell(user:IMage,victim:EnemyAttributes,magicWeapon:IMagicWeapon)
}
