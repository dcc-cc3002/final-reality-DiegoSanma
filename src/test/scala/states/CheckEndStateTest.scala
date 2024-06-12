package states

import characters.Paladin
import controller.GameController
import controller.states.initial.TurnCalculatingState
import controller.states.last.{CheckEndState, FinalState}
import enemy.Enemy
import sword.Sword

class CheckEndStateTest extends munit.FunSuite {
  var controller: GameController = null
  var check: CheckEndState = null
  var enemyKiller: Paladin = null
  var sword: Sword = null
  var characterKiller: Enemy = null

  override def beforeEach(context: BeforeEach): Unit = {
    controller = new GameController()
    check = new CheckEndState
    controller.changeState(check)
    enemyKiller = new Paladin("S",100,100,100)
    sword = new Sword("Excalibur",300,100)
    enemyKiller.receiveWeapon(sword)
    enemyKiller.changeWeapon(0)
    characterKiller = new Enemy("Golem",70,70,70,300)
  }

  test("CheckEnd continue"){
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[TurnCalculatingState],"NO team is defeated, continue combat")
  }

  test("CheckEnd with players dead"){
    for(i<- controller.getModel().getPlayers()){
      characterKiller.attack(i)
    }
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[FinalState],"PLayers defeated,should pass to final state")
  }

  test("CheckEnd with enemies dead"){
    for(i<- controller.getModel().getEnemies()){
      enemyKiller.attack(i)
    }
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[FinalState],"Enemies defeated,should pass to final state")
  }
}