package turnos

import entity.Entidad

import scala.collection.mutable.ArrayBuffer

/**A trait for the turn scheduler
 *
 * Defines the methods that will be used when scheduling turns
 *
 */
trait IProgramadorDeTurnos {
  def agregar(added:Entidad): Unit

  def sacar(removed:Entidad): Unit

  def Barra(): ArrayBuffer[(Entidad,Double)]

  def continuar(k:Int): Unit

  def revisionTurno(): Unit

  def next_turn(): Option[Entidad]
}
