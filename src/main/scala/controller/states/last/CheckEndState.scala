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
   * Also notifies use of how the game ended, bare in mind this is before they update the state to FinalState,
   * but that causes no issue as both functions are run almost simultaneously in the game
   * @param controller the game controller
   */

  override def handleInput(controller: GameController): Unit = {
    val result: Int = controller.checkIfFinished()
    if(result!=2){
      changedState = new FinalState
      controller.notifyEndGame(result)
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
