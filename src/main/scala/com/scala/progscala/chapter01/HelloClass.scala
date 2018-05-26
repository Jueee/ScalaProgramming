// package com.scala.progscala.chapter01


/**
  * 定义类时需要输入 class 关键字
  */
class HelloClass {

  // 定义方法时需要先输入 def 关键字，之后输入方法名称以及可选的参数列表。
  // 返回类型由冒号加类型表示。最后使用等于号（ = ）将方法签名和方法体分隔开。
  /**
    * 将输入字符串转换成大写字符串，并返回一个包含这些字符串的 Seq 对象。
    * @param strings
    * @return // Seq 是集合的一种抽象，你可以依照固定的顺序遍历这类结合抽象。
    */
  def upper(strings:String*):Seq[String]={  //  Scala  使用方括号（ […] ）表示参数类型，而 Java  使用角括号（ <…> ）
    strings.map((s:String) => s.toUpperCase())
  }

}

object HelloTest{
  def main(args: Array[String]): Unit = {
    val up = new HelloClass             // 创建 Upper 对象的一个实例
    println(up.upper("Hello","World"))  // 打印出产生的 Seq 对象
  }
}