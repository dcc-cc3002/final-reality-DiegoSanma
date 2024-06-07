package controller.states
import attributes.Attributes
import controller.GameController
import enemy.EnemyAttributes

import scala.collection.mutable.ArrayBuffer

class CharacterAttackState(selected:Attributes) extends AGameState {
  private var changedState: Option[IGameState] = None

  override def handleInput(controller: GameController): Unit = {
    val enemies: ArrayBuffer[EnemyAttributes] = controller.getModel().getEnemies()
    val choice: Int = controller.getNumericalInput()
    if(choice<1 || choice>3){
      controller.notifyInvalidOption(choice)
      changedState = Some(this)
    }
    else{
      changedState = Some(new ReceivingDamageState(selected,enemies(choice-1)))
    }
  }

  override def updateController(controller: GameController): Unit = {
    if(selected.getActiveWeapon().isEmpty){
      controller.notifyNoWeapon()
      changedState = Some(new ChangeWeaponState(selected))
    }
    controller.changeState(changedState.get)
  }
}
