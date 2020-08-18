package day02

class ConDemo3(var name:String) {

}

object ConDemo3{
  def main(args: Array[String]): Unit = {
    //通过主构造器来创建对象
    val demo = new ConDemo3("傻强")
  }
}