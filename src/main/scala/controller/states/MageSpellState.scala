package controller.states

import attributes.{Attributes, IMage}
import controller.GameController
import spells.{FireSpell, HealingSpell, ParalisisSpell, PoisonSpell, ThunderSpell}

class MageSpellState(mage:IMage,mageAsCharacter:Attributes) extends AGameState {
  private var changedState: Option[IGameState] = None

  override def handleInput(controller: GameController): Unit = {
    val choice: Int = controller.getNumericalInput()
    choice match {
      case 1 => changedState = Some(new CastingSpellState(mage,mageAsCharacter,new ThunderSpell()))
      case 2 => changedState = Some(new CastingSpellState(mage,mageAsCharacter,new FireSpell()))
      case 3 => changedState = Some(new CastingSpellState(mage,mageAsCharacter,new PoisonSpell()))
      case 4 => changedState = Some(new CastingSpellState(mage,mageAsCharacter,new HealingSpell()))
      case 5 => changedState = Some(new CastingSpellState(mage,mageAsCharacter,new ParalisisSpell()))
      case _ => controller.notifyInvalidOption(choice)
    }
  }

  override def updateController(controller: GameController): Unit = {
    if(changedState.isDefined){
      controller.changeState(changedState.get)
    }
  }

}
