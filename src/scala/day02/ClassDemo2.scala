package day02
//是object ClassDemo2伴生类
class ClassDemo2 {
  var name:String = "hainiu"
//  val age:Int = 20
  private val age:Int = 20
}

//class ClassDemo2 伴生对象
object ClassDemo2{
  def main(args: Array[String]): Unit = {
    val demo = new ClassDemo2
    //伴生对象可以访问
    println(demo.age)
  }
}