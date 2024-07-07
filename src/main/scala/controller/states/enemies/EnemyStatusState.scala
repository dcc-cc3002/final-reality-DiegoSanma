package controller.states.enemies

import controller.GameController
import controller.states.initial.TurnCalculatingState
import controller.states.last.CheckEndState
import controller.states.{AGameState, IGameState}
import enemy.EnemyAttributes
import status.IStatusEffect

/**Class for state that checks whether or not an enemy has a status condition
 *
 * @param selected enemy whose turn it is
 *
 */
class EnemyStatusState(selected:EnemyAttributes) extends  AGameState{
  /** Parameter for next state, if enemy lives/is not paralyzed, it passes to attackState */
  private var changedState: IGameState = new EnemyAttackState(selected)

  /**Method for handling any status condition the enemy may have
   * Makes sure to skip turn if necessary or to identify if the status effect killed the enemy
   *
   * @param controller the game controller
   */
  override def handleInput(controller: GameController): Unit = {
    val status: Option[IStatusEffect] = selected.getStatus()
    if(status.isDefined){
      val skip:Int = status.get.doEffect()
      if(selected.isAlive()==0){
        controller.getModel().removeEnemy(selected)
        while(controller.getModel().getTurnsLine().contains(selected)) {
          val index: Int = controller.getModel().getTurnsLine().indexOf(selected)
          controller.getModel().getTurnsLine().remove(index)
        }
        changedState = new CheckEndState
      }
      if(skip ==1){
        changedState = new TurnCalculatingState
      }
    }
  }

  /**Method for updating the controller´s state
   *
   * @param controller the game controller
   */

  override def updateController(controller: GameController): Unit = {
    controller.changeState(changedState)
  }
}
