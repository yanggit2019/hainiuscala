package day05

import akka.actor.{Actor, ActorRef, ActorSelection, ActorSystem, Props}

import scala.collection.mutable.ListBuffer
import scala.io.Source
// 父actor负责创建对应的子actor，并给子actor下发计算任务
class WCAkkaActor extends Actor{

  private val maps = new ListBuffer[Map[String,Int]]
  private var fileNum:Int = _
  def receive: Receive = {
    case "start" => println("WCAkkaActor receive=> start")
    case files:Array[String] =>{
      println(s"WCAkkaActor receive=> ${files.toBuffer}")
      fileNum = files.size
      for(i <- 0 until files.size){
        val ref: ActorRef = context.actorOf(Props[WCAkkaActor2], s"wcsub${i}")
        ref ! files(i)
      }
    }

    case map: Map[String, Int] =>{
      println(s"WCAkkaActor receive=> ${map}")
      maps += map
      if(maps.size == fileNum){
        val resMap: Map[String, Int] = maps.flatten.groupBy(_._1).mapValues(_.map(_._2).sum)
        println(s"resMap:${resMap}")
        // 清空maps
        maps.clear()
        for(i <- 0 until fileNum) {
          // 关闭子actor
          val actSubRef: ActorSelection = context.actorSelection(s"/user/wc0/wcsub${i}")
//          println(actSubRef)
          actSubRef ! "stop"
        }
      }
    }
  }
}
// 定义子actor，负责具体的计算逻辑
class WCAkkaActor2 extends Actor{
  def receive: Receive = {
    case "stop" =>{
      println("actor receive => stop, stopping....")
      context.stop(self)
    }
    case file:String =>{
      println(s"actor receive => ${file}")
      println(s"actsub path =>${self.path}")
      val lines: List[String] = Source.fromFile(file).getLines().toList
      val map: Map[String, Int] = lines.flatMap(_.split("\t")).map((_,1)).groupBy(_._1).mapValues(_.size)
      sender() ! map
    }
  }
}

object WCAkkaActor{
  def main(args: Array[String]): Unit = {
    val sys = ActorSystem("akka_wc_sys")
    val wc0: ActorRef = sys.actorOf(Props[WCAkkaActor], "wc0")
    wc0 ! "start"
    val files = Array[String]("E:\\tmp\\scala\\input\\word1.txt",
      "E:\\tmp\\scala\\input\\word2.txt",
      "E:\\tmp\\scala\\input\\word3.txt",
      "E:\\tmp\\scala\\input\\word4.txt")
    wc0 ! files

  }
}
