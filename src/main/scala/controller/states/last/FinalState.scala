package controller.states.last

import controller.GameController
import controller.states.AGameState

/**Class for final state of the game
 *
 */
class FinalState extends AGameState{
  /**Method for telling that the game has ended
   *
   * @param controller the game controller
   */
  override def updateController(controller: GameController): Unit = {
    controller.notifyEndGame()
  }

}
