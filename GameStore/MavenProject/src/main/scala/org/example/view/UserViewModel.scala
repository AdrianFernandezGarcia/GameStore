package org.example.view

import org.example.model.Customer

class UserViewModel() {

  private final var loggedUser:Customer = null

  def setLoggedUserProperty (customer:Customer)=loggedUser=customer


}
