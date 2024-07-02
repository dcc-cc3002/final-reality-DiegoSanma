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

/**Class for game Controller
 * Will be the main nucleus of all actions taking place during the game
 */
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
    private var stringChoice: String = ""
    private var weaponChoice: Int = 0
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
     * (Now, as view is not implemented as of yet, it is updated manually for tests)
     *
     * @return choice
     */

    def getNumericalInput():Int = {
       this.choice
    }

    /**Getter for weapon Choice
     *
     * @return this.weaponChoice
     */
    def getWeaponChoice(): Int = {
        this.weaponChoice
    }

    /**Method for updating the player´s choice
     * Would be updated by a view if it were implemented
     *
     * @param k the new choice
     */

    def updatePlayerChoice(k: Int): Unit = {
        this.choice = k
    }

    /**Method for updating player´s weapon choice
     * Would be updated by a view if it is implemented
     *
     * @param k the new choice
     */
    def updateWeaponChoice(k:Int): Unit = {
        this.weaponChoice = k
    }

    /**Method for asking for a name from the user
     * (As view is not implemented as of yet, there is only a set string being returned
     *
     * @return
     */
    def getStringInput(): String = {
        this.stringChoice
        /**val resp = StdIn.readLine()
        resp*/
    }

    /**Method for updating char choice
     *
     * @param char new char choice
     */

    def updateStringChoice(str:String): Unit = {
        this.stringChoice = str
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

    /**Method for when user is trying to add a weapon to their inventory
     * The weapon that they want to add wil depend on their weaponChoice
     *
     * @param selected the character trying to receive the weapon
     */
    def askUserForWeapon(selected:Attributes): Unit = {
        try {
            val choice: Int = this.getWeaponChoice()
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

    /**Method for when the user is trying to rename a weapon
     * The new namr will depend on their stringChoice
     *
     * @param selected the character whose active weapon is being renamed
     */
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

    /**Method for when the user is trying to drop a weapon from their inventory
     * The weapon they drop will depend on their weaponChoice, that will correspond to the
     * position of the weapon in the inventory they will drop
     *
     * @param selected the character that wants to drop their weapon
     */
    def userDropsWeapon(selected:Attributes): Unit = {
        val choice: Int = this.getWeaponChoice()
        if(choice>selected.getWeapons().length){
            this.notifyInvalidOption(choice)
        }
        else{
            selected.dropWeapon(choice)
        }
    }
    /** Method for notifying the use chose an invalid number as a choice */
    def notifyInvalidOption(choice:Int): Unit = {
    }

    /** Method for notifying user the have no weapon */
    def notifyNoWeapon(): Unit = {
    }

    /** Method for notifying user they dont have a magic weapon equipped */
    def notifyNotMagicWeapon(): Unit = {}

    /** Method for notifying the user they are attacking/casting a spell on a dead entity */
    def notifyNotAlive(victim:Entity):Unit = {}

    /** Method for notifying user they are trying to cast a spell with the wrong type of mage */
    def notifyNotRightMage():Unit = {}

    /** Method for notifying user they dont have enough mana to cast the spell they chose */
    def notifyNotEnoughMana(mana:Int): Unit = {}

    /** Method for notifying the user they are casting a spell on the wonrg type of entity */
    def notifyWrongTarget(): Unit = {}

    /** Method for notifying user their character cant equip a certain type fo weapon */
    def notifyCantAddWeapon(): Unit = {}

    /**Method for notifying user they are trying to rename an already named weapon
     *
     * @param str the name of the current weapon
     */
    def notifyAlreadyNamed(str: String): Unit = {}

    /** Method for notifying user the game has ended, as one of the two sides has been defeated */
    def notifyEndGame(): Unit = {
        println(s"The game has ended!")
    }

}