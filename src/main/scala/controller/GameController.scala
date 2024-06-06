package controller

import attributes.Attributes
import controller.observers.{AttackObserver, IObservers, StatusObserver}
import controller.states.{IGameState, InitialState}
import enemy.EnemyAttributes
import gameStarter.GameStarter
import turnscheduler.{ITurnScheduler, TurnScheduler}

import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn

class GameController {
    private var state: IGameState = null
    private val observers: ArrayBuffer[IObservers] = new ArrayBuffer[IObservers].empty
    private val model: ITurnScheduler = new TurnScheduler(new ArrayBuffer[Attributes].empty,new ArrayBuffer[EnemyAttributes].empty)
    init()

    private def init(): Unit = {
        observers+= new AttackObserver
        observers+= new StatusObserver
        state = new InitialState()
        val beginGame = new GameStarter(this,model)
        beginGame.chooseCharacters(model)
        beginGame.createEnemies(model)
    }

    def handleInput(): Unit ={
        state.handleInput(this)
    }

    def getNumericalInput():Int = {
        val resp = StdIn.readLine()
        resp.toInt
    }

    def getStringInput(): String = {
        val resp = StdIn.readLine()
        resp
    }

    def update(): Unit={
        state.updateController(this)
    }

    def notifyInvalidOption(choice:Int): Unit = {
    }

    def changeState(state_ :IGameState): Unit = {
        this.state = state_
    }

    def getModel(): ITurnScheduler={
        this.model
    }

}