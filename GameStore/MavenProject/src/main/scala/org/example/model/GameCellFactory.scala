package org.example.model

import javafx.scene.control.{ListCell, ListView}
import javafx.util.Callback


class GameCellFactory extends Callback[ListView[Game], ListCell[Game]] {
  override def call(param: ListView[Game]): ListCell[Game] = {
    val gameCell=new GameCell
    gameCell.init
    gameCell
  }
}
