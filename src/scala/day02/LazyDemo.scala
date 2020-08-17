package day02

object LazyDemo {
  def init():Unit = {
    println("init")
  }
  def main(args: Array[String]): Unit = {
    val p = init()       //没有lazy关键字的时候
    println("init after")
    println(p)
    println(p)
  }
}

//init
//init after
//()
//()


object LazyDemo2{
  def init():Unit = {
    println("init")
  }
  def main(args: Array[String]): Unit = {
    lazy val p = init()        //有lazy关键字的时候，在调用的时候才去初化它
    println("init after")
    println(p)
    println(p)
  }
}

//init after
//init
//()
//()
