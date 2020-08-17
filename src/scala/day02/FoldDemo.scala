package day02

object FoldDemo {
  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4,5)

    println("-----fold-------------")
    val sum = list.fold(10)((a:Int, b:Int)=> {
      println(s"a:${a}, b:${b}")
      a+b
    })
    println(sum)
    println("------foldLeft------------")
    val sum1: Int = list.foldLeft(10)((a: Int, b: Int) => {
      println(s"a:${a}, b:${b}")
      a + b
    })
    println(sum1)
    println("------foldRight------------")
    val sum2: Int = list.foldRight(10)((a: Int, b: Int) => {
      println(s"a:${a}, b:${b}")
      a + b
    })
    println(sum2)
    println("-------并行集合的fold-----------")
    // 利用并行集合多线程运算，没有顺序
    val sum3: Int = list.par.fold(10)((a: Int, b: Int) => {
      println(s"a:${a}, b:${b},threadName:${Thread.currentThread().getName}")
      a + b
    })
    println(sum3)
    println("-------并行集合的foldLeft-----------")
    // foldLeft 将并行集合多线程的运算变成了单线程，有顺序
    val sum4: Int = list.par.foldLeft(10)((a: Int, b: Int) => {
      println(s"a:${a}, b:${b},threadName:${Thread.currentThread().getName}")
      a + b
    })
    println(sum4)
    println("-------并行集合的foldRight-----------")
    // foldRight 将并行集合多线程的运算变成了单线程，有顺序
    val sum5: Int = list.par.foldRight(10)((a: Int, b: Int) => {
      println(s"a:${a}, b:${b},threadName:${Thread.currentThread().getName}")
      a + b
    })
    println(sum5)
  }
}
