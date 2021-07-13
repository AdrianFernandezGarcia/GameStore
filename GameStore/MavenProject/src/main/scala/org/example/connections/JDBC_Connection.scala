package org.example.connections

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException



/**
 *
 * @author Adrián
 */
object JDBC_Connection {
  private var instance:JDBC_Connection = null

  @throws[SQLException]
  def getInstance(username:String,password:String): JDBC_Connection = {
    if (instance == null) instance = new JDBC_Connection(username, password)
    else if (instance.getConnection.isClosed) instance = new JDBC_Connection(username, password)

    instance
  }
}

class JDBC_Connection(username:String,password:String) {

  private val url = "jdbc:mysql://localhost:3306/gamestore?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"

  @throws[SQLException]
  def getConnection: Connection = DriverManager.getConnection(url, username, password)
}
