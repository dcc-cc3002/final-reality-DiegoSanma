package enemigo

import entity.Entidad

/**A trait that defines the attributes that an enemy has
 *
 * The enemy´s attributes extends from the Entidad traits
 * and also includes an attack attribute
 *
 * @author Diego San Martin
 */

trait EnemigoAttributes extends Entidad{
    var attack: Int
}
