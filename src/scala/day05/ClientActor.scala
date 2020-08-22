package day05

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import com.typesafe.config.{Config, ConfigFactory}

class ClientActor(val serverHost:String,serverPort:Int) extends Actor{
  override def receive: Receive = {
    case "start" => println("client receive ==> start")
  }
}

object ClientActor{
  def main(args: Array[String]): Unit = {
    val serverHost = "127.0.0.1"
    val serverPort = 8888
    val host:String = "127.0.0.1"
    val port:Int = 8889

    // 解析配置参数
    val config:Config = ConfigFactory.parseString(
      s"""
         |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname = $host
         |akka.remote.netty.tcp.port = $port
      """.stripMargin
    )
    val clientSys: ActorSystem = ActorSystem("client_sys", config)
    val clientRef: ActorRef = clientSys.actorOf(Props[ClientActor](new ClientActor(serverHost, serverPort)), "client")
    clientRef ! "start"
  }
}