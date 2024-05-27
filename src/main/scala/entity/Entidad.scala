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


  protected def maxHeal(healing:Int,maxHealth:Int): Unit

  def attack(victim:Entidad): Unit
  protected def checkHealth(damage:Int): Unit

  protected def isAlive(): Int
}
