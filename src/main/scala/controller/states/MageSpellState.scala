package controller.states

import attributes.IMage
import controller.GameController
import spells.{FireSpell, HealingSpell, ParalisisSpell, PoisonSpell, ThunderSpell}

class MageSpellState(mage:IMage) extends AGameState {
  private var changedState: Option[IGameState] = None

  override def handleInput(controller: GameController): Unit = {
    val choice: Int = controller.getNumericalInput()
    choice match {
      case 1 => changedState = Some(new CastingSpellState(mage,new ThunderSpell()))
      case 2 => changedState = Some(new CastingSpellState(mage,new FireSpell()))
      case 3 => changedState = Some(new CastingSpellState(mage,new PoisonSpell()))
      case 4 => changedState = Some(new CastingSpellState(mage,new HealingSpell()))
      case 5 => changedState = Some(new CastingSpellState(mage,new ParalisisSpell()))
      case _ => controller.notifyInvalidOption(choice)
    }
  }

  override def updateController(controller: GameController): Unit = {
    if(changedState.isDefined){
      controller.changeState(changedState.get)
    }
  }

}
