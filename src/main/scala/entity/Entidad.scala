package entity

import attributes.Attributes
import enemigo.EnemigoAttributes

/**A trait that includes the methods that will be included in any Entidad
 */
trait Entidad{
  def getName(): String
  def getHp(): Int
  def getDefense():Int
  def getWeight():Int

  def takedamagePlayer(agresor:Attributes): Unit

  def takedamageEnemy(agresor:EnemigoAttributes): Unit

  def takedamage(damage:Int)

  def attack(victim:Entidad): Unit
}
