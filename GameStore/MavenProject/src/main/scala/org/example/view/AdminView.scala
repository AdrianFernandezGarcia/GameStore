package org.example.view

import javafx.event.ActionEvent
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.{Node, Parent, Scene}
import javafx.scene.control.Button
import javafx.stage.Stage

class AdminView {

  @FXML
  private val gameManagementButton= new Button()
  @FXML
  private val userManagementButton= new Button()


  @FXML
    def toGameManagement(evt:ActionEvent)={
    try {
      val fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/addgame.fxml"))
      val addGameView = new AddGameView
      fxmlLoader.setController(addGameView)
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

  @FXML
  def toUserManagement(evt:ActionEvent)={
    try {
      val fxmlLoader = new FXMLLoader(getClass().getResource("usermanagement"))
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
