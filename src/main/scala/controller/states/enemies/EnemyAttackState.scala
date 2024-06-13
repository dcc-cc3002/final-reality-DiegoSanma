package controller.states.enemies

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
    var choice: Int = controller.getEnemyChoice(controller.getModel().getPlayers().length)
    try{
      selected.attack(controller.getModel().getPlayers()(choice))
      changedState = Some(new CheckEndState)
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
