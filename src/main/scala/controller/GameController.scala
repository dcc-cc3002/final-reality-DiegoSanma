package controller

import attributes.Attributes
import axe.Axe
import bow.Bow
import controller.observers.{AttackObserver, IObservers, StatusObserver}
import controller.states.IGameState
import controller.states.initial.InitialState
import enemy.EnemyAttributes
import entity.Entity
import exceptions.weaponexceptions.{AlreadyNamedException, FullInventoryException, InvalidWeaponTypeException}
import gameStarter.GameStarter
import party.Party
import staff.Staff
import sword.Sword
import turnscheduler.{ITurnScheduler, TurnScheduler}
import wand.Wand
import weapons.TWeapons

import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn
import scala.util.Random

class GameController {
    /** All parameters necessary for the game controller to work */
    private var state: IGameState = null
    private val observers: ArrayBuffer[IObservers] = new ArrayBuffer[IObservers].empty
    private val model: ITurnScheduler = new TurnScheduler(new ArrayBuffer[Attributes].empty,new ArrayBuffer[EnemyAttributes].empty)
    private val beginGame: GameStarter = new GameStarter(this,model)
    private val allPlayers: Party = new Party(new ArrayBuffer[Option[Attributes]].empty)
    private val allEnemies: ArrayBuffer[EnemyAttributes] = new ArrayBuffer[EnemyAttributes].empty
    /** Parameters necessary for testing when player input is required(Changing State) */
    private var choice: Int = 0
    init()

    private def init(): Unit = {
        observers+= new AttackObserver
        observers+= new StatusObserver
        state = new InitialState()
        beginGame.createEnemies()
        for(i <- model.getEnemies()){
            allEnemies.addOne(i)
        }
    }

    /**Method for choosing players to add to combat
     * Will only allow a maximum of 3 players
     */
    def choosePlayers(): Unit = {
        if(allPlayers.getMembers().length<3){
            beginGame.chooseCharacter()
            allPlayers.addMember(model.getPlayers()(model.getPlayers().length-1))
        }
    }

    /**Method for when controller has to handle an input, delegates to state to tell what to do
     *
     */

    def handleInput(): Unit ={
        state.handleInput(this)
    }

    /**Method for getting a numerical input from the user
     * (Now, as view is not implemented as of yet, it simply uses a specific array with numbers, to check)
     *
     * @return choice
     */

    def getNumericalInput():Int = {
       this.choice
        /**val resp = StdIn.readLine()
        resp.toInt*/
    }

    /**Method for updating the player´s choice
     *
     * @param k the new choice
     */

    def updatePlayerChoice(k: Int): Unit = {
        this.choice = k
    }

    /**Method for asking for a name from the user
     * (As view is not implemented as of yet, there is only a set string being returned
     *
     * @return
     */
    def getStringInput(): String = {
        "Name"
        /**val resp = StdIn.readLine()
        resp*/
    }

    /**Method for telling the controller to update its status, delegates that to the state, where it checks if
     * should pass to another state or not
     */

    def update(): Unit={
        state.updateController(this)
    }

    /**Getter for game´s current state
     *
     * @return this.state
     */
    def getState(): IGameState = {
        this.state
    }

    /**Method for changing the state in the game controller
     *
     * @param state_ the state to be changed as
     */
    def changeState(state_ :IGameState): Unit = {
        this.state = state_
    }


    /**Getter for model/turn scheduler used by game controller
     *
     * @return this.model
     */
    def getModel(): ITurnScheduler={
        this.model
    }

    /**Getter for party of characters in game
     *
     * @return this.allPlayers
     */
    def getAllPlayers(): Party = {
        this.allPlayers
    }

    /**Getter for all enemies in game
     *
     * @return this.allEnemies
     */
    def getAllEnemies(): ArrayBuffer[EnemyAttributes] = {
        this.allEnemies
    }

    /**Method for checking if the game has finished, or if one of the teams was defeated completely
     *
     * @return true, if players or enemies was defeated
     *         false, if both are still alive
     */

    def checkIfFinished(): Boolean = {
        var enemiesAlive: Int = 0
        for(i<-allEnemies){
            enemiesAlive += i.isAlive()
        }
        if(this.allPlayers.defeated() == 0 || enemiesAlive == 0){
            true
        }
        else{
            false
        }
    }
    /**Method for getting a random enemies choice(for testing, left it at 1 to know what choice they will make)
     *
     * @param range the range in which the enemy can decide
     *
     * @return new Random().nextInt(range)
     */

    def getEnemyChoice(range:Int): Int = {
        0
        /**new Random().nextInt(range)*/
    }

    def askUserForWeapon(selected:Attributes): Unit = {
        try {
            val choice: Int = this.getNumericalInput()
            choice match {
                case 1 => selected.receiveWeapon(new Sword("", 100, 100))
                case 2 => selected.receiveWeapon(new Axe("", 80, 80))
                case 3 => selected.receiveWeapon(new Bow("", 30, 50))
                case 4 => selected.receiveWeapon(new Wand("", 25, 20, 50))
                case 5 => selected.receiveWeapon(new Staff("", 50, 85, 70))
                case _ => this.notifyInvalidOption(choice)
            }
        }
        catch {
            case e0: FullInventoryException => this.notifyCantAddWeapon()
            case e: InvalidWeaponTypeException => this.notifyCantAddWeapon()
        }
    }

    def askForName(selected:Attributes): Unit = {
        if(selected.getActiveWeapon().isEmpty){
            this.notifyNoWeapon()
        }
        else{
            val choice: String = this.getStringInput()
            try{
                selected.getActiveWeapon().get.rename(choice)
            }
            catch {
                case e: AlreadyNamedException => this.notifyAlreadyNamed(selected.getActiveWeapon().get.getWeaponName())
            }
        }
    }

    def userDropsWeapon(selected:Attributes): Unit = {
        val choice: Int = this.getNumericalInput()
        selected.dropWeapon(choice)
    }
    def notifyInvalidOption(choice:Int): Unit = {
    }

    def notifyNoWeapon(): Unit = {
    }

    def notifyNotMagicWeapon(): Unit = {}

    def notifyNotAlive(victim:Entity):Unit = {}

    def notifyNotRightMage():Unit = {}

    def notifyNotEnoughMana(mana:Int): Unit = {}

    def notifyWrongTarget(): Unit = {}

    def notifyCantAddWeapon(): Unit = {}

    def notifyAlreadyNamed(str: String): Unit = {}

    def notifyEndGame(): Unit = {
        println(s"The game has ended!")
    }

}