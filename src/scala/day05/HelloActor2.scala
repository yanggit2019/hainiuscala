package day05

import scala.actors.Actor

class HelloActor2 extends Actor {
  override def act(): Unit = {
    while (true){
      //1.通过receive可以接收到发送给actor的消息
      //然后通过receive偏函数和case实现匹配具体消息
      receive{
        //5.通过模式匹配获取消息
        case "start" => println("actor receive start")
      }
    }
  }
}

//3.创建伴生对象
object HelloActor2{
  def main(args: Array[String]): Unit = {
    val actor = new HelloActor2
    actor.start()
    //4.发送start消息
    actor ! "start"
  }
}