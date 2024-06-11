package enemy

import attributes.{IMage, Mage}
import entity.{AEntity, Entity}
import status.IStatusEffect
import weapons.IMagicWeapon

/**A trait that defines the attributes that an enemy has
 *
 * The enemy´s attributes extends from the Entity traits
 * and also declares a getAttack method, for getting the attack parameter
 *
 * @author Diego San Martin
 */

trait EnemyAttributes extends AEntity{
    def getAttack(): Int

    def getMaxActionBar(): Int

    def getStatus(): Option[IStatusEffect]

    def setStatus(status:Option[IStatusEffect]): Unit

    def takeSpellDamage(mage:IMage,magicWeapon: IMagicWeapon)

}
