package controller.states.player

import attributes.{Attributes, IMage}
import controller.GameController
import controller.states.{AGameState, IGameState}
import exceptions.mage.NotMageException

/**Class for when a character has to choose what to do in their turn
 *
 * @param selected character whose turn it is
 */
class CharacterChoiceState (selected:Attributes) extends AGameState {
  /** Parameter that hold the next state */
  private var stateChoice: Option[IGameState] = None

  /**Method for handling the choice the user will choose to make during their turn
   * Can choose to change their weapons, attack an enemy or cast a spell if they are a mage
   *
   * @param controller the game controller
   */
  override def handleInput(controller: GameController): Unit = {
    val choice: Int = controller.getNumericalInput()
    choice match {
      case 1 => stateChoice = Some(new ChangeWeaponState(selected))
      case 2 => stateChoice = Some(new CharacterAttackState(selected))
      case 3 => this.checkIfMage(selected,controller)
      case _ => controller.notifyInvalidOption(choice)
    }
  }

  /**Method for updating controller´s state, if necessary
   *
   * @param controller the game controller
   */
  override def updateController(controller: GameController): Unit = {
    if(stateChoice.isDefined){
      controller.changeState(stateChoice.get)
    }
  }

  private def checkIfMage(selected: Attributes,controller: GameController): Unit = {
    try{
      val mage: IMage = selected.seeIfMage()
      stateChoice = Some(new MageSpellState(mage,selected))

    }
    catch {
      case e: NotMageException => controller.notifyInvalidOption(3)
    }
  }

}
