package starters

import characters.{Paladin, Warrior}
import controller.GameController
import controller.states.initial.InitialState

class GameStarterTest extends munit.FunSuite {
  var controller: GameController = null

  override def beforeEach(context: BeforeEach): Unit = {
    controller = new GameController()
  }

  test("Checking game starter works correctly"){
    assertEquals(controller.getAllEnemies().length,3,"Should have added three enemies")

    controller.updatePlayerChoice(100)
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[InitialState],"Invalid choice was given for characters")

    controller.updatePlayerChoice(1)
    controller.handleInput()
    controller.update()
    assertEquals(controller.getAllPlayers().getMembers().length,1,"Should´ve added someone")
    assert(controller.getAllPlayers().getMembers()(0).get.isInstanceOf[Paladin],"Should´ve added warrior")

    controller.updatePlayerChoice(7)
    controller.handleInput()
    controller.update()
    assertEquals(controller.getAllPlayers().getMembers().length,1,"Shouldn´t add anyone, invalid choice")
  }

}
