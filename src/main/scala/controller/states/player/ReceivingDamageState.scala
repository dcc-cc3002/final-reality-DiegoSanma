package controller.states.player

import attributes.Attributes
import controller.GameController
import controller.states.initial.TurnCalculatingState
import controller.states.last.CheckEndState
import controller.states.{AGameState, IGameState}
import enemy.EnemyAttributes
import exceptions.damage.UnaliveDamagedException
import exceptions.weaponexceptions.NoWeaponException

class ReceivingDamageState(selected:Attributes,victim:EnemyAttributes) extends AGameState {
  private var changedState: Option[IGameState] = None
  override def handleInput(controller: GameController): Unit = {
    try{
      selected.attack(victim)
      changedState = Some(new CheckEndState)
    }
    catch {
      case e: NoWeaponException => changedState = Some(new ChangeWeaponState(selected))
      case e1: UnaliveDamagedException => changedState = Some(new CharacterAttackState(selected))
    }
  }

  override def updateController(controller: GameController): Unit = {
    controller.changeState(changedState.get)
  }
}
