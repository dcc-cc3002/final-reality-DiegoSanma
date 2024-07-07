package controller.states.player

import attributes.Attributes
import controller.GameController
import controller.states.initial.TurnCalculatingState
import controller.states.last.CheckEndState
import controller.states.{AGameState, IGameState}
import enemy.EnemyAttributes
import exceptions.damage.UnaliveDamagedException
import exceptions.weaponexceptions.NoWeaponException

/**Class for state where an enemy is receiving damage from a character´s attack
 *
 * @param selected character attacking
 * @param victim enemy being attacked
 */
class ReceivingDamageState(selected:Attributes,victim:EnemyAttributes) extends AGameState {
  /** Parameter that holds the next state */
  private var changedState: Option[IGameState] = None

  /**Method for trying to attack enemy
   * If character has no weapon, they are sent to ChangeWeaponState
   * If the enemy they are trying to attack is dead, they are sent back to CharacterAttackState
   *
   * @param controller the game controller
   */
  override def handleInput(controller: GameController): Unit = {
    try{
      selected.attack(victim)
      if(victim.isAlive()==0){
        controller.getModel().removeEnemy(victim)
        while(controller.getModel().getTurnsLine().contains(victim)){
          val index: Int = controller.getModel().getTurnsLine().indexOf(victim)
          controller.getModel().getTurnsLine().remove(index)
        }
      }
      changedState = Some(new CheckEndState)
    }
    catch {
      case e: NoWeaponException => changedState = Some(new ChangeWeaponState(selected))
      case e1: UnaliveDamagedException => changedState = Some(new CharacterAttackState(selected))
    }
  }

  /**Method for updating the controller´s state
   *
   * @param controller the game controller
   */
  override def updateController(controller: GameController): Unit = {
    controller.changeState(changedState.get)
  }
}
