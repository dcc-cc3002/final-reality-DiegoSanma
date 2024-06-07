package controller.states
import attributes.Attributes
import controller.GameController

class ChangeWeaponState(selected:Attributes) extends AGameState {
  private var changedState: Option[IGameState] = None

  override def handleInput(controller: GameController): Unit = {
    val choice: Int = controller.getNumericalInput()
    choice match{
      case 1 =>
      case 2 =>
      case 3 =>
      case 4 => selected.changeWeapon(controller.getNumericalInput())
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
