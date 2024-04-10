package enemigo

/**A trait that defines the attributes that an enemy has
 *
 * The enemy´s attributes include a name, hitpoints, attack, defense and weight(in kilos)
 *
 * @author Diego San Martin
 */

trait EnemigoAttributes {
    val name: String
    var hp: Int
    var attack: Int
    var defense: Int
    var weight: Int
}
