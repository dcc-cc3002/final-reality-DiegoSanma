package states

import controller.GameController
import controller.states.initial.InitialState
import turnscheduler.ITurnScheduler

class ControllerTest extends munit.FunSuite {

  test("Initiliazing Controller"){
    val controller: GameController = new GameController()
    assert(controller.getModel().isInstanceOf[ITurnScheduler],"Model is not initialized as a turn Scheduler")
    val turnScheduler: ITurnScheduler = controller.getModel()
    assertEquals(turnScheduler.getEnemies().length,3,"Turn Scheduler not started with 3 enemies")
    assert(turnScheduler.getPlayers().isEmpty,"Still no players have been added")
    assert(controller.getState().isInstanceOf[InitialState],"Controller should be initialized with Initial State")
    assert(!(controller.getState().hasFinished()),"In initial state, game has not finished")
  }

}
