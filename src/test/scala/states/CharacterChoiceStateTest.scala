package states

import controller.GameController
import controller.states.player.{ChangeWeaponState, CharacterAttackState, CharacterChoiceState, MageSpellState}

class CharacterChoiceStateTest extends munit.FunSuite {
  var controller: GameController = null
  var choiceState: CharacterChoiceState = null
  var choiceState2: CharacterChoiceState = null

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
    choiceState = new CharacterChoiceState(controller.getModel().getPlayers()(0))
    choiceState2 = new CharacterChoiceState(controller.getModel().getPlayers()(2))
  }

  test("Choice made by non magic character"){
    controller.changeState(choiceState)
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[CharacterChoiceState],"Invalid choice given")

    controller.updatePlayerChoice(2)
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[CharacterAttackState],"Chose to attack an enemy")

    controller.changeState(new CharacterChoiceState(controller.getModel().getPlayers()(0)))

    controller.updatePlayerChoice(1)
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[ChangeWeaponState],"Choice was to change weapon, shoud be that state")

    controller.changeState(new CharacterChoiceState(controller.getModel().getPlayers()(0)))
    controller.updatePlayerChoice(3)
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[CharacterChoiceState],"Character is not a mage, so cant choose to cast spell")
  }

  test("Choice made by a mage"){
      controller.changeState(choiceState2)
      controller.updatePlayerChoice(3)
      controller.handleInput()
      controller.update()
    assert(controller.getState().isInstanceOf[MageSpellState],"Mage chose to cast spell, so must be in that state")
  }

}
