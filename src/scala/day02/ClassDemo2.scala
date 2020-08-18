package day02
//是object ClassDemo2伴生类
class ClassDemo2 {
  var name:String = "hainiu"
//  val age:Int = 20
  //private修饰的成员，当前对象和伴生对象都可访问
  private val age:Int = 20
  //private[this]修饰的成员，只有当前类能访问，伴生对象不能访问
  private[this] var money:Int =1
  private def getMoney()=this.money
}

//class ClassDemo2 伴生对象
object ClassDemo2{
  def main(args: Array[String]): Unit = {
    val demo = new ClassDemo2
    //伴生对象可以访问
    println(demo.age)
    println(demo.getMoney())
  }
}
//非伴生对象
object ClassDemo2Other{
  def main(args: Array[String]): Unit = {
    val demo = new ClassDemo2
//    demo.age
  }
}
