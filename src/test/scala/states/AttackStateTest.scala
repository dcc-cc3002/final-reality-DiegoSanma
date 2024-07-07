package states

import characters.Paladin
import controller.GameController
import controller.states.enemies.{EnemyAttackState, EnemyStatusState}
import controller.states.initial.AttackState
import controller.states.player.CharacterChoiceState

class AttackStateTest extends munit.FunSuite {
  var controller: GameController = null
  var attack: AttackState = null

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
  }

  test("Attack State Character´s Turn"){
    attack = new AttackState(controller.getAllPlayers().getMembers()(0).get)
    controller.changeState(attack)
    assertEquals(attack,controller.getState().asInstanceOf[AttackState],"State should be attack state just defined")
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[CharacterChoiceState],"Should´ve passed to characterChoiceState")
  }

  test("Attack State Enemies Turn"){
    attack = new AttackState(controller.getModel().getEnemies()(0))
    controller.changeState(attack)
    assertEquals(attack,controller.getState().asInstanceOf[AttackState],"State should be attack state just defined")
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[EnemyStatusState],"Should´ve passed to EnemyStatusState")
  }

}
