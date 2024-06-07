package controller.states.player

import attributes.Attributes
import controller.GameController
import controller.states.{AGameState, IGameState}
import enemy.EnemyAttributes

import scala.collection.mutable.ArrayBuffer

class CharacterAttackState(selected:Attributes) extends AGameState {
  private var changedState: Option[IGameState] = None

  override def handleInput(controller: GameController): Unit = {
    val enemies: ArrayBuffer[EnemyAttributes] = controller.getModel().getEnemies()
    val choice: Int = controller.getNumericalInput()
    if(choice<1 || choice>enemies.length+1){
      controller.notifyInvalidOption(choice)
    }
    else{
      changedState = Some(new ReceivingDamageState(selected,enemies(choice-1)))
    }
  }

  override def updateController(controller: GameController): Unit = {
    if(changedState.isDefined) {
      controller.changeState(changedState.get)
    }
  }
}
