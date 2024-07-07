package controller.states.last

import controller.GameController
import controller.states.AGameState

/**Class for final state of the game
 *
 * Works so i can know effectively that a gama has ended
 *
 */
class FinalState extends AGameState{

  /**Method for checking if a game in this state has finished
   * Since it is in it´s final state, it returns true
   *
   *  @return true
   */
  override def hasFinished(): Boolean = {
    true
  }

}
