import scala.util.control.Breaks

object HelloWorld {
  def main(args: Array[String]): Unit = {
    println("hello,world")

    val breaks = new Breaks
    //实现break操作
    breaks.breakable(
      for (i <- 1 to 5){
        if (i ==3) breaks.break()
        print(s"${i} ")
      }
    )
    
     
  }

}
