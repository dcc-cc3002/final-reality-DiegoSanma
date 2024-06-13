package controller.states.initial

import controller.GameController
import controller.states.player.CharacterChoiceState
import controller.states.AGameState
import controller.states.enemies.{EnemyAttackState, EnemyStatusState}
import entity.Entity

/**Class for state when deciding whose turn it is next
 *
 * @param selected the entity whose turn it is next
 */
class AttackState(private var selected:Entity) extends AGameState {

  /**Method for checking whether the entity whose turn it is is a character or enemy
   * Makes sure to update the controller state depending on what the entity is
   *
   * @param controller the game controller
   */
  override def updateController(controller: GameController): Unit = {
    val players = controller.getModel().getPlayers()
    if(players.contains(selected)){
      var index:Int = players.indexOf(selected)
      controller.changeState(new CharacterChoiceState(players(index)))
    }
    else{
      val enemies= controller.getModel().getEnemies()
      val index: Int = enemies.indexOf(selected)
      controller.changeState(new EnemyStatusState(enemies(index)))
    }
  }

}
