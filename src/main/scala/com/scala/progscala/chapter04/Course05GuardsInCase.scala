package com.scala.progscala.chapter04

/**
  * case 中的 guard 语句
  *
  * 【注】if 表达式的两边不需要使用括号，就像我们在 for 表达式中也不需要括号一样。
  */
object Course05GuardsInCase {

  def main(args: Array[String]): Unit = {
    for (i <- Seq(1,2,3,4)){
      i match {
        case _ if i%2 == 0 => println(s"even:$i") // 只有当 i 为偶数时才匹配
        case _  =>println(s"odd:$i")  // 匹配上一 case  子句相对的另一种可能性，即 i 为奇数。
      }
    }
  }
}
