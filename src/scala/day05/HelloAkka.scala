package day05

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

class HelloAkka extends Actor{
  override def receive: Receive = {
    case  "start" =>{
      println("actor receive ==> start")
    }
    case  "hello" =>{
      println("actor receive ==> hello")
    }
    case  "stop this" =>{
      println("actor receive ==> stop this ,stopping ....")
      //关闭的是当前的actor对象
      context.stop(self)
    }
    case  "stop all" =>{
      println("actor receive ==> stop all ,stopping ....")
      //关闭的是所有actor对象
      context.system.terminate()
    }
  }
}

//测试：
object HelloAkka{
  def main(args: Array[String]): Unit = {
    //创建ActorSystem
    val actorSystem: ActorSystem = ActorSystem("hello_sys")
    //创建HelloAkka对象，并获取外部引用对象,给对象起别名
    //akka地址 [akka://hello_sys/user/hello]
    val helloRef: ActorRef = actorSystem.actorOf(Props[HelloAkka], "hello")
    helloRef ! "start"
    helloRef ! "hello"
//    helloRef ! "stop this"
    //当关闭了actor对象后，当前消息就处理不了了
//    helloRef ! "hello"
    helloRef ! "stop all"
  }
}