package controller.states.last

import controller.GameController
import controller.states.initial.TurnCalculatingState
import controller.states.{AGameState, IGameState}

/**Class for checking the end of the game
 * State for checking if either the enemies or players were defeated
 */
class CheckEndState extends AGameState{
  /** Parameter for new state to be applied, begins as TurnCalculatingState, assuming game has not ended */
  private var changedState: IGameState =  new TurnCalculatingState

  /**Method for checking if the game has finished
   * If it has, changes state to FinalState
   *
   * @param controller the game controller
   */

  override def handleInput(controller: GameController): Unit = {
    if(controller.checkIfFinished()){
      changedState = new FinalState
    }
  }

  /**Method for updating controller´s state
   *
   * @param controller the game controller
   */
  override def updateController(controller: GameController): Unit = {
    controller.changeState(changedState)
  }

}
