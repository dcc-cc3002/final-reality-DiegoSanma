package controller.states.player

import attributes.Attributes
import controller.GameController
import controller.states.{AGameState, IGameState}

class ChangeWeaponState(selected:Attributes) extends AGameState {
  private var changedState: Option[IGameState] = None

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

  override def updateController(controller: GameController): Unit = {
    if(changedState.isDefined){
      controller.changeState(changedState.get)
    }
  }

}
