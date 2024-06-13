package controller.states.player

import attributes.{Attributes, IMage}
import controller.GameController
import controller.states.{AGameState, IGameState}
import entity.Entity
import spells.ISpells

import scala.collection.mutable.ArrayBuffer

/**Class for when a mage is choosing what entity to cast their spell on
 *
 * @param mage mage casting spell
 * @param mageAsCharacter mage as character casting spell
 * @param spell spell being casted
 */
class CastingSpellState(mage:IMage,mageAsCharacter:Attributes,spell:ISpells) extends AGameState {
  /** Parameter that holds the next state */
  private var changedState: Option[IGameState]= None

  /**Method for asking user what entity they want o cast the spell on
   *
   * @param controller the game controller
   */
  override def handleInput(controller: GameController): Unit = {
    val entities: ArrayBuffer[Entity] = controller.getModel().getPlayers()++controller.getModel().getEnemies()
    val choice: Int = controller.getNumericalInput()
    if(choice<1 || choice>entities.length+1){
      controller.notifyInvalidOption(choice)
    }
    else{
      changedState = Some(new InflictingSpellState(mage,mageAsCharacter,spell,entities(choice-1)))
    }
  }

  /**Method for updating controller´s state, if necessary
   *
   * @param controller the game´s controller
   */
  override def updateController(controller: GameController): Unit = {
    if(changedState.isDefined){
      controller.changeState(changedState.get)
    }
  }
}
