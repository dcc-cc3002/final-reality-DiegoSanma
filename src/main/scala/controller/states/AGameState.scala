package controller.states
import controller.GameController

abstract class AGameState extends IGameState {
  override def handleInput(controller: GameController): Unit = {}

  override def updateController(controller: GameController): Unit = {}

  override def updateFlow(controller: GameController): Unit = {}
}
