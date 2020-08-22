package day05

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import com.typesafe.config.{Config, ConfigFactory}

class ServerActor extends Actor{
  override def receive: Receive = {
        
    case "start" => println("server receive ==> start ")
  }
}

object ServerActor{
  def main(args: Array[String]): Unit = {
    val host:String = "127.0.0.1"
    val port:Int = 8888

    // 解析配置参数
    val config:Config = ConfigFactory.parseString(
      s"""
         |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname = $host
         |akka.remote.netty.tcp.port = $port
      """.stripMargin
    )
    val serverSys: ActorSystem = ActorSystem("server_sys", config)
    //akka.tcp://server_sys@127.0.0.1:8888/user/server
    val serverRef: ActorRef = serverSys.actorOf(Props[ServerActor], "server")
    serverRef ! "start"
  }
}