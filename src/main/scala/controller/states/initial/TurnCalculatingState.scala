package controller.states.initial

import controller.GameController
import controller.states.AGameState
import entity.Entity

/**Class for turn calculating state
 *
 */
class TurnCalculatingState extends AGameState {
  /** Parameter for keeping the entity whose turn it is next */
  private var selected: Option[Entity] = None

  /**Method for increasing action bar of everyone on the game, and checking who has the next turn
   *
   * @param controller the game controller
   */
  override def handleInput(controller: GameController): Unit = {
      controller.getModel().continue(30)
      controller.getModel().checkTurn()
      selected = controller.getModel().nextTurn()
  }

  /**Method for updating the controller´s state, only does so if it is someone´s turn
   *
   * @param controller the game controller
   */

  override def updateController(controller: GameController): Unit = {
    if(selected.isDefined){
      controller.changeState(new AttackState(selected.get))
    }
  }
}
