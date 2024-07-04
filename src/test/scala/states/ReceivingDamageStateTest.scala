package states

import controller.GameController
import controller.states.last.CheckEndState
import controller.states.player.{ChangeWeaponState, CharacterAttackState, ReceivingDamageState}
import sword.Sword

class ReceivingDamageStateTest extends munit.FunSuite {
  var controller: GameController = null
  var sword: Sword = null
  var attackState: CharacterAttackState = null
  var enemyKiller: Sword = null

  override def beforeEach(context: BeforeEach): Unit = {
    sword = new Sword("Excalibur",120,50)
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

  test("Successful attack"){
    controller.getModel().getPlayers()(0).receiveWeapon(sword)
    controller.getModel().getPlayers()(0).changeWeapon(0)

    controller.updatePlayerChoice(1)
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[ReceivingDamageState],"Should pass to ReceivingDamageState")

    controller.handleInput()
    controller.update()

    assertEquals(controller.getAllEnemies()(0).getHp(),80,"Enemy should´ve taken 20 damage")
    assert(controller.getState().isInstanceOf[CheckEndState],"Should´ve passed to checkEndState")
  }

  test("Unsuccessful Attack"){
    controller.updatePlayerChoice(1)
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[ReceivingDamageState],"Should pass to ReceivingDamageState")
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[ChangeWeaponState],"Has no weapon, should pass to ChangeWeaponState")

    controller.changeState(new CharacterAttackState(controller.getModel().getPlayers()(0)))
    controller.handleInput()
    controller.update()
    enemyKiller = new Sword("Excalibur",250,90)
    controller.getModel().getPlayers()(0).receiveWeapon(enemyKiller)
    controller.getModel().getPlayers()(0).changeWeapon(0)

    controller.getModel().getPlayers()(0).attack(controller.getAllEnemies()(0))

    assertEquals(controller.getAllEnemies()(0).getHp(),0,"Enemy should´ve died")

    controller.handleInput()
    controller.update()

    assert(controller.getState().isInstanceOf[CharacterAttackState],"Enemy was already dead, should pass to CharacterAttackState")

  }

}
