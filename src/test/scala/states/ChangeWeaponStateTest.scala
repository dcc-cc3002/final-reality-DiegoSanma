package states

import axe.Axe
import bow.Bow
import controller.GameController
import controller.states.player.ChangeWeaponState
import sword.Sword

class ChangeWeaponStateTest extends munit.FunSuite {
  var controller: GameController = null
  var changeWeapon: ChangeWeaponState = null

  override def beforeEach(context: BeforeEach): Unit = {
    controller = new GameController()
    controller.updatePlayerChoice(2)
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
    changeWeapon = new ChangeWeaponState(controller.getAllPlayers().getMembers()(0).get)
    controller.changeState(changeWeapon)
  }

  test("Receiving weapon and dropping"){
    controller.updatePlayerChoice(1)
    controller.handleInput()
    controller.update()

    assert(controller.getState().isInstanceOf[ChangeWeaponState],"Should still be in same state")
    assert(controller.getAllPlayers().getMembers()(0).get.getWeapons().isEmpty,"Weapon Choice to big")
    controller.updateWeaponChoice(1)
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[ChangeWeaponState],"Should still be in same state")
    assert(controller.getAllPlayers().getMembers()(0).get.getWeapons()(0).isInstanceOf[Sword],"Sword not added")

    controller.updateWeaponChoice(2)
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[ChangeWeaponState],"Should still be in same state")
    assert(controller.getAllPlayers().getMembers()(0).get.getWeapons()(1).isInstanceOf[Axe],"Axe not added")


    controller.updateWeaponChoice(3)
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[ChangeWeaponState],"Should still be in same state")
    assert(controller.getAllPlayers().getMembers()(0).get.getWeapons()(2).isInstanceOf[Bow],"Bow not added")

    controller.updateWeaponChoice(1)
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[ChangeWeaponState],"Should still be in same state")
    assertEquals(controller.getAllPlayers().getMembers()(0).get.getWeapons().length,3,"Inventory is full")

    controller.updatePlayerChoice(2)
    controller.updateWeaponChoice(0)
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[ChangeWeaponState],"Should still be in same state")
    assertEquals(controller.getAllPlayers().getMembers()(0).get.getWeapons().length,2,"Sword should be removed")
    for(i<-controller.getAllPlayers().getMembers()(0).get.getWeapons()){
      assert(!(i.isInstanceOf[Sword]))
    }

    controller.updateWeaponChoice(3)
    controller.handleInput()
    controller.update()
    assert(controller.getState().isInstanceOf[ChangeWeaponState],"Should still be in same state")
    assertEquals(controller.getAllPlayers().getMembers()(0).get.getWeapons().length,2,"Weapon Choice bigger than inventory")
  }

  test("Equipping and Renaming Weapon"){
    controller.updatePlayerChoice(3)
    controller.updateStringChoice("Sanma")
    controller.update()
    controller.handleInput()
    assert(controller.getState().isInstanceOf[ChangeWeaponState],"Should still be in same state")

    controller.updatePlayerChoice(1)
    controller.updateWeaponChoice(1)
    controller.handleInput()
    controller.update()

    controller.updateWeaponChoice(2)
    controller.updatePlayerChoice(4)
    controller.handleInput()
    controller.update()
    assert(controller.getAllPlayers().getMembers()(0).get.getActiveWeapon().isEmpty,"Tried to add beyond current inventory")

    controller.updateWeaponChoice(0)
    controller.handleInput()
    controller.update()
    assert(controller.getAllPlayers().getMembers()(0).get.getActiveWeapon().get.isInstanceOf[Sword],"Should be sword")

    controller.updatePlayerChoice(3)
    controller.handleInput()
    controller.update()

    assertEquals(controller.getAllPlayers().getMembers()(0).get.getActiveWeapon().get.getWeaponName(),"Sanma","Not renamed")

    controller.updateStringChoice("Excalibur")
    controller.handleInput()
    controller.update()

    assertEquals(controller.getAllPlayers().getMembers()(0).get.getActiveWeapon().get.getWeaponName(),"Sanma","Was renamed")


  }

}
