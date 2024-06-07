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
import staff.Staff
import sword.Sword
import turnscheduler.{ITurnScheduler, TurnScheduler}
import wand.Wand
import weapons.TWeapons

import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn
import scala.util.Random

class GameController {
    private var state: IGameState = null
    private val observers: ArrayBuffer[IObservers] = new ArrayBuffer[IObservers].empty
    private val model: ITurnScheduler = new TurnScheduler(new ArrayBuffer[Attributes].empty,new ArrayBuffer[EnemyAttributes].empty)
    private val weaponInGame: ArrayBuffer[TWeapons] = new ArrayBuffer[TWeapons].empty
    private val numbers: ArrayBuffer[Int] = ArrayBuffer(9,1,3,5,2,4)
    private var next: Int = 0
    init()

    private def init(): Unit = {
        observers+= new AttackObserver
        observers+= new StatusObserver
        state = new InitialState()
        val beginGame = new GameStarter(this,model)
        beginGame.chooseCharacters(model)
        beginGame.createEnemies(model)
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
     * @return number
     */

    def getNumericalInput():Int = {
        if(this.next >5){
            this.next = 0
        }
        val number: Int = this.numbers(next)
        next +=1
        number
        /**val resp = StdIn.readLine()
        resp.toInt*/
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


    def getModel(): ITurnScheduler={
        this.model
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

}