package org.example.controller

import org.example.model.Customer

import scala.collection.mutable.ListBuffer

class CustomerManager {
  val customerDAO = new CustomerDAO()
  val customerList: ListBuffer[Customer] = ListBuffer()

  def getCustomer(index: Long): Customer = customerDAO.get(index)

  def getAllCustomers(): ListBuffer[Customer] = customerDAO.getAll

  def saveCustomer(customer: Customer): Unit = customerDAO.save(customer)

  def updateCustomer(customer: Customer): Unit = customerDAO.update(customer)

  def deleteCustomer(customer: Customer): Unit = customerDAO.delete(customer)

}