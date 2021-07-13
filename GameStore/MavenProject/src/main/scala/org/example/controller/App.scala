package org.example.controller;

import javafx.application.Application
import javafx.application.Application.launch
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.stage.StageStyle
import org.example.controller.App.mainStage
import org.example.view.LoginView

 class App() extends Application{

    override def start(primaryStage: Stage) {
         mainStage = primaryStage
         mainStage.initStyle(StageStyle.DECORATED);
         try {
             val fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"))
             val loginView = new LoginView()
             fxmlLoader.setController(loginView)
             val parent :Parent = fxmlLoader.load()
             val scene = new Scene(parent)
             mainStage.setScene(scene)
             mainStage.show()
         } catch {
             case ex:Exception=>{
                 println(ex)
             }
         }
     }

     def launchApp: Unit ={
         launch()
     }
 }

object App {
    private var mainStage: Stage=null
    def main(args: Array[String]): Unit = {
        val app= new App
        app.launchApp
    }

}
