package gameStarter

import characters.{BlackMage, Ninja, Paladin, Warrior, WhiteMage}
import controller.GameController

class GameStarter(controller:GameController){
  private var paladin: Paladin = null
  private var warrior: Warrior = null
  private var ninja: Ninja = null
  private var whiteMage: WhiteMage = null
  private var blackMage: BlackMage = null

    def chooseCharacters(): Unit = {
    }
}
