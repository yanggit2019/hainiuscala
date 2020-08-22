package day05

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

class GirlActor extends Actor{
  override def receive: Receive = {
    case "踩我脚了" =>{
      println("girl actor receive ==> 踩我脚了,send 谁呀")
      Thread.sleep(2000)
      sender() ! "谁呀"
    }
    case "美女是你啊" =>{
      println("girl actor receive ==> 美女是你啊,send 咋滴啦")
      Thread.sleep(2000)
      sender() ! "咋滴啦"
    }
  }
  
}

class BoyActor(val girlRef:ActorRef) extends Actor{
  override def receive: Receive = {
    case "start" =>{
      println("boy actor receive ==> start")
      girlRef ! "踩我脚了"
    }
    case "谁呀" =>{
      println("boy actor receive ==> 谁呀，send 美女是你啊")
      girlRef ! "美女是你啊"
    }
    case "咋滴啦" =>{
      println("boy actor receive ==> 咋滴啦，send 踩我脚了")
      girlRef ! "踩我脚了"
    }
      
  }
}

//测试类
object DialogDemo{
  def main(args: Array[String]): Unit = {
    val actorSystem: ActorSystem = ActorSystem("dialog_sys")
    //创建GirlActor对象
    val girlRef: ActorRef = actorSystem.actorOf(Props[GirlActor], "girl")
    //创建BoyActor对象
    val boyRef: ActorRef = actorSystem.actorOf(Props[BoyActor](new BoyActor(girlRef)), "boy")
    boyRef ! "start"
    
    
  }
}