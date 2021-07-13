package org.example.controller

import javafx.stage.Stage
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.StageStyle
import org.example.view.LoginView
import scalafx.application.JFXApp3


object GameShop extends JFXApp3  {

  override def start(): Unit = {
    val mainStage = new Stage()
    mainStage.initStyle(StageStyle.UNDECORATED)
    try {
      val fxmlLoader = new FXMLLoader(getClass.getResource("fxml/login.fxml"))
      val loginView = new LoginView
      fxmlLoader.setController(loginView)
      val parent = fxmlLoader.load.asInstanceOf[Parent]
      val scene = new Scene(parent)
      mainStage.setScene(scene)
      mainStage.show
    } catch {
      case e: Exception =>
        System.out.println(e)
        //logger.log(Level.FATAL, "Fatal error. Application can not start ...");
        System.exit(1)
    }
  }


}
