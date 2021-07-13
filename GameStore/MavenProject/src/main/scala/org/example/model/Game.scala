package org.example.model
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO


class Game (private var gameid :Long,private var title:String, private var price:Double,private var platform:List[String]){

  private val gamePicture:BufferedImage=ImageIO.read(new File("src/main/resources/images/"+title+".jpg"))
  private val picturePath:String="src/main/resources/images/"+title+".jpg"

  //GETTERS AND SETTERS
  def getId():Long= gameid

  def getTitle:String=title
  def setTitle(title:String)=this.title=title

  def getPrice:Double=price
  def setPrice(price:Double)=this.price=price

  def getPlatforms:List[String]=platform
  def setPlatforms(platform:List[String])=this.platform=platform

  def getGamePicture:BufferedImage=gamePicture
  def getPath:String=picturePath

  override def toString: String = this.title
}
