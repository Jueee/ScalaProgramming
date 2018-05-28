package com.scala.progscala.chapter02

import scala.collection.mutable


/**
  * 推断类型信息
  */
/**
  * 什么时候需要显式类型注解
  * 1、声明了可变的 var 变量或不可变的 val 变量，没有进行初始化。
  * 2、所有的方法参数
  * 3、方法的返回值类型，在以下情况中必须显式声明其类型：
  *   3.1、在方法中明显地使用了 return （即使在方法末尾也是如此）。
  *   3.2、递归方法。
  *   3.3、两个或多个方法重载（拥有相同的函数名），其中一个方法调用了另一个重载方法，调用者需要显式类型注解。
  *   3.4、Scala  推断出的类型比你期望的类型更为宽泛，如 Any 。
  */
object Course06InferringType {
  def main(args: Array[String]): Unit = {
    // Java7 之前的版本中的类型声明代码往往比较繁琐：
    // HashMap<Integer, String> intToStringMap = new HashMap<Integer, String>();

    // Java7 引入了尖括号操作符来推断表达式右边的泛型类型，降低了冗余度
    // HashMap<Integer, String> intToStringMap = new HashMap<>();

    // 以上声明可以用 Scala  重写如下：
    val intToStringMap:mutable.HashMap[Integer,String] = new mutable.HashMap

    // 将 HashMap[Integer, String] 放在等号后边，代码会更简洁：
    val intToStringMap2 = new mutable.HashMap[Integer,String]


    /**
      * 重载的函数中，如果其中一个调用了另一个，则需要提供显式类型注解：
      */
    object StringUtilV1{
      // 参数列表中， String 后的 * 表示 “0  个或多个 String” 。方法可以拥有其他参数，但必须位于可变参数之前，且方法只能拥有一个可变参数。
      def joiner(strings: String*) = strings.mkString("-")
//      def joiner(strings:List[String]) = joiner(strings:_*) // 错误版本，编译错误
      def joiner(strings:List[String]):String = joiner(strings:_*) // 正确版本
    }
    println(StringUtilV1.joiner(List("Programming","Scala")))


    def makeList(strings:String*) ={
      if (strings.length == 0)
//        List(0) // 错误版本，实际上，我们返回的是拥有一个元素 0 的 List[Int]
        List.empty[String]  // 正确版本：返回 List.empty[String] 或空列表的专用产生工具 Nil 。两种方法都可以得到正确的返回值推断结果。
      else strings.toList
    }
    val list:List[String] = makeList()


    def double1(i:Int) {2 * i}
    println(double1(2))    // 错误版本，打印 ()，方法签名 double(Int)Unit

    def double2(i:Int) = {2 * i}
    println(double2(2))    // 正确版本，打印 4，方法签名 double(Int)Int
  }
}
