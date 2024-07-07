package controller.states.initial

import controller.GameController
import controller.states.{AGameState, IGameState}

/** Class for initial state of the game
 */
class InitialState extends AGameState {
  /** Parameter that holds the state that should come next */
  private var changedState: Option[IGameState] = None

  /**Method for handling player´s choice
   * Can choose to add a player, or to begin the game(must have at least one character added)
   *
   * @param controller the game controller
   */
  override def handleInput(controller: GameController): Unit = {
    val choice: Int = controller.getNumericalInput()
    choice match{
      case 0 => changedState = None
      case x if (0<x && x<6) => controller.choosePlayers()
      case 6 => checkIfCanStart(controller)
      case _ => controller.notifyInvalidOption(choice)
    }
  }

  /**Method for updating controller to TurnCalculatingState, if player can/chose to do so
   *
   * @param controller the game controller
   */
  override def updateController(controller: GameController): Unit = {
    if(changedState.isDefined){
      controller.changeState(changedState.get)
    }
  }

  /**Private method for checking if when a player choose to start the game, they have enough players to do so
   *
   * @param controller the game controller
   */
  private def checkIfCanStart(controller: GameController): Unit = {
    if(controller.getAllPlayers().getMembers().isEmpty){
      controller.notifyInvalidOption(6)
    }
    else{
      changedState = Some(new TurnCalculatingState)
    }
  }



}
