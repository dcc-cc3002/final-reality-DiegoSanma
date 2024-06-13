package entity

import attributes.{Attributes, IMage, Mage}
import enemy.EnemyAttributes
import spells.{HealingLightSpells, IDarkSpells, ISpells, StatusLightSpells}
import turnscheduler.TurnScheduler
import weapons.IMagicWeapon

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

  def checkHealth(damage:Int): Unit

  def isAlive(): Int

  def addToTurns(scheduler: TurnScheduler): Unit

  def removeFromTurns(scheduler: TurnScheduler) : Unit

  def getActionBar(): Int

  def addToActionBar(k:Int): Unit

  def heal(amountPercentage:Double): Unit

  def checkDarkInflictSpell(user:IMage,spell:IDarkSpells,magicWeapon: IMagicWeapon)

  def checkLightHealSpell(user:IMage,spell:HealingLightSpells)

  def checkLightStatusSpell(user:IMage,spell:StatusLightSpells,magicWeapon: IMagicWeapon)
}
