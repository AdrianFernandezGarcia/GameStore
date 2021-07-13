package org.example.view

import javafx.beans.property.{ReadOnlyBooleanWrapper, SimpleStringProperty}
import org.example.connections.JDBC_Connection
import org.example.model.Customer

import java.sql.{SQLException, Statement}

class LoginViewModel{
  private final val email = new SimpleStringProperty()
  private final val password= new SimpleStringProperty()
  private final val successfulLogin= new ReadOnlyBooleanWrapper()

  successfulLogin.bind(email.isNotEmpty.and(password.isNotEmpty))

  def emailProperty :SimpleStringProperty=email

  def passwordProperty:SimpleStringProperty=password

  def successfulLoginProperty: ReadOnlyBooleanWrapper=successfulLogin

  /**
   *
   * @return the customer trying to log in if email and password matches. If they don`t it returns null
   */
  def login():Customer={
    val query ="Select * from customers c where c.email like '"+emailProperty.get()+"';"
    var customer:Customer=null

    try{
      val connection=JDBC_Connection.getInstance("root","root").getConnection
      val statement :Statement= connection.createStatement()
      val resSet= statement.executeQuery(query)
      while(resSet.next()){
        if(resSet.getString(5).equals(passwordProperty.get())){
          val customerNumber= resSet.getLong(1)
          val firstName= resSet.getString(2)
          val surName= resSet.getString(3)
          val email= resSet.getString(4)
          val password= resSet.getString(5)
          val birthDate= resSet.getDate(6).toLocalDate
          customer= new Customer(customerNumber,firstName,surName,email,password,birthDate)
        }
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


}
