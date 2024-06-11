package controller.states.enemies

import controller.GameController
import controller.states.initial.TurnCalculatingState
import controller.states.{AGameState, IGameState}
import enemy.EnemyAttributes
import status.IStatusEffect

/**Class for state that checks whether or not an enemy has a status condition
 *
 */
class EnemyStatusState(selected:EnemyAttributes) extends  AGameState{
  private var changedState: IGameState = new EnemyAttackState(selected)

  override def handleInput(controller: GameController): Unit = {
    val status: Option[IStatusEffect] = selected.getStatus()
    if(status.isDefined){
      status.get.doEffect()
      if(selected.isAlive()==0){
        changedState = new TurnCalculatingState
      }
    }
  }

  override def updateController(controller: GameController): Unit = {
    controller.changeState(changedState)
  }
}
