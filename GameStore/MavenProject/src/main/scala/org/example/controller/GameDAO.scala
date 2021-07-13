package org.example.controller

import org.example.connections.JDBC_Connection
import org.example.model.{DAO, Game}

import java.sql.{SQLException, Statement}
import java.util.Arrays
import scala.collection.mutable.ListBuffer

class GameDAO extends DAO[Game,Long]{

  override def get(id: Long): Game = {
    val query ="Select * from games where gameid="+id+";"
    var game:Game=null
    try{
      val connection= JDBC_Connection.getInstance("root","root").getConnection
      val statement :Statement= connection.createStatement()
      val resSet= statement.executeQuery(query)
      while(resSet.next()){
        val gameTitle= resSet.getString(2)
        val gamePrice= resSet.getDouble(3)
        val gamePlatform= resSet.getString(4)
        val imagePath= resSet.getString(5)
        val platformsList:List[String]= gamePlatform.split(",").toList
        game=new Game(0,gameTitle,gamePrice,platformsList)
        connection.close()
      }
    }
    catch {
      case sqlEx: SQLException=>{
        sqlEx.printStackTrace()
      }
    }
    game
  }
  override def getAll: ListBuffer[Game] = {
    val query ="Select * from games;"
    var games:ListBuffer[Game]=ListBuffer()
    try{
      val connection= JDBC_Connection.getInstance("root","root").getConnection
      val statement :Statement= connection.createStatement()
      val resSet= statement.executeQuery(query)
      while(resSet.next()){
        val gameTitle= resSet.getString(2)
        val gamePrice= resSet.getDouble(3)
        val gamePlatform= resSet.getString(4)
        val imagePath= resSet.getString(5)
        val platformsList:List[String]= gamePlatform.split(",").toList

        games.+=(new Game(0,gameTitle,gamePrice,platformsList))

      }
      connection.close()
    }
    catch {
      case sqlEx: SQLException=>{
        sqlEx.printStackTrace()
      }
    }
    games

  }

  override def save(t: Game): Unit = {
    try{
      val connection= JDBC_Connection.getInstance("root","root").getConnection
      val statement :Statement= connection.createStatement()
      statement.execute("INSERT INTO games (gameTitle,gamePrice,gamePlatform,gameImagePath) \n"+
        "values ('"+t.getTitle+"',"+t.getPrice+",'"+t.getPlatforms+"', '"+t.getPath+"';")

      connection.close()
    }
    catch {
      case sqlEx : SQLException=>{
        sqlEx.printStackTrace()
      }
    }
  }

  override def update(t: Game): Unit = {
    try{
      val connection= JDBC_Connection.getInstance("root","root").getConnection
      val statement :Statement= connection.createStatement()
      statement.execute("update customers\n" +
        "set gameTitle= '"  +t.getTitle+   "' ,gamePrice= "  +t.getPrice+  " ,gamePlatform= '"+   t.getPlatforms   + "' ,gameImagePath= '"+t.getPath+"'\n"

        +"where customerNumber = "+   t.getId()   +";")

      connection.close()
    }
    catch {
      case sqlEx : SQLException=>{
        sqlEx.printStackTrace()
      }
    }

  }

  override def delete(t: Game): Unit = {
    try{
      val connection= JDBC_Connection.getInstance("root","root").getConnection
      val statement :Statement= connection.createStatement()
      statement.execute("DELETE from customers "+"where customerNumber="+t.getId()+";")
      connection.close()
    }
    catch {
      case sqlEx : SQLException=>{
        sqlEx.printStackTrace()
      }
    }

  }
}
