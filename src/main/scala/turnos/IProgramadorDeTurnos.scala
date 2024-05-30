package turnos

import attributes.Attributes
import enemigo.{Enemigo, EnemigoAttributes}
import entity.Entidad

import scala.collection.mutable.ArrayBuffer

/**A trait for the turn scheduler
 *
 * Defines the methods that will be used when scheduling turns
 *
 */
trait IProgramadorDeTurnos {
  def addTo(added:Entidad): Unit

  def addCharacter(added:Attributes): Unit

  def addEnemy(added:EnemigoAttributes): Unit

  def removeEntity(removed:Entidad): Unit

  def removeCharacter(removed:Attributes)

  def removeEnemy(removed:EnemigoAttributes)

  def Barra(): ArrayBuffer[(Entidad,Double)]

  def continuar(k:Int): Unit

  def revisionTurno(): Unit

  def next_turn(): Option[Entidad]

  def getPlayers(): ArrayBuffer[Attributes]

  def getEnemies(): ArrayBuffer[EnemigoAttributes]
}
