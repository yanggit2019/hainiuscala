package day02

class ConDemo3(var name:String) {
  var age:Int = _
  //定义辅助构造器
  def this(name:String,age:Int) = {
    this(name)
    this.age=age
  }
  
}

object ConDemo3{
  def main(args: Array[String]): Unit = {
    //通过主构造器来创建对象
    val demo = new ConDemo3("傻强")
    //通过辅助构造器创建对象
    val demo2 = new ConDemo3("傻强", 10)
    println(demo2.age)
    
  }
}