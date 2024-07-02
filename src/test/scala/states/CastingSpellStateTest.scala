package states

import controller.GameController
import controller.states.player.{CastingSpellState, CharacterChoiceState, InflictingSpellState, MageSpellState}
import wand.Wand

class CastingSpellStateTest extends munit.FunSuite {
  var controller: GameController = null
  var spellState: CastingSpellState = null
  var characterChoice: CharacterChoiceState = null
  var magicWeapon: Wand = null

  override def beforeEach(context: BeforeEach): Unit = {
    controller = new GameController()
    controller = new GameController()
    controller.updatePlayerChoice(1)
    controller.handleInput()
    controller.update()
    controller.updatePlayerChoice(4)
    controller.handleInput()
    controller.update()
    controller.updatePlayerChoice(5)
    controller.handleInput()
    controller.update()
    controller.updatePlayerChoice(6)
    controller.handleInput()
    controller.update()
    characterChoice = new CharacterChoiceState(controller.getAllPlayers().getMembers()(2).get)
    controller.changeState(characterChoice)
    controller.updatePlayerChoice(3)
    controller.handleInput()
    controller.update()
    controller.updatePlayerChoice(2)
    controller.handleInput()
    controller.update()
  }

  test("Correct pass to state"){
    assert(controller.getState().isInstanceOf[CastingSpellState],"Should´ve passed to CastingSpellState")
  }

  test("Choosing an enemy to cast spell on"){
    controller.updatePlayerChoice(0)
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[CastingSpellState],"Should´ve remained in CastingSpellState")

    controller.updatePlayerChoice(1)
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[InflictingSpellState],"Should´be chosen a correct enemy and changed state")
  }
}
