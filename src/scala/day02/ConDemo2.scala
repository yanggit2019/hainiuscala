package day02
//主构造器参数没有var,val修饰，代表private[this]修饰
//最后一个参数末尾参数可以是默认参数，当new对象是可以不传
class ConDemo2 (var name:String,addr:String = "北京市",private val age:Int = 20){

}

object ConDemo2{
  def main(args: Array[String]): Unit = {
    val demo = new ConDemo2("傻强")
    println(s"name:${demo.name},age:${demo.age}")
//    demo.addr
  }
}
//其他测试类
object ConDemo2Other{
  def main(args: Array[String]): Unit = {
    val demo = new ConDemo2("傻强")
    println(s"name:${demo.name},age:${demo.age}")
  }
}