package controller.states.enemies

import controller.GameController
import controller.states.initial.TurnCalculatingState
import controller.states.last.CheckEndState
import controller.states.{AGameState, IGameState}
import enemy.EnemyAttributes
import exceptions.damage.UnaliveDamagedException

class EnemyAttackState(selected:EnemyAttributes) extends AGameState {
  private var changedState: Option[IGameState]= None
  override def handleInput(controller: GameController): Unit = {
    var choice: Int = controller.getEnemyChoice(controller.getModel().getPlayers().length)
    try{
      selected.attack(controller.getModel().getPlayers()(choice))
      changedState = Some(new CheckEndState)
    }
    catch {
      case e: UnaliveDamagedException => changedState = None
    }
  }

  override def updateController(controller: GameController): Unit = {
    if(changedState.isDefined){
      controller.changeState(changedState.get)
    }
  }

}
