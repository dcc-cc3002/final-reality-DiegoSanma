package controller.states
import controller.GameController
import entity.Entity

class TurnCalculatingState extends AGameState {
  private var selected: Option[Entity] = null
  override def handleInput(controller: GameController): Unit = {
      controller.getModel().continue(30)
      controller.getModel().checkTurn()
      selected = controller.getModel().nextTurn()
  }

  override def updateController(controller: GameController): Unit = {
    if(selected.isDefined){
      controller.changeState(new AttackState(selected.get))
    }
  }
}
