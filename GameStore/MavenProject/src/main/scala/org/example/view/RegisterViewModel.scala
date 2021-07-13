package org.example.view
import javafx.beans.property.{ReadOnlyBooleanWrapper, SimpleIntegerProperty, SimpleStringProperty, StringProperty}
import org.example.connections.JDBC_Connection
import org.example.controller.CustomerManager
import org.example.model.Customer

import java.sql.{SQLException, Statement}
import java.time.LocalDate

  class RegisterViewModel{
    private final val firstName = new SimpleStringProperty()
    private final val surName= new SimpleStringProperty()
    private final val email = new SimpleStringProperty()
    private final val password= new SimpleStringProperty()
    private final val birthDateYear= new SimpleStringProperty()
    private final val birthDateMonth= new SimpleStringProperty()
    private final val birthDateDay= new SimpleStringProperty()
    private final val successfulRegister= new ReadOnlyBooleanWrapper()

    //successfulRegister.bind(firstName.isNotEmpty.and(surName.isNotEmpty).and(email.isNotEmpty).and(password.isNotEmpty))

    def firstNameProperty: SimpleStringProperty= firstName

    def setFirstNameProperty(firstName :StringProperty):Unit= this.firstName.bind(firstName)

    def surNameProperty: SimpleStringProperty=surName

    def emailProperty :SimpleStringProperty=email

    def passwordProperty:SimpleStringProperty=password

    def birthDateYearProperty:SimpleStringProperty=birthDateYear

    def birthDateMonthProperty:SimpleStringProperty=birthDateMonth

    def birthDateDayProperty: SimpleStringProperty=birthDateDay

    def successfulLoginProperty: ReadOnlyBooleanWrapper=successfulRegister


    def register():Customer ={
      val manager= new CustomerManager
      var customer:Customer=null
      val query ="Select * from customers c where c.email like '"+emailProperty.get()+"';"

      try{
        val connection= JDBC_Connection.getInstance("root","root").getConnection
        val statement :Statement= connection.createStatement()
        val resSet= statement.executeQuery(query)
        if(resSet.next()){
          println("That email´s already been registered")
        }
        else{
          customer= new Customer(0,firstName.get(),surName.get(),email.get(),password.get()
            ,checkDate(birthDateYear.get(),birthDateMonth.get(),birthDateDay.get()))
          manager.saveCustomer(customer)

        }
        connection.close()
      }
      catch {
        case sqlEx: SQLException=>{
          sqlEx.printStackTrace()
        }
      }


      customer
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
