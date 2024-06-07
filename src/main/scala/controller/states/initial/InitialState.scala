package controller.states.initial

import controller.GameController
import controller.states.AGameState

class InitialState extends AGameState {

  override def updateController(controller: GameController): Unit = {
    controller.changeState(new TurnCalculatingState())
  }



}
