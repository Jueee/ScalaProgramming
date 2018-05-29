package com.scala.progscala.chapter02

/**
  * 导入类型及其成员
  */

// 用下划线（ _ ）当通配符，导入包中的所有类型。
import java.awt._

// 导入包中单独的 Scala  类型或 Java  类型。
import java.io.File

// 导入了 java.io.File 中所有的静态方法和属性
// 与之等价的 Java import  语句为 import static java.io.File.* 。
import java.io.File._

// 选择性导入了 java.util.Map 和 java.util.HashMap 。
import java.util.{Map, HashMap}



/**
  * import  语句几乎可以放在任何位置上，所以你可以将其可见性限制在需要的作用域中
  * 可以在导入时对类型做重命名，也可以限制不想要的类型的可见性
  *
  * 【注】当你想取一个便利的名字或避免与当前作用域中其他同名声明冲突时，别名非常有用。
  * 导入 Java  类型时经常使用别名，以避免其余 Scala  中同名类型的冲突
  */
object Course12ImportingTypes{
  def stuffWithBigInteger() = {
    import java.math.BigInteger.{
      ONE => _,             // 常量重命名为下划线，使得该常量不可见
      TEN,
      ZERO => JAVAZERO  }   // 导入时对类型做重命名
//    println("ONE:"+ONE)   // ONE 未定义
    println("TEN:"+TEN)
    println("ZERO:"+JAVAZERO)
  }

  def main(args: Array[String]): Unit = {
    stuffWithBigInteger()
  }
}

/**
  * 12.1 　导入是相对的
  */
import scala.collection.mutable._
import collection.immutable._ //  由于 scala 已经导入，不需要给出全路径
import _root_.scala.collection.parallel._ //  从 “ 根 ” 开始的全路径



/**
  * 12.2 　包对象
  * 客户端可以用 import com.scala.progscala.chapter02.json._ 导入所有的定义，或用通常的方法单独导入元素。
  */
// com\scala\progscala\chapter02\json  包目录
package object json{  // 使用 package 关键字给包名之后的对象命名，在这里对象名为 json 。
  class  JSONObject {}// 适合暴露给客户端的成员
  def fromString(string: String):JSONObject = {Option.empty.get}
}


