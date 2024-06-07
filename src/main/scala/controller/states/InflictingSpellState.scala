package controller.states

import attributes.{Attributes, IMage}
import controller.GameController
import entity.Entity
import exceptions.damage.{FriendlyFireException, NotEnoughManaException, UnaliveDamagedException}
import exceptions.mage.WrongMageException
import exceptions.weaponexceptions.{NoWeaponException, NotMagicWeaponException}
import spells.ISpells

class InflictingSpellState(mage:IMage,mageAsCharacter:Attributes,spell:ISpells,victim:Entity) extends AGameState {
  private var changedState: Option[IGameState] = None

  override def handleInput(controller: GameController): Unit = {
    try{
      mage.throwSpell(spell,victim)
      changedState = Some(new TurnCalculatingState())
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

  override def updateController(controller: GameController): Unit = {
    controller.changeState(changedState.get)
  }

}
