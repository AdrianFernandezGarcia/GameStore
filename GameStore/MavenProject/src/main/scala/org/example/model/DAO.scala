package org.example.model

import scala.collection.mutable.ListBuffer

trait DAO[T, K] {

  def get(id: K): T

  def getAll: ListBuffer[T]

  def save(t: T): Unit

  def update(t: T): Unit

  def delete(t: T): Unit
}
