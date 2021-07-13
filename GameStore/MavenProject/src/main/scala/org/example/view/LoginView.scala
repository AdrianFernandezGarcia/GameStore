package org.example.view

import javafx.event.ActionEvent
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.{Button, PasswordField, TextField}
import javafx.scene.{Node, Parent, Scene}
import javafx.stage.Stage
import org.example.model.ShowDialogUI

class LoginView extends ShowDialogUI{
  val loginViewModel = new LoginViewModel
  @FXML
  private var email= new TextField()
  @FXML
  private var password= new PasswordField()
  @FXML
  private val loginButton= new Button()
  @FXML
  private val registerButton= new Button()


  @FXML
  def processLogin( evt:ActionEvent)={
    //bind email and password properties
    loginViewModel.emailProperty.bind(email.textProperty())
    loginViewModel.passwordProperty.bind(password.textProperty())



    if(email.textProperty().get().equals("admin"))  {
      try {
        val fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/admin.fxml"))
        val adminView = new AdminView
        fxmlLoader.setController(adminView)
        val parent: Parent = fxmlLoader.load()
        val scene = new Scene(parent)
        val app_stage = evt.getSource.asInstanceOf[Node].getScene.getWindow.asInstanceOf[Stage]
        app_stage.setScene(scene)
        app_stage.show()
      }
      catch {
        case ex:Exception=>{
          println(ex)
        }
      }
    }

    //check if login was successful
    //if it was not , show an error dialog
    else if(loginViewModel.login()==null) showDialog(AlertType.ERROR,"Login error","Wrong email or password. Try Again")

    //it it was, login and change to user scene
    else {
      try {
        val fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/user.fxml"))
        val userView = new UserView(loginViewModel.login())
        fxmlLoader.setController(userView)
        val parent: Parent = fxmlLoader.load()
        val scene = new Scene(parent)
        val app_stage = evt.getSource.asInstanceOf[Node].getScene.getWindow.asInstanceOf[Stage]
        app_stage.setScene(scene)
        app_stage.centerOnScreen()
        app_stage.show()
      }
      catch {
        case ex:Exception=>{
          println(ex)
        }
      }
    }

  }

  @FXML
  def toRegister(evt:ActionEvent)={
    try {
      val fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/register.fxml"))
      val registerView = new RegisterView()
      fxmlLoader.setController(registerView)
      val parent:Parent = fxmlLoader.load()
      val scene = new Scene(parent)
      val app_stage = evt.getSource.asInstanceOf[Node].getScene.getWindow.asInstanceOf[Stage]
      app_stage.setScene(scene)
      app_stage.show()

    } catch {
      case ex:Exception=>{
        println(ex)
      }
    }
  }

}
