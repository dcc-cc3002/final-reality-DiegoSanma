package controller.states.player

import attributes.Attributes
import controller.GameController
import controller.states.{AGameState, IGameState}
import enemy.EnemyAttributes

import scala.collection.mutable.ArrayBuffer

/**Class for state where character is choosing what enemy to attack
 *
 * @param selected the character whose turn it is
 */
class CharacterAttackState(selected:Attributes) extends AGameState {
  /** Parameter that holds the next state */
  private var changedState: Option[IGameState] = None

  /**Method for handling the user´s choice of what enemy to attack
   *
   * @param controller the game controller
   */
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

  /**Method for updating controller´s state, if necessary
   *
   * @param controller the game controller
   */
  override def updateController(controller: GameController): Unit = {
    if(changedState.isDefined) {
      controller.changeState(changedState.get)
    }
  }
}
