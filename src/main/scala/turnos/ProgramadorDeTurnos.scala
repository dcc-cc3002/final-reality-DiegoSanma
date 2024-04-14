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

class ProgramadorDeTurnos (val players: ArrayBuffer[Attributes], val enemies: ArrayBuffer[Enemigo]) {
   /** A method for adding either players or enemies to the the turn calculator
   *
   * The function agregar receives a player or enemy and adds it to its corresponding array
   * It adds the corresponding player to the end of their array
   * If that character is already inside the array, no one is added adn the array stays the same
   */

    def agregar(added: Any): Unit = {
    if (!(added.isInstanceOf[Attributes]) || !(added.isInstanceOf[Enemigo])) {
      return
    }
    if (added.isInstanceOf[Attributes]) {
      if (!(players.contains(added))) {
        players.addOne(added.asInstanceOf[Attributes])
        registro.addOne(0)
      }
    }
    else {
      if (!(enemies.contains(added))) {
        enemies.addOne(added.asInstanceOf[Enemigo])
        registro.addOne(0)
      }
    }
  }

    /** A method for removing either players or enemies to the the turn calculator
     *
     * The function remover receives a player or enemy and removes it from their corresponding array
     * It removes him from the array
     * If that character in not inside the array, the array stays the same
     */

    def sacar(removed: Any): Unit = {
      if (!(removed.isInstanceOf[Attributes]) || !(removed.isInstanceOf[Enemigo])) {
        return
      }
      if (removed.isInstanceOf[Attributes]) {
        if ((players.contains(removed))) {
          val index = players.indexOf(removed)
          players.remove(index)
          registro.remove(index)
        }
      }
      else {
        if (!(enemies.contains(removed))) {
          val index = enemies.indexOf(removed)
          enemies.remove(index)
          registro.remove(index)
        }
      }
    }


    /** A method that calculates the action bar for each character in combat
     *
     * The function returns a buffer array with the tuple of the character and their max action bar
     *
     * The action bar is calculated differently for enemies and players
     * For players it is equal to their weight added with half their weapon´s weight
     * For enemies, it is equal to their weight
     *
     */


    def Barra():ArrayBuffer[(Any,Double)] ={
        var barraMaxima: ArrayBuffer[(Any,Double)] = ArrayBuffer()
        for(i <- 0 until players.length){
          if(players(i).weapon.isDefined) {
            var barra: Double = players(i).weight + players(i).weapon.get.weight
            barraMaxima += ((players(i), barra): (Any, Double))
          }
          else{
            var barra: Double = players(i).weight
            barraMaxima += ((players(i), barra): (Any, Double))
          }
        }
        for(i <- 0 until enemies.length)  {
          var barra: Double = enemies(i).weight
          barraMaxima += ((enemies(i), barra): (Any, Double))
      }
      return barraMaxima
    }

    /** A paramater that holds the values for everyone´s action bar during combat */
    var registro: ArrayBuffer[Int] = ArrayBuffer.fill(players.length + enemies.length)(0)

    /** A method that adds a constant value to each character currently in combat*
     * The function takes a paramater th Integer k, that is added to each action bar
     *
     */
     def continuar(k:Int): Unit = {
       for(i<-0 until registro.length){
          registro(i) = registro(i) + k
       }
     }

}
