package turnscheduler

import attributes.Attributes
import enemy.{Enemy, EnemyAttributes}
import entity.Entity

import scala.collection.mutable.ArrayBuffer

/**A trait for the turn scheduler
 *
 * Defines the methods that will be used when scheduling turns
 *
 */
trait ITurnScheduler {

  def getTurnsLine(): ArrayBuffer[Entity]

  def addTo(added:Entity): Unit

  protected def addCharacter(added:Attributes): Unit

  protected def addEnemy(added:EnemyAttributes): Unit

  protected def removeEntity(removed:Entity): Unit

  protected def removeCharacter(removed:Attributes)

  def removeEnemy(removed:EnemyAttributes)

  def continue(k:Int): Unit

  def checkTurn(): Unit

  def nextTurn(): Option[Entity]

  def getPlayers(): ArrayBuffer[Attributes]

  def getEnemies(): ArrayBuffer[EnemyAttributes]
}
