package day02

import scala.io.Source

class Parser {
  def getHostName(ip:String) = Parser.map.getOrElse(ip,"无此主机名")
}

//伴生对象
object Parser{
  //利用单例对象初始化过程来读取文件生产map
  //H:\input ip.txt
  private[this] val path:String = "H:\\input\\ip.txt"
  //scala读文件
  private[this] val lines: List[String] = Source.fromFile(path).getLines().toList
//  println(lines)
  private val map: Map[String, String] = lines.map(_.split(" ")).map(f => (f(0), f(1))).toMap
//  println(map)
  //测试方法：
  def main(args: Array[String]): Unit = {
    val parser = new Parser
    val hostname = parser.getHostName("192.168.88.189")
    println(s"hostName:${hostname}")
    
  }
}