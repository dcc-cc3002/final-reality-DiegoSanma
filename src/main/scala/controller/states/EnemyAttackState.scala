package controller.states

import controller.GameController
import enemy.EnemyAttributes
import exceptions.damage.UnaliveDamagedException

import scala.util.Random

class EnemyAttackState(selected:EnemyAttributes) extends AGameState {
  private var changedState: Option[IGameState]= None
  override def handleInput(controller: GameController): Unit = {
    var choice: Int = new Random().nextInt(2)
    while(choice>controller.getModel().getPlayers()){
      choice = new Random().nextInt(2)
    }
    try{
      selected.attack(controller.getModel().getPlayers()(choice))
      changedState = Some(new TurnCalculatingState())
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
