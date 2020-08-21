package day05
import scala.actors.Actor

class HelloActor extends Actor {
  //1.相当于java的run()线程体
  override def act(): Unit = {
    println("do act")
    //3.添加一些额外功能
    for (i <- 1 to 5){
      println(i)
    }
  }
  
}

object HelloActor{
  def main(args: Array[String]): Unit = {
    val actor = new HelloActor
    //2.相当于启动线程
    actor.start()
  }
}