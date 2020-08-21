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
        case "id1" => {
          println("actor receive id1,send id2")
          //10.加入延时
          Thread.sleep(2000)
          //7.返回异步无返回消息
          sender ! "id2"
        }
          
      }
    }
  }
}

//3.创建伴生对象
object HelloActor2{
  def main(args: Array[String]): Unit = {
    val actor = new HelloActor2
    actor.start()
    //4.发送start消息 异步无返回消息
    actor ! "start"
    //6.发送同步消息
    val res1 = actor !? "id1"
    //8.强转
    val str = res1.asInstanceOf[String]
    //9.打印
    println(str)
  }
}