package states

import controller.GameController
import controller.states.enemies.EnemyAttackState
import controller.states.initial.TurnCalculatingState
import controller.states.last.CheckEndState

class EnemyAttackStateTest extends munit.FunSuite {
  var controller: GameController = null
  var enemyattack: EnemyAttackState = null

  override def beforeEach(context: BeforeEach): Unit = {
    controller = new GameController()
    enemyattack = new EnemyAttackState(controller.getModel().getEnemies()(0))
    controller.changeState(enemyattack)
  }

  test("Attacking a character"){
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[CheckEndState],"Enemy should´ve attacked successfully")
    assertEquals(controller.getModel().getPlayers()(0).getHp(),50,"Paladin´s hp should be 50 now")

    controller.changeState(new EnemyAttackState(controller.getModel().getEnemies()(0)))
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[CheckEndState],"Enemy should´ve attacked successfully")
    assertEquals(controller.getModel().getPlayers()(0).getHp(),0,"Paladin´s hp should be 0 now")

    controller.changeState(new EnemyAttackState(controller.getModel().getEnemies()(0)))
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[EnemyAttackState],"As paladin is dead, enemy has not attacked yet")
  }

}
