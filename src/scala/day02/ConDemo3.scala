package day02

class ConDemo3(var name:String) {
  var age:Int = _
  var addr:String = _
  //定义辅助构造器
  //辅助构造器参数不能加var val修饰
  //辅助构造器可以重载
  //辅助构造器不能跟主构造器形成列表一致
  def this(name:String,age:Int) = {
    this(name)
    this.age=age
  }
  def this(name:String,age:Int,addr:String) = {
    this(name)
    this.age=age
    this.addr=addr
  }
  
}

object ConDemo3{
  def main(args: Array[String]): Unit = {
    //通过主构造器来创建对象
    val demo = new ConDemo3("傻强")
    println(demo.name)
    //通过辅助构造器创建对象
    val demo2 = new ConDemo3("傻强", 10)
    println(demo2.age)
    val demo3 = new ConDemo3("傻强", 10, "四川")
    println(demo3.addr)
  }
}