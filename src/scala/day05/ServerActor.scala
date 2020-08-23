package day05

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import com.typesafe.config.{Config, ConfigFactory}

class ServerActor extends Actor{
  override def receive: Receive = {
        
    case "start" => println("server receive ==> start ")
    case Client2ServerMsg(num1,symbol,num2) =>{
      println(s"server receive ==> Client2ServerMsg(${num1},${symbol},${num2})")
      var res:Int =0
      var errCode:String="000000"
      var errMsg:String="成功"
      symbol match {
        case "+" => res = num1+num2
        case "-" =>res = num1-num2
        case "*" =>res = num1*num2
        case _ =>{
          errCode = "ERR001"
          errMsg = "该计算目前只支持+-*运算，待版本更新后，再来尝试其他运算"
        }
      }
      val msg = Server2ClientMsg(errCode,errMsg,res)
      println(s"server send ${msg}")
          sender() ! msg
    }
  }
}

object ServerActor{
  def main(args: Array[String]): Unit = {
    val host = args(0)
    val port = args(1).toInt
//    val host:String = "127.0.0.1"
//    val port:Int = 8888

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