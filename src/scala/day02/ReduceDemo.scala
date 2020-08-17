package day02

object ReduceDemo {
  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4,5)
    println("--------reduce------------------")
    val sum: Int = list.reduce((a: Int, b: Int) => {
      println(s"${Thread.currentThread().getName}:${a} + ${b} = ${a + b}")
      a + b
    })
    println(s"sum:${sum}")

    println("--------reduceLeft------------------")
    val sum2: Int = list.reduceLeft((a: Int, b: Int) => {
      println(s"${Thread.currentThread().getName}:${a} + ${b} = ${a + b}")
      a + b
    })
    println(s"sum:${sum2}")

    println("--------reduceRight------------------")
    val sum3: Int = list.reduceRight((a: Int, b: Int) => {
      println(s"${Thread.currentThread().getName}:${a} + ${b} = ${a + b}")
      a + b
    })
    println(s"sum:${sum3}")


    println("--------list.par.reduce------------------")
    val sum4: Int = list.par.reduce((a: Int, b: Int) => {
      println(s"${Thread.currentThread().getName}:${a} + ${b} = ${a + b}")
      a + b
    })
    println(s"sum:${sum4}")

    println("--------list.par.reduceLeft------------------")
    val sum5: Int = list.par.reduceLeft((a: Int, b: Int) => {
      println(s"${Thread.currentThread().getName}:${a} + ${b} = ${a + b}")
      a + b
    })
    println(s"sum:${sum5}")

    println("--------list.par.reduceRight------------------")
    val sum6: Int = list.par.reduceRight((a: Int, b: Int) => {
      println(s"${Thread.currentThread().getName}:${a} + ${b} = ${a + b}")
      a + b
    })
    println(s"sum:${sum6}")
  }
}

