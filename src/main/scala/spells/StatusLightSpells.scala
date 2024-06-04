package spells

import attributes.IMage
import characters.WhiteMage
import enemy.EnemyAttributes

/**
 * Trait for spells that inflict a status condition on an enemy
 */
trait StatusLightSpells {
    def finalStatusSpell(user:IMage,victim:EnemyAttributes)
}
