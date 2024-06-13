package states

import controller.GameController
import controller.states.player.{CharacterChoiceState, InflictingSpellState, MageSpellState}
import wand.Wand

class MageSpellStateTest extends munit.FunSuite {
  var controller: GameController = null
  var spellState: MageSpellState = null
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
    characterChoice = new CharacterChoiceState(controller.getAllPlayers().getMembers()(1).get)
    controller.changeState(characterChoice)
    controller.updatePlayerChoice(3)
    controller.handleInput()
    controller.update()
  }

  test("Correctly passed to spell state"){
    assert(controller.getState().isInstanceOf[MageSpellState],"Should´ve passed to MageSpellState")
  }

  test("Thunder Spell chosen"){
    controller.updatePlayerChoice(1)
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[InflictingSpellState],"Should´ve passed to inflicting spell state")
  }

  test("Fire Spell chosen"){
    controller.updatePlayerChoice(2)
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[InflictingSpellState],"Should´ve passed to inflicting spell state")
  }

  test("Poison Spell chosen"){
    controller.updatePlayerChoice(3)
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[InflictingSpellState],"Should´ve passed to inflicting spell state")
  }
  test("Healing Spell chosen"){
    controller.updatePlayerChoice(4)
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[InflictingSpellState],"Should´ve passed to inflicting spell state")
  }

  test("Paralisis Spell chosen"){
    controller.updatePlayerChoice(5)
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[InflictingSpellState],"Should´ve passed to inflicting spell state")
  }
  test("No spell chosen"){
    controller.updatePlayerChoice(6)
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[MageSpellState],"Choice too big")
  }
}
