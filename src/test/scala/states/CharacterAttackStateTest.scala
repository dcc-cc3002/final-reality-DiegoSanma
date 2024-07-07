package states

import controller.GameController
import controller.states.player.{CharacterAttackState, ReceivingDamageState}

class CharacterAttackStateTest extends munit.FunSuite {
  var controller: GameController = null
  var attackState: CharacterAttackState = null
  override def beforeEach(context: BeforeEach): Unit = {
    controller = new GameController()
    controller.updatePlayerChoice(1)
    controller.handleInput()
    controller.update()
    controller.updatePlayerChoice(3)
    controller.handleInput()
    controller.update()
    controller.updatePlayerChoice(5)
    controller.handleInput()
    controller.update()
    controller.updatePlayerChoice(6)
    controller.handleInput()
    controller.update()
    attackState = new CharacterAttackState(controller.getModel().getPlayers()(0))
    controller.changeState(attackState)
  }

  test("Attacking") {
    assert(controller.getState().isInstanceOf[CharacterAttackState], "Should be in CharacterAttackState")

    controller.updatePlayerChoice(7)
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[CharacterAttackState], "Invalid input given")

    controller.updatePlayerChoice(1)
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[ReceivingDamageState],"Should have passed to ReceivingDamageState")
  }
}
