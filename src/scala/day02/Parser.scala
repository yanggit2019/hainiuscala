package day02

import scala.io.Source

class Parser {

}


object Parser{
  //利用单例对象初始化过程来读取文件生产map
  //H:\input ip.txt
  private[this] val path:String = "H:\\input\\ip.txt"
  //scala读文件
  private[this] val list: List[String] = Source.fromFile(path).getLines().toList
}