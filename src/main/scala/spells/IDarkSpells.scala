package spells

import attributes.IMage
import enemy.EnemyAttributes
import weapons.IMagicWeapon

/**
 * Trait for dark spells, includes method for attacking enemies
 */

trait IDarkSpells {
  def throwFinalDarkAttack(user:IMage,victim:EnemyAttributes,magicWeapon:IMagicWeapon)


}
