package day05

import scala.actors.{Actor, Future}
import scala.collection.mutable.ListBuffer
import scala.io.Source

class WordCountActor extends Actor{
  override def act(): Unit = {
    while(true){
      
      receive{
        //2.实现wordcount逻辑
        case  path:String =>{
          val lines: List[String] = Source.fromFile(path).getLines().toList
          val map: Map[String, Int] = lines.flatMap(_.split("\t")).map((_, 1)).groupBy(_._1).mapValues(_.size)
          //把结果返回去
          sender ! map
          
        }
      }
    }
  }
  
}

object WordCountActor {
  def main(args: Array[String]): Unit = {
  val arr = Array[String]("H:\\课程\\0821-scala05\\code\\测试数据\\input\\word1.txt",
    "H:\\课程\\0821-scala05\\code\\测试数据\\input\\word2.txt",
    "H:\\课程\\0821-scala05\\code\\测试数据\\input\\word3.txt",
    "H:\\课程\\0821-scala05\\code\\测试数据\\input\\word4.txt"
  )
    //用于装future
  val futures = new ListBuffer[Future[Any]]
  //定义一个存放数据的list,用于装future中的数据
  val mapList = new ListBuffer[Map[String, Int]]
  //1.读取文件
  for (file <- arr) {
    val actor: WordCountActor = new WordCountActor
    actor.start()
    val future: Future[Any] = actor !! file
    futures += future
  }
  //3.把futrue里面的数据都拿出来
  while (!futures.isEmpty) {
    //已经有返回值的future，提取future中的数据
    val dones: ListBuffer[Future[Any]] = futures.filter(_.isSet)
    for (done <- dones) {
      val map: Map[String, Int] = done().asInstanceOf[Map[String, Int]]
      println(s"map:${map}")
      mapList += map
      futures -= done
    }
  }
  //此时，所有actor计算的结果都已经拿到，下面是进行整体汇总
  val list: ListBuffer[(String, Int)] = mapList.flatten
  val resMap: Map[String, Int] = list.groupBy(_._1).mapValues(_.map(_._2).sum)
  println(resMap)
  }
}
