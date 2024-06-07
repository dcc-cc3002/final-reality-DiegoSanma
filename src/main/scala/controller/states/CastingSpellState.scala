package controller.states

import attributes.{Attributes, IMage}
import controller.GameController
import enemy.EnemyAttributes
import entity.Entity
import spells.ISpells

import scala.collection.mutable.ArrayBuffer

class CastingSpellState(mage:IMage,mageAsCharacter:Attributes,spell:ISpells) extends AGameState {
  private var changedState: Option[IGameState]= None

  override def handleInput(controller: GameController): Unit = {
    val entities: ArrayBuffer[Entity] = controller.getModel().getPlayers()++controller.getModel.getEnemies()
    val choice: Int = controller.getNumericalInput()
    if(choice<1 || choice>entities.length+1){
      controller.notifyInvalidOption(choice)
    }
    else{
      changedState = Some(new InflictingSpellState(mage,mageAsCharacter,spell,entities(choice-1)))
    }
  }

  override def updateController(controller: GameController): Unit = {
    if(changedState.isDefined){
      controller.changeState(changedState.get)
    }
  }
}
