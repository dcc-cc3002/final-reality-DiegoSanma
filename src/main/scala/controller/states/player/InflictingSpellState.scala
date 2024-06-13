package controller.states.player

import attributes.{Attributes, IMage}
import controller.GameController
import controller.states.initial.TurnCalculatingState
import controller.states.last.CheckEndState
import controller.states.{AGameState, IGameState}
import entity.Entity
import exceptions.damage.{FriendlyFireException, NotEnoughManaException, UnaliveDamagedException}
import exceptions.mage.WrongMageException
import exceptions.weaponexceptions.{NoWeaponException, NotMagicWeaponException}
import spells.ISpells

/**Class for inflicting the effect/damage of the spell on the victim
 *
 * @param mage mage casting the spell
 * @param mageAsCharacter mage as character
 * @param spell spell being casted
 * @param victim the victim of the spell
 */
class InflictingSpellState(mage:IMage,mageAsCharacter:Attributes,spell:ISpells,victim:Entity) extends AGameState {
  /** Parameter that hold the next state */
  private var changedState: Option[IGameState] = None

  /**Method for handling the consecuences fo throwing spell on chosen entity
   * In this method, there are many exceptions that can be thrown, and each one will
   * throw back the user to the state where they need to change their choice
   *
   * For example, if they don´t have enough mana, they have to go back to CharacterChoiceState,as they can
   * either cast a different spell or attack, as they dont have enough mana for any spell
   *
   * If they are the worng type of mage, they are sent back to MageSpellState, to choose a different
   * type of spell to cast
   *
   * Etc...
   *
   * @param controller the game controller
   */
  override def handleInput(controller: GameController): Unit = {
    try{
      mage.throwSpell(spell,victim)
      changedState = Some(new CheckEndState)
    }
    catch {
      case e1: NoWeaponException =>
        controller.notifyNoWeapon()
        changedState = Some(new ChangeWeaponState(mageAsCharacter))
      case e2: NotMagicWeaponException =>
        controller.notifyNotMagicWeapon()
        changedState = Some(new ChangeWeaponState(mageAsCharacter))
      case e3: UnaliveDamagedException =>
        controller.notifyNotAlive(victim)
        changedState = Some(new CastingSpellState(mage,mageAsCharacter,spell))
      case e4: WrongMageException =>
        controller.notifyNotRightMage()
        changedState = Some(new MageSpellState(mage,mageAsCharacter))
      case e5: NotEnoughManaException =>
        controller.notifyNotEnoughMana(mage.getMana())
        changedState = Some(new CharacterChoiceState(mageAsCharacter))
      case e6: FriendlyFireException =>
        controller.notifyWrongTarget()
        changedState = Some(new CastingSpellState(mage,mageAsCharacter,spell))
    }
  }

  /**Method for updating the controller´s state
   *
   * @param controller the game controller
   */
  override def updateController(controller: GameController): Unit = {
    controller.changeState(changedState.get)
  }


}
