package org.example.model

import java.time.LocalDate

class Customer(private var customerNumber:Long, private var firstName :String, private var surName :String, private var email: String,private var password:String,private var birthDate : LocalDate)  {

  //GETTERS & SETTERS
  def getNumber():Long= customerNumber
  def getFirstName():String=firstName
  def setFirstName(firstName:String):Unit=this.firstName=firstName
  def getSurName():String=surName
  def setSurname(surname:String):Unit=this.surName=surname
  def getEmail():String=email
  def setEmail(email:String):Unit=this.email=email
  def getPassword:String=password
  def SetPassword(pass:String):Unit=this.password=pass
  def getBirthDate():LocalDate=birthDate
  def setBirthDate(birthDate:LocalDate):Unit=this.birthDate=birthDate

  //Override parent´s toString
  override def toString: String = firstName


}
