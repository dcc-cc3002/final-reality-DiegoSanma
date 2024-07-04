package gameStarter

import controller.GameController
import controller.states.last.FinalState

/**Class for starting a game
 *
 */
class StartGame {
  /**Method for running an active game
   *
   * While the game has not passed ot final State, the controller will continue to
   * handle inputs and update the game state
   *
   */
  def run(): Unit = {
    val controller: GameController = new GameController()
    while(!(controller.getState().hasFinished())){
      controller.handleInput()
      controller.update()
    }
  }
}
