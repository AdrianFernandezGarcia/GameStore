package org.example.model

import javafx.embed.swing.SwingFXUtils
import javafx.geometry.{Insets, Pos}
import javafx.scene.control.ListCell
import javafx.scene.image.ImageView
import javafx.scene.layout.{HBox, VBox}
import javafx.scene.text.{Font, FontWeight, Text}

import javax.imageio.ImageIO

class GameCell extends ListCell[Game]  {

  private var image = new ImageView()
  private val title = new Text()
  private val platform= new Text()
  private var content= new HBox()
  def init: Unit ={
    title.setFont(Font.font("Verdana", FontWeight.BOLD, 16))
    platform.setFont(Font.font("Verdana", FontWeight.THIN, 14))
    val vbox= new VBox(title,platform)
    vbox.setPadding(new Insets(10))
    content= new HBox(image,vbox)
  }

  override def updateItem(item: Game, empty: Boolean): Unit = {
    super.updateItem(item, empty)

    if (!(empty || item == null)) {
      image.setImage(SwingFXUtils.toFXImage(item.getGamePicture, null))
      title.setText("\n"+item.getTitle+"\n")

      //Unwrapping the platform list and setting platform field´s text
      var platformString=""
      val listLength=item.getPlatforms.toArray.length
      for ( a<- 0 until listLength ){

        if(a==0 || a==listLength) platformString+=item.getPlatforms(a)

        else platformString+=item.getPlatforms(a)+", "

      }
      platform.setText(platformString)

      //Setting the image
      setGraphic(content)
    }
  }
}