package states

import characters.{BlackMage, Ninja}
import controller.GameController
import controller.states.initial.{AttackState, TurnCalculatingState}

class TurnCalculatingStateTest extends munit.FunSuite {
  var controller: GameController = null

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

  test("Correct calculator"){
    assert(controller.getState().isInstanceOf[TurnCalculatingState],"Should start in Turn Calculating")
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[TurnCalculatingState],"Should not have changed state, as no one passes")

    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[AttackState],"Ninja should have passed his action bar")
    assert(controller.getModel().getPlayers()(1).isInstanceOf[Ninja],"Should be a ninja")
    assertEquals(controller.getModel().getPlayers()(1).getActionBar(),0,"Ninja´s action bar should be 0")

    controller.changeState(new TurnCalculatingState())
    controller.handleInput()
    controller.update()
    assert(controller.getModel().getPlayers()(2).isInstanceOf[BlackMage],"Should be a blackMage")
    assertEquals(controller.getModel().getPlayers()(2).getActionBar(),0,"Black Mage´s action bar should be 0")
    assert(controller.getState().isInstanceOf[AttackState],"Ninja should have passed his action bar")

    controller.changeState(new TurnCalculatingState())
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[AttackState],"Ninja should have passed his action bar")
    assert(controller.getModel().getPlayers()(1).isInstanceOf[Ninja],"Should be a ninja")
    assertEquals(controller.getModel().getPlayers()(1).getActionBar(),0,"Ninja´s action bar should be 0")
    for(i<-controller.getModel().getEnemies()){
      assertEquals(i.getActionBar(),0,"Enemies action bar should be 0")
    }
  }

}
