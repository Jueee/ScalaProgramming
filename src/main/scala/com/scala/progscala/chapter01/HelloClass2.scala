package com.scala.progscala.chapter01
/**
  *   简化代码
  */
/**
  *   Upper 被声明为单例对象， Scala 将单例模式视为本语言的第一等级成员。
  *   尽管我们声明了一个类，不过 Scala  运行时只会创建Upper 的一个实例。
  *   也就是说，无法通过 new 创建 Upper 对象。
  */
object HelloClass2 {

  // 尽管 Scala  无法推断出方法的参数类型，却常常能够推断出方法的返回类型，因此我们在此省略返回类型的显式声明。
  // 函数体只使用一次该参数，使用占位符 _ 来替代命名参数。
  def upper(strings:String*)=strings.map(_.toUpperCase)

  // mkString 接受一个输入参数，该参数指定了集合元素间的分隔符。
  def upper2(strings:String*)=strings.map(_.toUpperCase).mkString(" ")

  // mkString 接受三个输入参数，最左边的前缀字符串、分隔符和最右边的后缀字符串。
  def upper3(strings:String*)=strings.map(_.toUpperCase).mkString("[", ", ", "]")
}

object HelloTest2{
  def main(args: Array[String]): Unit = {
    // 由于使用了对象而不是类，此次调用变得更加简单。调用语法与调用 Java  类静态方法时的语法一样。
    println(HelloClass2.upper("Hello","World","Test"))

    println(HelloClass2.upper2("Hello","World","Test"))

    println(HelloClass2.upper3("Hello","World","Test"))
  }
}