package controller.states

import controller.GameController

trait IGameState {
  def updateController(controller: GameController)

  def handleInput(controller:GameController)

  def updateFlow(controller:GameController)
}
