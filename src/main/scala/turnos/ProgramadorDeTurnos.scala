package turnos

import attributes.{Attributes, Mage}
import enemigo.Enemigo

import scala.collection.mutable.ArrayBuffer

/** A class that calculates the turns taken by each characters and enemy during a battle sequence
 *
 * @tparam players an Array with all the players participating
 * @tparam enemies an Array with all the enemies participating
 *
 * @example
 * var turnos = new ProgramadorDeTurnos(ArrayBuffer[magoblanco,ninja], ArrayBuffer[goblin,golem])
 * println(s"The name of the first enemy is ${enemies[1].name}"), should be "goblin"
 * println(s"The name of the second player is ${players[2].name}"), should be "ninja"
 *
 * @author Diego San Martin
 */

class ProgramadorDeTurnos (val players: ArrayBuffer[Attributes], val enemies: ArrayBuffer[Enemigo]){
  /** A method for adding either players or enemies to the the turn calculator
   *
   * The function agregar receives a player or enemy and adds it to its corresponding array
   * It adds the corresponding player to the end of their array
   * If that character is already inside the array, no one is added adn the array stays the same
   */

  def agregar(added: Any): Unit = {
    if(!(added.isInstanceOf[Attributes]) || !(added.isInstanceOf[Enemigo])){
      return
    }
    if(added.isInstanceOf[Attributes]) {
      if (!(players.contains(added))) {
        players.addOne(added.asInstanceOf[Attributes])
      }
    }
    else {
      if (!(enemies.contains(added))) {
        enemies.addOne(added.asInstanceOf[Enemigo])
      }
    }
  }
}
