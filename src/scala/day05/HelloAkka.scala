package day05

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

class HelloAkka extends Actor{
  override def receive: Receive = {
    case  "start" =>{
      println("actor receive ==> start")
    }
  }
}

//测试：
object HelloAkka{
  def main(args: Array[String]): Unit = {
    //创建ActorSystem
    val actorSystem: ActorSystem = ActorSystem("hello_sys")
    //创建HelloAkka对象，并获取外部引用对象,给对象起别名
    val helloRef: ActorRef = actorSystem.actorOf(Props[HelloAkka], "hello")
    helloRef ! "start"
    
  }
}