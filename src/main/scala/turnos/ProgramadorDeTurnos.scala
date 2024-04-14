package turnos

import attributes.Attributes
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

class ProgramadorDeTurnos (var players: ArrayBuffer[Attributes],var enemies: ArrayBuffer[Enemigo]){

}
