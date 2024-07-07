package controller.states
import controller.GameController

abstract class AGameState extends IGameState {
  override def handleInput(controller: GameController): Unit = {}

  override def updateController(controller: GameController): Unit = {}

  /**Method for chekcing if the state in which we are is the last state
   * In other words, if the game should fininshed
   *
   * @return false
   */
  override def hasFinished(): Boolean = {
    false
  }
}
