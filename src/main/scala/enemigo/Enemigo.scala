package enemigo

import attributes.Attributes

/**A class that defines an enemy(Enemigo)
 *
 * As an extension of the trait EnemigoAttributes, an enemy is defined by its name, hp, attack, defense and weight
 *
 *
 * @param name the name of the enemy
 * @param hp the hp of the enemy
 * @param attack the attack of the enemy
 * @param defense the defense of the enemy
 * @param weight the weight of the enemy
 */
class Enemigo(val name: String,var hp: Int,var attack: Int,var defense: Int,var weight: Int)extends EnemigoAttributes {

}
