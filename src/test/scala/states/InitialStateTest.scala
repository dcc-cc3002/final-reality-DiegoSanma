package states

import characters.Paladin
import controller.GameController
import controller.states.initial.{InitialState, TurnCalculatingState}

class InitialStateTest extends munit.FunSuite {
  var controller: GameController = null

  override def beforeEach(context: BeforeEach): Unit = {
    controller = new GameController()
  }

    test("How it begins"){
      controller.handleInput()
      controller.update()
      assert(controller.getState().isInstanceOf[InitialState],"Haven´t added players")
    }

  test("Choosing players"){
    controller.updatePlayerChoice(1)
    assertEquals(controller.getNumericalInput(),1,"Choice should be 1")
    controller.handleInput()
    controller.update()

    assert(controller.getState().isInstanceOf[InitialState],"User has not decided to add members yet")
    assertEquals(controller.getAllPlayers().getMembers().length,1,"Should have a player added")
    assert(controller.getAllPlayers().getMembers()(0).get.isInstanceOf[Paladin],"Should´ve added a Paladin")


    controller.updatePlayerChoice(7)
    controller.handleInput()
    controller.update()

    assert(controller.getState().isInstanceOf[InitialState],"Invalid choice made")

    controller.updatePlayerChoice(6)
    controller.handleInput()
    controller.update()

    assert(controller.getState().isInstanceOf[TurnCalculatingState],"User chose to begin game")
  }

  test("Trying to add players to full game"){
    controller.updatePlayerChoice(2)
    controller.handleInput()
    controller.update()

    controller.updatePlayerChoice(4)
    controller.handleInput()
    controller.update()

    controller.updatePlayerChoice(5)
    controller.handleInput()
    controller.update()

    assertEquals(controller.getAllPlayers().getMembers().length,3,"Party should be full")

    controller.updatePlayerChoice(3)
    controller.handleInput()
    controller.update()

    assertEquals(controller.getModel().getPlayers().length,3,"No more characters should be added to turn scheduler")
  }

}
