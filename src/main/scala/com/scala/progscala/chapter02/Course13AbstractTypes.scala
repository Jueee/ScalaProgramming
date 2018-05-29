package com.scala.progscala.chapter02

import java.io.{BufferedInputStream, File, FileInputStream}

/**
  * 抽象类型与参数化类型
  *
  * 在语法上， Java  使用尖括号（ <…> ），而 Scala  使用方括号（ […] ），因为在 Scala  中 < 和 > 常用作方法名。
  */
object Course13AbstractTypes {

  // 字符串列表可以声明如下：
  val strings:List[String] = List("one","two","three")

  /**
    * 由于我们可以在集合 List[A] 中使用任何类型作为类型 A ，这种特性被称为参数多态。
    * 在方法 List 的通用实现中，允许使用任何类型的实例作为 List 的元素。
    */

  /**
    * 在List的源码List.scala中，List的定义如下：
    * sealed abstract class List[+A] {}
    *
    * A 之前的 + 表示：如果 B 是 A 的子类，则 List[B] 也是 List[A] 的子类型，这被称为协类型。
    * 协类型很符合直觉，如果我们有一个函数 f(list: List[Any]) ，那么传递 List[String] 给这个函数，也应该能正常工作。
    *
    * 如果类型参数前有 - ，则表示另一种关系：如果 B 是 A 的子类型，且 Foo[A] 被声明为 Foo[-A] ，则 Foo[B] 是 Foo[A] 的父类型（称为逆类型）。
    */


  /**
    * 参数化类型和抽象类型都被声明为其他类型的成员，就像是该类型的方法与属性一样。
    * 以下示例在父类中使用抽象类型，而在子类中将该类型具体化：
    */
  abstract class BulkReader{
    type In   // 类型成员
    val source:In   // val 变量
    def read:String // read 方法
  }

  class StringBulkReader(val source:String) extends BulkReader{
    override type In = String
    override def read: String = source
  }

  class FileBulkReader(val source:File) extends BulkReader{
    override type In = File
    override def read: String = {
      val in = new BufferedInputStream(new FileInputStream(source))
      val numBytes = in.available()
      val bytes = new Array[Byte](numBytes)
      in.read(bytes,0,numBytes)
      new String(bytes)
    }
  }
  /**
    *  type 成员的工作机制与参数化类型中的类型参数非常类似。
    *  class StringBulkReader(val source: String) extends BulkReader[String] {...}
    *  class FileBulkReader(val source: File) extends BulkReader[File] {...}
    */

  def main(args: Array[String]): Unit = {

    println(new StringBulkReader("Hello Scala!").read)

    println(new File(".").getAbsolutePath)
    println(new FileBulkReader(new File("src/main/scala/com/scala/progscala/chapter02/Course13AbstractTypes.scala")).read)
  }

}
