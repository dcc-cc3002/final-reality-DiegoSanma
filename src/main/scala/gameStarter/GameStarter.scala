package gameStarter

import attributes.Attributes
import characters.{BlackMage, Ninja, Paladin, Warrior, WhiteMage}
import controller.GameController
import enemy.Enemy
import entity.Entity
import turnscheduler.ITurnScheduler

import scala.collection.mutable.ArrayBuffer

class GameStarter(controller:GameController,model:ITurnScheduler){
  private var selected: Attributes = null

    def chooseCharacters(turnScheduler: ITurnScheduler): Unit = {
      while (turnScheduler.getPlayers().length < 3) {
        var choice: Int = controller.getNumericalInput()
        var name: String = controller.getStringInput()
        choice match {
          case 1 => selected = new Paladin(name, 100, 100, 100)
          case 2 => selected = new Warrior(name, 100, 100, 100)
          case 3 => selected = new Ninja(name, 100, 100, 100)
          case 4 => selected = new WhiteMage(name, 100, 100, 100, 100)
          case 5 => selected = new BlackMage(name, 100, 100, 100, 100)
          case _ => controller.notifyInvalidOption(choice)
          turnScheduler.addTo(selected)
        }
      }
    }

    def createEnemies(turnScheduler: ITurnScheduler): Unit = {
      for (i <-1 to 3){
        turnScheduler.addTo(new Enemy("Bad",100,100,100,100))
      }
    }

    def createWeapons(controller:GameController): Unit = {

    }
}
