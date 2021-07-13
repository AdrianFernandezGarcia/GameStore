package org.example.view

import javafx.event.ActionEvent
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.{Button, PasswordField, TextField}
import javafx.scene.{Node, Parent, Scene}
import javafx.stage.Stage

class RegisterView {
  val registerViewModel = new RegisterViewModel
  @FXML
  private var tfFirstName= new TextField()
  @FXML
  private var tfSurName= new TextField()
  @FXML
  private var tfBirthDateYear= new TextField()
  @FXML
  private var tfBirthDateMonth= new TextField()
  @FXML
  private var tfBirthDateDay= new TextField()
  @FXML
  private var tfEmail= new TextField()
  @FXML
  private var pfPassword= new PasswordField()
  @FXML
  private var registerButton= new Button()


  @FXML
  def checkRegister(evt:ActionEvent): Unit ={
    registerViewModel.setFirstNameProperty(tfFirstName.textProperty())
    registerViewModel.surNameProperty.bind(tfSurName.textProperty())
    registerViewModel.birthDateYearProperty.bind(tfBirthDateYear.textProperty())
    registerViewModel.birthDateMonthProperty.bind(tfBirthDateMonth.textProperty())
    registerViewModel.birthDateDayProperty.bind(tfBirthDateDay.textProperty())
    registerViewModel.emailProperty.bind(tfEmail.textProperty())
    registerViewModel.passwordProperty.bind(pfPassword.textProperty())
    if(registerViewModel.register()!=null){
      toLogin(evt)
    }
  }

  def toLogin(evt:ActionEvent): Unit ={
    try {
      val fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"))
      val loginView = new LoginView()
      fxmlLoader.setController(loginView)
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
