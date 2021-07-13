package org.example.view

import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.FXML
import javafx.scene.control.{ListCell, ListView, MenuButton}
import javafx.scene.image.ImageView
import javafx.scene.layout.Pane
import org.example.controller.GameDAO
import org.example.model.{Customer, Game, GameCellFactory}

class UserView (customer:Customer){

  private val userViewModel = new UserViewModel
  private val gameDAO= new GameDAO
  @FXML
  private var dropMenu= new MenuButton()
  @FXML
  private var gameList= new ListView[Game]()
  private val data:ObservableList[Game] = FXCollections.observableArrayList()

  @FXML
  def initialize: Unit ={
    dropMenu.setText(customer.getFirstName())


    gameDAO.getAll.foreach(game => {
      data.add(game)
    })

    gameList.setItems(data)
    gameList.setCellFactory(new GameCellFactory)

  }
  }


