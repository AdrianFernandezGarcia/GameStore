package org.example.model

import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType

trait ShowDialogUI {

  def showDialog(alertType: AlertType,title:String,message:String): Unit ={
    val alert = new Alert(alertType)
    alert.setTitle(title)
    alert.setHeaderText(null)
    alert.setContentText(message)

    alert.showAndWait()
  }


}
