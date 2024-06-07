package controller.states.initial

import controller.GameController
import controller.states.player.CharacterChoiceState
import controller.states.AGameState
import controller.states.enemies.EnemyAttackState
import entity.Entity

class AttackState(private var selected:Entity) extends AGameState {

  override def updateController(controller: GameController): Unit = {
    val players = controller.getModel().getPlayers()
    if(players.contains(selected)){
      var index:Int = players.indexOf(selected)
      controller.changeState(new CharacterChoiceState(players(index)))
    }
    else{
      val enemies= controller.getModel().getEnemies()
      val index: Int = enemies.indexOf(selected)
      controller.changeState(new EnemyAttackState(enemies(index)))
    }
  }

}
