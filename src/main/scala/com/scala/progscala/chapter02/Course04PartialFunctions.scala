package com.scala.progscala.chapter02

/**
  * 偏函数(PartialFunction)
  *
  * 偏函数之所以 “ 偏 ” ，原因在于它们并不处理所有可能的输入，而只处理那些能与至少一个 case  语句匹配的输入。
  * 在偏函数中只能使用 case  语句，而整个函数必须用花括号包围。
  *
  * 如果偏函数被调用，而函数的输入却与所有语句都不匹配，系统就会抛出一个 MatchError运行时错误。
  * 可以用 isDefineAt 方法测试特定输入是否与偏函数匹配，这样偏函数就可以避免抛出 MatchError 错误了。
  */
object Course04PartialFunctions {

  def main(args: Array[String]): Unit = {
    // 只匹配字符串的偏函数
    val pf1:PartialFunction[Any,String] = {case s:String => "YES"}

    // 只匹配 Double  数字的偏函数
    val pf2:PartialFunction[Any,String] = {case d:Double => "YES"}

    // 将这两个函数结合，得到一个新的偏函数：既能匹配字符串，又能匹配 Double  数字
    val pf = pf1 orElse pf2

    // 辅助函数：用于 try 一个偏函数，然后将可能产生的 MatchError 异常捕捉到。无论是否捕获异常，函数均返回一个字符串。
    def tryPF(x:Any, f:PartialFunction[Any,String]):String =
      try { f(x).toString} catch { case _:MatchError => "ERROR"}

    // 辅助函数：使用了 isDefineAt ，返回值为字符串
    def d(x:Any, f:PartialFunction[Any,String]) =
      f.isDefinedAt(x).toString

    // 使用了多个偏函数的链式组合，并将结果以表格的形式打印出来
    List("str", 3.14, 10) foreach { x =>
      printf("%-5s | %-5s | %-6s | %-5s | %-6s | %-5s | %-6s\n", x.toString,
        d(x, pf1), tryPF(x, pf1), d(x, pf2), tryPF(x, pf2), d(x, pf), tryPF(x, pf))
    }

  }
}
