package states

import controller.GameController
import controller.states.enemies.{EnemyAttackState, EnemyStatusState}
import controller.states.initial.TurnCalculatingState
import controller.states.last.CheckEndState
import enemy.Enemy
import status.{Burnt, Paralyzed, Poisoned}
import wand.Wand

class EnemyStatusStateTest extends munit.FunSuite {
  var controller: GameController = null
  var statusState: EnemyStatusState = null
  var wand: Wand = null
  var deathWand: Wand = null
  var burnt: Burnt = null
  var poisoned: Poisoned = null
  var poisonedDeath: Poisoned = null
  var paralzyed: Paralyzed = null


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
    statusState = new EnemyStatusState(controller.getModel().getEnemies()(0))
    controller.changeState(statusState)
    wand = new Wand("Stick",70,70,60)
    deathWand = new Wand("Bigger Stick",70,70,325)
    burnt = new Burnt(wand)
    poisoned = new Poisoned(wand)
    poisonedDeath = new Poisoned(deathWand)
    paralzyed = new Paralyzed
  }

  test("EnemyStatusState correct for no status"){
    assert(controller.getState().isInstanceOf[EnemyStatusState],"State should be enemyStatusState")
    assert(controller.getModel().getEnemies()(0).getStatus().isEmpty,"Enemy should have no status condition")
    controller.handleInput()
    controller.update()

    assert(controller.getState().isInstanceOf[EnemyAttackState],"With no status condition, should pass to attack state")
  }

  test("StatusState while paralyzed"){
    controller.getModel().getEnemies()(0).setStatus(paralzyed)
    controller.handleInput()
    controller.update()

    assert(controller.getState().isInstanceOf[TurnCalculatingState],"Turn should´ve been skipped")
    assert(controller.getModel().getEnemies()(0).getStatus().isEmpty,"Should no longer be paralzyed")
  }

  test("StatusState while burnt"){
    controller.getModel().getEnemies()(0).setStatus(burnt)
    controller.handleInput()
    controller.update()

    assert(controller.getState().isInstanceOf[EnemyAttackState],"Survives burn, should pass to attack state")
    assert(controller.getModel().getEnemies()(0).getStatus().get.isInstanceOf[Burnt],"Should still be burnt")
    assertEquals(controller.getModel().getEnemies()(0).getHp(),70,"Enemy shouldve taken 30 burn damage")
  }

  test("StatusState while poisoned"){
    controller.getModel().getEnemies()(0).setStatus(poisoned)
    controller.handleInput()
    controller.update()

    assert(controller.getState().isInstanceOf[EnemyAttackState],"Survives poison, should pass to attack state")
    assert(controller.getModel().getEnemies()(0).getStatus().get.isInstanceOf[Poisoned],"Should still be poisoned")
    assertEquals(controller.getModel().getEnemies()(0).getHp(),80,"Enemy shouldve taken 20 poison damage")
  }

  test("Death in StatusState"){
    controller.getModel().getEnemies()(0).setStatus(poisonedDeath)
    controller.handleInput()
    controller.update()

    assert(controller.getState().isInstanceOf[CheckEndState],"Enemy should´ve died, so passes to turn calculating")
    assertEquals(controller.getModel().getEnemies()(0).isAlive(),0,"Should be dead")
  }


}
