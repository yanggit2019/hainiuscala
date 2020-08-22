package day05

import scala.actors.{Actor, Future}

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
        case AsyncSendAndReturnMsg(id) => {
          val num: Int = id.substring(2).toInt
          println(s"actor receive ${id},send id${num+1}")
          //13.加入延时
          Thread.sleep(3000)
          //14.返回异步无返回消息
          sender ! AsyncSendAndReturnMsg(s"id${num+1}")
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
    //6.发送同步消息，等待消息返回
    val res1 = actor !? "id1"
    //8.强转
    val str = res1.asInstanceOf[String]
    //9.打印
    println(str)
    //11.发送异步有返回消息
    val future: Future[Any] = actor !! AsyncSendAndReturnMsg("id3")
    //12.如果isSet为True代表有返回，如果为false代表没返回
    future.isSet
    //循环等待理解成在干别的活
    while (!future.isSet){
      //15。如果没有返回值
      Thread.sleep(800)
      println("sleep 800")
    }
    //16.有值后转换打印值
    //future()提取数据，内部调用class的apply方法
    val msg2: AsyncSendAndReturnMsg = future().asInstanceOf[AsyncSendAndReturnMsg]
    println(msg2)
  }
}
//13.定义样例类实现异步有返回消息的发送和接收
case class AsyncSendAndReturnMsg(id:String)