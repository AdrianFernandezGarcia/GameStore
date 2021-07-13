package org.example.view

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.{Button, CheckBox, TextField}
import org.example.model.{ScaleImage, ShowDialogUI}

import java.io.File
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

class AddGameView extends ShowDialogUI with ScaleImage{

  private val addGameViewModel= new AddGameViewModel
  @FXML
  private var tfTitle= new TextField()
  @FXML
  private var tfPrice= new TextField()
  @FXML
  private var cbPC= new CheckBox()
  @FXML
  private var cbPS= new CheckBox()
  @FXML
  private var cbXBox= new CheckBox()
  @FXML
  private var cbNintendo= new CheckBox()
  @FXML
  private var imageButton= new Button()

  private var pickedImage:File= null

  @FXML
  def submitAdd(): Unit ={
    val platformsList:List[String]=List()
    addGameViewModel.titleProperty.bind(tfTitle.textProperty())
    addGameViewModel.priceProperty.bind(tfPrice.textProperty())

    if(cbPC.isSelected) platformsList.+(cbPC.getText)
    if(cbPS.isSelected)platformsList.+(cbPS.getText)
    if(cbXBox.isSelected)platformsList+(cbXBox.getText)
    if(cbNintendo.isSelected)platformsList+(cbNintendo.getText)



    if(addGameViewModel.addGame()!=null){
      //back to adminview
    }

    else showDialog(AlertType.ERROR,"Error Saving Game","Something went wrong adding the game. Try it again.")

  }


  @FXML
  def pickImage(evt:ActionEvent): Unit ={
    val filter= new FileNameExtensionFilter("Images","jpg","png","jpeg")
    val fileChooser = new JFileChooser()
    fileChooser.setFileFilter(filter)
    fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES)

    val option = fileChooser.showOpenDialog(null)

    if (option == JFileChooser.APPROVE_OPTION) pickedImage = fileChooser.getSelectedFile



  }

  @FXML
  def submitGameRegister(evt:ActionEvent): Unit ={

  }

}
