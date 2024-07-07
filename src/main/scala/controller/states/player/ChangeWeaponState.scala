package controller.states.player

import attributes.Attributes
import controller.GameController
import controller.states.{AGameState, IGameState}

/**Class for state where character is changing his weapons
 *
 * @param selected the character changing his weapons
 */
class ChangeWeaponState(selected:Attributes) extends AGameState {
  /** Parameter that holds the next state */
  private var changedState: Option[IGameState] = None

  /**Method for handling the user´s choice
   * Can choose to add a weapon to their inventory, drop a weapon, equip a weapon, rename their active weapon
   * or leave the current state
   *
   * @param controller the game controller
   */
  override def handleInput(controller: GameController): Unit = {
    val choice: Int = controller.getNumericalInput()
    choice match{
      case 1 => controller.askUserForWeapon(selected)
      case 2 => controller.userDropsWeapon(selected)
      case 3 => controller.askForName(selected)
      case 4 => selected.changeWeapon(controller.getWeaponChoice())
      case 5 => changedState = Some(new CharacterChoiceState(selected))
      case _ => controller.notifyInvalidOption(choice)
    }
  }

  /**Method for updating the controller´s state, if necessary
   *
   * @param controller the game controller
   */
  override def updateController(controller: GameController): Unit = {
    if(changedState.isDefined){
      controller.changeState(changedState.get)
    }
  }

}
