package day02

object ConnecttionFactory {
  //初始化连接
  
  //提供获取总连接数
  
  //提供获取连接的操作
  
  //提供释放连接的操作

}
//创建连接对象
class Conn(var id:String){
  //相当于Java的toString方法
  override def toString: String = s"Conn[${id}]"
}
