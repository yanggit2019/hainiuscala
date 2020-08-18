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
  //7.提供获取总连接数
  def getConnSize = this.conns.size
  //8.提供获取连接的操作
  def getConn = {
    //12.判断连接
    if (this.conns.size == 0){
      null
    }else{
      val conn = this.conns.remove(0)
      conn
    }
  }
  //15.提供释放连接的操作
  def releaseConn(conn: Conn):Boolean={
    if (conn ==null || this.conns.size == connMax){
      false
    }else{
      this.conns += conn
      true
    }
  }

}
//2.创建连接对象
class Conn(var id:Int){
  //3.相当于Java的toString方法
  override def toString: String = s"Conn[${id}]"
}
//9.获取测试类
object ConnectionFactoryDemo{
  def main(args: Array[String]): Unit = {
    //10.获取总连接数
    println(ConnecttionFactory.getConnSize)
    //11.获取连接
    val conn = ConnecttionFactory.getConn
    //13.获取多个连接
    val conn2 = ConnecttionFactory.getConn
    val conn3 = ConnecttionFactory.getConn
    val conn4 = ConnecttionFactory.getConn
    //14.打印连接
    println(s"conn:${conn}")
    println(s"conn2:${conn2}")
    println(s"conn3:${conn3}")
    println(s"conn4:${conn4}")
    
    //16 释放连接
    val res4:Boolean=ConnecttionFactory.releaseConn(conn4)
    val res3:Boolean=ConnecttionFactory.releaseConn(conn3)
    val res2:Boolean=ConnecttionFactory.releaseConn(conn2)
    val res:Boolean=ConnecttionFactory.releaseConn(conn)

    println(s"conn release result:${res4}")
    println(s"conn2 release result:${res3}")
    println(s"conn3 release result:${res2}")
    println(s"conn4 release result:${res}")
  }
}