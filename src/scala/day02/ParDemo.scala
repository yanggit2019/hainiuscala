package day02

object ParDemo {
  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4,5)
//    一个线程来遍历
    list.foreach(f => {
//      打印当前的线程名
      println(s"${Thread.currentThread().getName} => ${f}")
    })
  }
  
}
