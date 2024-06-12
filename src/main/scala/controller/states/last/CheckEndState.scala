package controller.states.last

import controller.GameController
import controller.states.initial.TurnCalculatingState
import controller.states.{AGameState, IGameState}

class CheckEndState extends AGameState{
  private var changedState: IGameState =  new TurnCalculatingState

  override def handleInput(controller: GameController): Unit = {
    if(controller.checkIfFinished()){
      changedState = new FinalState
    }
  }

  override def updateController(controller: GameController): Unit = {
    controller.changeState(changedState)
  }

}
