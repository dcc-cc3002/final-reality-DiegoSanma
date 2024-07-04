package controller.states.enemies

import attributes.Attributes
import controller.GameController
import controller.states.initial.TurnCalculatingState
import controller.states.last.CheckEndState
import controller.states.{AGameState, IGameState}
import enemy.EnemyAttributes
import exceptions.damage.UnaliveDamagedException

/**Class for state for when enemies are choosing who to attack
 *
 * @param selected enemy whose turn it is
 */
class EnemyAttackState(selected:EnemyAttributes) extends AGameState {
  /** Parameter for checking if state must be changed */
  private var changedState: Option[IGameState]= None

  /**Method for handling choice made by enemy on who to attack
   * If enemy tries to attack a dead player, changedState remains None
   *
   * @param controller the game controller
   *
   */
  override def handleInput(controller: GameController): Unit = {
    val choice: Int = controller.getEnemyChoice(controller.getModel().getPlayers().length)
    val victim: Option[Attributes] = controller.getAllPlayers().getMembers()(choice)
    try {
      if (victim.isDefined) {
        selected.attack(victim.get)
        if (victim.get.isAlive() == 0) {
          controller.getModel().removeCharacter(victim.get)
          while (controller.getModel().getTurnsLine().contains(victim.get)) {
            val index: Int = controller.getModel().getTurnsLine().indexOf(victim.get)
            controller.getModel().getTurnsLine().remove(index)
          }
        }
        changedState = Some(new CheckEndState)
      }
    }
    catch {
      case e: UnaliveDamagedException => changedState = None
    }
  }

  /**Method for updating controller´s state if necessary
   *
   * @param controller the game controller
   */
  override def updateController(controller: GameController): Unit = {
    if(changedState.isDefined){
      controller.changeState(changedState.get)
    }
  }

}
