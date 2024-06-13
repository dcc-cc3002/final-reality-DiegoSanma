package controller.states.player

import attributes.{Attributes, IMage}
import controller.GameController
import controller.states.{AGameState, IGameState}
import spells._

/**Class for state where mage is choosing what spell to cast
 *
 * @param mage the mage casting the spell
 * @param mageAsCharacter the mage as a Character
 */
class MageSpellState(mage:IMage,mageAsCharacter:Attributes) extends AGameState {
  /** Parameter that hold the next state */
  private var changedState: Option[IGameState] = None

  /**Method for handling user´s choice of what spell to cast
   *
   * @param controller the game controller
   */
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
