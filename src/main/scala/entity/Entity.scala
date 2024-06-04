package entity

import attributes.{Attributes, IMage, Mage}
import enemy.EnemyAttributes
import spells.{HealingLightSpells, IDarkSpells, ISpells, StatusLightSpells}
import turnscheduler.TurnScheduler

/**A trait that includes the methods that will be included in any Entidad
 */
trait Entity{
  def getName(): String
  def getHp(): Int
  def getDefense():Int
  def getWeight():Int
  def getMaxHealth(): Int

  def takedamagePlayer(agresor:Attributes): Unit

  def takedamageEnemy(agresor:EnemyAttributes): Unit


  protected def maxHeal(healing:Int,maxHealth:Int): Unit

  def attack(victim:Entity): Unit

  protected def checkHealth(damage:Int): Unit

  def checkifCharacter(): Unit

  def checkifEnemy(): Unit

  def isAlive(): Int

  def addToTurns(scheduler: TurnScheduler): Unit

  def removeFromTurns(scheduler: TurnScheduler) : Unit

  def getActionBar(): Int

  def addToActionBar(k:Int): Unit

  def heal(amountPercentage:Double): Unit

  def checkDarkInflictSpell(user:IMage,spell:IDarkSpells)

  def checkLightHealSpell(user:IMage,spell:HealingLightSpells)

  def checkLightStatusSpell(user:IMage,spell:StatusLightSpells)
}
