package day02

class ClassDemo {
  //1.定义不可变属性
  val sex:String ="boy"
  
  //5.定义可变属性用下划线占位
  //_是用来占位的，当初始化时，会根据变量类型初始化属性值，字符串初始化属性值时null
  //当有_占位，变量类型不能省略
  var nickName:String = _
  
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
    //设置属性值
    demo.setName("hainiu123")
    //打印信息
    demo.printInfo()
    
    println(demo.nickName)
    demo.nickName="大数据人员生产基地"
    println(demo.nickName)
   
  }
}