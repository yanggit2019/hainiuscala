package day02
//主构造器参数没有var,val修饰，代表private[this]修饰
//最后一个参数末尾参数可以是默认参数，当new对象是可以不传
//private和private[this]都可以修饰主构造器函数，权限同属性一样
class ConDemo2 (var name:String,addr:String = "北京市",private val age:Int = 20){
  //定义公有方法
  def getAge = this.age
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
//    println(s"name:${demo.name},age:${demo.age}")
    println(s"name:${demo.name}")
    //private修饰的主构造器参数，其他对象不能访问
//    demo.age
    println(demo.getAge)
  }
}