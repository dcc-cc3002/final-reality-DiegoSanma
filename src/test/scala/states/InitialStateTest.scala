package states

import controller.GameController
import controller.states.initial.{InitialState, TurnCalculatingState}

class InitialStateTest extends munit.FunSuite {
  var controller: GameController = null

  override def beforeEach(context: BeforeEach): Unit = {
    controller = new GameController()
  }

    test("Correct update"){
      controller.update()
      assert(controller.getState().isInstanceOf[TurnCalculatingState],"Update Controller should update state to next one")
    }

}
