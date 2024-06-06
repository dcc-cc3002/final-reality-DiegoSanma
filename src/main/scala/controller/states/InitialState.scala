package controller.states
import controller.GameController

class InitialState extends AGameState {

  override def updateController(controller: GameController): Unit = {
    controller.changeState(new TurnCalculatingState())
  }



}
