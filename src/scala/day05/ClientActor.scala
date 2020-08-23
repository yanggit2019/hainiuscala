package day05

import akka.actor.{Actor, ActorRef, ActorSelection, ActorSystem, Props}
import com.typesafe.config.{Config, ConfigFactory}

import scala.util.Random

class ClientActor(val serverHost:String,serverPort:Int) extends Actor{
  var serverRef: ActorSelection = _
  override def preStart(): Unit = {
    //获取ServerActor的外部引用对象，不创建对象，只获取
    serverRef = context.actorSelection("akka.tcp://server_sys@127.0.0.1:8888/user/server")
  }
  override def receive: Receive = {
    case "start" => println("client receive ==> start")
    case SendClientMsg(data) =>{
      println(s"client receive ==> SendClientMsg(${data})")
      val arr: Array[String] = data.split(" ")
      if (arr.length != 3){
        println("接收到的本地数据格式错误")
      }else{
      val num1: Int = arr(0).toInt
      val num2: Int = arr(2).toInt
      val symblo: String = arr(1)
        val msg = Client2ServerMsg(num1,symblo,num2)
        println(s"client send ==> ${msg}")
       serverRef !  msg
      }
    }
    case Server2ClientMsg(errCode,errMsg,res) =>{
      println(s"client receive calculate result ==>{errcode:${errCode},errMsg:${errMsg},res:${res}}")
    }
  }
}

object ClientActor{
  def main(args: Array[String]): Unit = {
    val serverHost = "192.168.88.189"
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
    
    val symbolArr = Array("+","-","*","/")
    while(true){
      val num1: Int = Random.nextInt(100)
      val num2: Int = Random.nextInt(100)
      val symbol: String = symbolArr(Random.nextInt(symbolArr.length))
      clientRef ! SendClientMsg(s"${num1} ${symbol} ${num2}")
      Thread.sleep(1000)
    }
    clientRef ! SendClientMsg("1 + 2")
  }
}
//定义样例类用于封装本地发送给clientActor的消息
case class SendClientMsg(data:String)
//定义用于封装client发送给server端的消息
case class Client2ServerMsg(val num1:Int,val symbol: String,val num2:Int)
//用于封装server发送client端的消息(计算结果)
case class Server2ClientMsg(errCode:String,errMsg:String,res:Int)