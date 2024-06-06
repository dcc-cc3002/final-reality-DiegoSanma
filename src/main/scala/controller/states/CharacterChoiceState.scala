package controller.states

import attributes.{Attributes, IMage}
import controller.GameController
import exceptions.mage.NotMageException

class CharacterChoiceState (selected:Attributes) extends AGameState {
  private val stateChoice: Option[IGameState] = None
  override def handleInput(controller: GameController): Unit = {
    val choice: Int = controller.getNumericalInput()
    choice match {
      case 1 => stateChoice = new ChangeWeaponState(selected)
      case 2 => stateChoice = new CharacterAttackState(selected)
      case 3 => this.checkIfMage(selected,controller)
      case _ => controller.notifyInvalidOption(choice)
    }
  }

  override def updateController(controller: GameController): Unit = {
    if(stateChoice.isDefined){
      controller.changeState(stateChoice.get)
    }
  }

  private def checkIfMage(selected: Attributes,controller: GameController): IMage = {
    try{
      val mage: IMage = selected.seeIfMage()
    }
    catch {
      case e: NotMageException => controller.notifyInvalidOption(3)
    }
    stateChoice = MageSpellState(mage)
  }

}
