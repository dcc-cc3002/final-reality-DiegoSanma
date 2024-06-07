package controller.states
import attributes.Attributes
import controller.GameController
import enemy.EnemyAttributes
import exceptions.damage.UnaliveDamagedException
import exceptions.weaponexceptions.NoWeaponException

class ReceivingDamageState(selected:Attributes,victim:EnemyAttributes) extends AGameState {
  private var changedState: Option[IGameState] = None
  override def handleInput(controller: GameController): Unit = {
    try{
      selected.attack(victim)
      changedState = Some(new TurnCalculatingState())
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
