package day02
//定义一个主构造器
//private修饰主构造器，伴生类可以通过构造器来new对象
class ConDemo  (var name:String,val sex:String) {

}

object ConDemoOther{
  def main(args: Array[String]): Unit = {
    //通过主构造器来new对象
    val demo = new ConDemo("傻强", "boy")
    demo.name="傻强2"
    println(demo.sex)
  }
}