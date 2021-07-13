package org.example.model

import java.awt.Color
import java.io.File

trait ScaleImage {

  import javax.imageio.ImageIO
  import java.awt.Image
  import java.awt.image.BufferedImage
  import java.io.IOException

  def scaleImage(file: File,fileName:String): File = {
    val width, height=150
    val writingPath = "src/main/resources/images/"+fileName+".jpg"
    try {
      val image = ImageIO.read(file)
      val rescaledImage = image.getScaledInstance(width,height, Image.SCALE_SMOOTH)
      val buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
      buffer.getGraphics.drawImage(rescaledImage, 0, 0, new Color(0, 0, 0), null)
      ImageIO.write(buffer, "jpg", new File(writingPath))
    } catch {
      case ex: IOException =>

    }
    new File(writingPath)
  }

}
