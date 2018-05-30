package com.scala.progscala.chapter04

/**
  * 简单匹配
  *
  * 当尝试去匹配一个没有 case 语句的值时，我们发现编译器抛出了 MatchError
  */
object Course01SimpleMatch {

  def main(args: Array[String]): Unit = {
    // 通过匹配 Boolean  值来模拟掷硬币：
    val bools = Seq(true,false)
    for (bool <- bools){
      bool match{
        case true => println("Got heads")
        case false => println("Got tails")
      }
    }

    // 旧式的 if 语句替代写法：
    for (bool <- bools){
      val which = if (bool) "Head" else "tails"
      println("Got "+which)
    }
  }
}
