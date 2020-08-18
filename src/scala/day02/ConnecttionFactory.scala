package day02

import scala.collection.mutable.ArrayBuffer

object ConnecttionFactory {
  //初始化连接
  //5.
  private[this] val connMax =3
  //4
  private[this] val conns = new ArrayBuffer[Conn](connMax)
  //6.创建连接对象放入数组
  for(i <- 0 until connMax){
    conns += new Conn(i)
  }
  //提供获取总连接数
  
  //提供获取连接的操作
  
  //提供释放连接的操作

}
//2.创建连接对象
class Conn(var id:Int){
  //3.相当于Java的toString方法
  override def toString: String = s"Conn[${id}]"
}
