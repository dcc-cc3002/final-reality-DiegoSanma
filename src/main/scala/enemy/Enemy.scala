package enemy

import attributes.Attributes
import entity.AEntity

/**A class that defines an enemy(Enemigo)
 *
 * As an extension of the trait EnemigoAttributes, an enemy is defined by its name, hp, attack, defense and weight
 *
 * @constructor creates an enemy with name, hp, attack, defense and weight
 *
 * @param name the name of the enemy
 * @param hp the hp of the enemy
 * @param defense the defense of the enemy
 * @param weight the weight of the enemy
 * @param attack the attack of the enemy
 *
 * @example
 * var enemy = new Enemy("Fat",20,30,100,150)
 * println(s"The name of the enemy is ${enemy.name}"), should be "Fat"
 *
 * @author Diego San Martin
 */

class Enemy(val name: String, var hp: Int, var defense: Int, var weight: Int, var attack: Int)
  extends AEnemy(name,hp,defense,weight,attack) {

}
