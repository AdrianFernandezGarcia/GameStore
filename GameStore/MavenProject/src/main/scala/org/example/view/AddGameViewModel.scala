package org.example.view

import javafx.beans.property.{SimpleListProperty, SimpleStringProperty}
import javafx.collections.ObservableList
import org.example.connections.JDBC_Connection
import org.example.controller.{CustomerManager, GameManager}
import org.example.model.Game

import java.sql.{SQLException, Statement}
import java.time.LocalDate

class AddGameViewModel {
  private final val title = new SimpleStringProperty()
  private final val price= new SimpleStringProperty()
  private final val platforms = new SimpleListProperty[String]()
  private final val imagePath= new SimpleStringProperty()

  //successfulRegister.bind(firstName.isNotEmpty.and(surName.isNotEmpty).and(email.isNotEmpty).and(password.isNotEmpty))

  def titleProperty: SimpleStringProperty= title

  def priceProperty: SimpleStringProperty=price

  def platformsProperty:SimpleListProperty[String]=platforms

  def pathProperty:SimpleStringProperty=imagePath

  def addGame():Boolean ={
    val manager= new GameManager
    var successful=false
    val query ="Select * from games g where g.title like '"+titleProperty.get()+"';"

    try{
      val connection= JDBC_Connection.getInstance("root","root").getConnection
      val statement :Statement= connection.createStatement()
      val resSet= statement.executeQuery(query)
      if(resSet.next()){
        println("That game´s already been registered")
      }
      else{
        val platformList:ObservableList[String]=  platformsProperty.get()
        manager.saveGame(new Game(0,titleProperty.get(),priceProperty.get().toDouble,platformList.asInstanceOf[List[String]]))
        successful=true
      }
      connection.close()
    }
    catch {
      case sqlEx: SQLException=>{
        sqlEx.printStackTrace()
      }
    }

    successful
  }

  def checkDate(dateYear:String,dateMonth:String,dateDay:String): LocalDate ={
    val listMonths31= List(1,3,5,7,8,10,12)
    val listMonths30= List(4,6,9,11)
    var birthDate:LocalDate=null
    val birthDateYearInt=dateYear.toInt
    val birthDateMonthInt=dateMonth.toInt
    val birthDateDayInt=dateDay.toInt

    //the date´s year´ll be correct if it´s a number of 4 digits between 1900 and the current year
    if(!((dateYear.length==4)&&(birthDateYearInt>1900)&&(birthDateYearInt<LocalDate.now().getYear))){
      println("Date year has to be a number of 4 digits between 1900 and the current year ")
      birthDate
    }
    else if(!dateMonth.matches("0[1-9]|1[012]")){
      println("Month date has to be a number of 2 digits between 1 and 12")
      birthDate
    }
    else if(listMonths31.contains(dateMonth)&& !((birthDateDayInt>0)&&(birthDateDayInt<31))){
      println("Wrong month 31")
      birthDate
    }
    else if(listMonths30.contains(dateMonth) && !((birthDateDayInt>0)&&(birthDateDayInt<30))){
      println("Wrong month 30")
      birthDate
    }
    else if(dateMonth==2 && !((birthDateDayInt>0)&&(birthDateDayInt<29))){
      println("Wrong month feb")
      birthDate
    }

    else{
      birthDate= LocalDate.of(birthDateYearInt,birthDateMonthInt,birthDateDayInt)
      birthDate
    }

  }


}
