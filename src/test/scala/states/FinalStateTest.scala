package states

import controller.GameController
import controller.states.last.FinalState

class FinalStateTest extends munit.FunSuite {
  var controller: GameController = null
  var finalPart: FinalState = null

  override def beforeEach(context: BeforeEach): Unit = {
    controller = new GameController()
    finalPart = new FinalState()
    controller.changeState(finalPart)
  }

  test("Check print"){
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[FinalState],"Just prints out, no change of state")
  }
}
