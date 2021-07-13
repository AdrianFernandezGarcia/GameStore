package org.example.controller
import org.example.connections.JDBC_Connection
import org.example.model.{Customer, DAO}

import java.sql.{SQLException, Statement}
import scala.collection.mutable.ListBuffer

class CustomerDAO extends DAO [Customer,Long]{
  private final val SELECT_ALL_QUERY="Select * from customers;"


  override def get(id: Long): Customer = {
    val query ="Select * from customers where customerNumber="+id+";"
    var customer:Customer=null
    try{
      val connection= JDBC_Connection.getInstance("root","root").getConnection
      val statement :Statement= connection.createStatement()
      val resSet= statement.executeQuery(query)
      while(resSet.next()){
        val customerNumber= resSet.getLong(1)
        val firstName= resSet.getString(2)
        val surName= resSet.getString(3)
        val email= resSet.getString(4)
        val password= resSet.getString(5)
        val birthDate= resSet.getDate(6).toLocalDate

        customer=new Customer(customerNumber,firstName,surName,email,password,birthDate)
        connection.close()
      }
    }
    catch {
      case sqlEx: SQLException=>{
        sqlEx.printStackTrace()
      }
    }

    customer
  }

  override def getAll: ListBuffer[Customer] = {
    val customerList : ListBuffer[Customer]= ListBuffer()
    try{
      val connection= JDBC_Connection.getInstance("root","root").getConnection
      val statement :Statement= connection.createStatement()
      val resSet= statement.executeQuery(SELECT_ALL_QUERY)
      while(resSet.next()){
        val customerNumber= resSet.getLong(1)
        val firstName= resSet.getString(2)
        val surName= resSet.getString(3)
        val email= resSet.getString(4)
        val password= resSet.getString(5)
        val birthDate= resSet.getDate(6).toLocalDate

        customerList.+=(new Customer(customerNumber,firstName,surName,email,password,birthDate))
        connection.close()
      }
    }
    catch{
      case sqlEx:SQLException=>{
        sqlEx.printStackTrace()
      }
    }


    customerList

  }


  override def save(t: Customer): Unit = {

    try{
      val connection= JDBC_Connection.getInstance("root","root").getConnection
      val statement :Statement= connection.createStatement()
      statement.execute("INSERT INTO customers (firstName,surName,email,password,birthDate) \n"+
        "values ('"+t.getFirstName()+"','"+t.getSurName()+"','"+t.getEmail()+"', '"+t.getPassword+"' ," +
        "'"+java.sql.Date.valueOf(t.getBirthDate())+"');")

      connection.close()
    }
    catch {
      case sqlEx : SQLException=>{
        sqlEx.printStackTrace()
      }
    }

  }

  override def update(t: Customer): Unit ={
    try{
      val connection= JDBC_Connection.getInstance("root","root").getConnection
      val statement :Statement= connection.createStatement()
      statement.execute("update customers\n" +
        "set firstName= '"  +t.getFirstName()+   "' ,surName= '"+   t.getSurName()  +"' ,email= '"+   t.getEmail()   + "' ,password= '"+t.getPassword+"'"+
        ",birthDate= '"+java.sql.Date.valueOf(t.getBirthDate())+"'\n"
        +"where customerNumber = "+   t.getNumber()   +";")

      connection.close()
    }
    catch {
      case sqlEx : SQLException=>{
        sqlEx.printStackTrace()
      }
    }
  }

  override def delete(t: Customer): Unit = {
    try{
      val connection= JDBC_Connection.getInstance("root","root").getConnection
      val statement :Statement= connection.createStatement()
      statement.execute("DELETE from customers "+"where customerNumber="+t.getNumber()+";")
      connection.close()
    }
    catch {
      case sqlEx : SQLException=>{
        sqlEx.printStackTrace()
      }
    }
  }
}
