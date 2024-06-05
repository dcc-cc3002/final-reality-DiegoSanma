package controller.observers

import attributes.IMage
import entity.Entity
import spells.ISpells

/**Class for observer that will be updating health when an entity is being attacked in some way
 *
 */

class AttackObserver {
  /**Method for when our observer is notified that an entity wants to attack another
   *
   * @param attacker the entity attacking
   * @param receiver the entity being attacked
   */
  def updateAttack(attacker:Entity,receiver:Entity): Unit = {
    attacker.attack(receiver)
  }

  /**Method for when our observer is notified that a mage wants to cast a spell on another entity
   *
   * @param attacker the mage casting the spell
   * @param receiver the entity the spell is being cast on
   * @param spell the spell being casted
   */
  def updateSpellCast(attacker:IMage,receiver:Entity,spell:ISpells): Unit = {
    attacker.throwSpell(spell,receiver)
  }

}
