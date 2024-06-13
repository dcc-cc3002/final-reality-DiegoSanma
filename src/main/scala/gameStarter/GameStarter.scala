package gameStarter

import attributes.Attributes
import characters.{BlackMage, Ninja, Paladin, Warrior, WhiteMage}
import controller.GameController
import enemy.Enemy
import entity.Entity
import exceptions.turns.AlreadyInSchedulerException
import turnscheduler.ITurnScheduler

import scala.collection.mutable.ArrayBuffer

class GameStarter(controller:GameController,model:ITurnScheduler){
   /** Paramter to see what character the user has chosen */
  private var selected: Option[Attributes] = None


  /**Method for choosing characters to add to the game
   * Depends on the user´s choice, what character they choose to add
   */
  def chooseCharacter(): Unit = {
        var choice: Int = controller.getNumericalInput()
        var name: String = controller.getStringInput()
        choice match {
          case 1 => selected = Some(new Paladin(name, 100, 100, 100))
          case 2 => selected = Some(new Warrior(name, 100, 100, 150))
          case 3 => selected = Some(new Ninja(name, 100, 100, 50))
          case 4 => selected = Some(new WhiteMage(name, 100, 100, 75, 100))
          case 5 => selected = Some(new BlackMage(name, 100, 100, 90, 100))
          case _ => controller.notifyInvalidOption(choice)
        }
        if(selected.isDefined) {
          try {
            model.addTo(selected.get)
          }
          catch {
            case e: AlreadyInSchedulerException => selected = None
          }
        }
    }

    def createEnemies(): Unit = {
      for (i <-1 to 3){
        model.addTo(new Enemy("Bad",100,100,100,150))
      }
    }
}
