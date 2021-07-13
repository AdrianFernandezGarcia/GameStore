package org.example.controller

import org.example.model.{Customer, Game}

import scala.collection.mutable.ListBuffer

class GameManager {
  val gameDAO = new GameDAO()
  val customerList: ListBuffer[Customer] = ListBuffer()

  def getGame(index: Long): Game = gameDAO.get(index)

  def getAllGames(): ListBuffer[Game] = gameDAO.getAll

  def saveGame(game: Game): Unit = gameDAO.save(game)

  def updateGame(game: Game): Unit = gameDAO.update(game)

  def deleteGame(game: Game): Unit = gameDAO.delete(game)
}
