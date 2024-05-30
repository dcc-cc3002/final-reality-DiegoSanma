package enemigo

import attributes.Mage
import entity.{AEntidad, Entidad}

/**A trait that defines the attributes that an enemy has
 *
 * The enemy´s attributes extends from the Entidad traits
 * and also declares a getAttack methid, for getting the attack parameter
 *
 * @author Diego San Martin
 */

trait EnemigoAttributes extends AEntidad{
    def getAttack(): Int

    def getMaxActionBar(): Int

    def takeSpellDamage(mage:Mage)

}
