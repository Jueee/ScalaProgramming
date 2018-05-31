package com.scala.progscala.chapter04

/**
  * 元组的匹配
  *
  * 通过元组字面量，很容易对元组进行匹配
  *
  * 元素可以拆分为各个组成的元素。我们可以匹配元组中任意位置的字面量，同时忽略我们不需要的值。
  */
object Course04MatchTuples {

  def main(args: Array[String]): Unit = {
    val langs = Seq(
      ("Scala", "Martin", "Odersky"),
      ("Clojure", "Rich", "Hickey"),
      ("Lisp", "John", "McCarthy")
    )

    for (tuple <- langs){
      tuple match {
        // 匹配一个三元组的元组，其中第一个元素为字符串 Scala ，忽略第二和第三个元素
        case ("Scala", _, _) => println("Found Scala")
        // 匹配任意三元素元组，其中的元素可以为任意类型
        // 但在这里，由于输入的值为 lang ，元素类型被推断为 String 。
        // 元组的三个元素被提取到变量 lang 、 first 和 last 中。
        case (lang, first, last) => println(s"Found other langage:$lang ($first, $last)")
      }
    }
  }
}
