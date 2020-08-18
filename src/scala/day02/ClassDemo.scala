package day02

class ClassDemo {
  //1.定义不可变属性
  val sex:String ="boy"
  
  //2.定义可变属性
  var name:String = "hainiu"
  //3.定义方法
  def setName(name:String) = {
    this.name=name
  } 
  //4.定义函数
  val printInfo = () => println(s"name:${this.name},sex:${this.sex}")
}
//测试类
object ClassDemoTest{
  def main(args: Array[String]): Unit = {
    //测试
    val demo = new ClassDemo
    println(demo.name)
  }
}