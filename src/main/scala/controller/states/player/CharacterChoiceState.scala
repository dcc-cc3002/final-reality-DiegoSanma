package controller.states.player

import attributes.{Attributes, IMage}
import controller.GameController
import controller.states.{AGameState, IGameState}
import exceptions.mage.NotMageException

class CharacterChoiceState (selected:Attributes) extends AGameState {
  private var stateChoice: Option[IGameState] = None
  override def handleInput(controller: GameController): Unit = {
    val choice: Int = controller.getNumericalInput()
    choice match {
      case 1 => stateChoice = Some(new ChangeWeaponState(selected))
      case 2 => stateChoice = Some(new CharacterAttackState(selected))
      case 3 => this.checkIfMage(selected,controller)
      case _ => controller.notifyInvalidOption(choice)
    }
  }

  override def updateController(controller: GameController): Unit = {
    if(stateChoice.isDefined){
      controller.changeState(stateChoice.get)
    }
  }

  private def checkIfMage(selected: Attributes,controller: GameController): Unit = {
    try{
      val mage: IMage = selected.seeIfMage()
      stateChoice = Some(new MageSpellState(mage,mageAsCharacter))

    }
    catch {
      case e: NotMageException => controller.notifyInvalidOption(3)
    }
  }

}
