package com.scala.progscala.chapter02

/**
  * 分号
  *
  * 分号是表达式之间的分隔符，可以推断得出。
  * 当一行结束时， Scala  就认为表达式结束了，除非它可以推断出该表达式尚未结束，应该延续到下一行
  *
  * 【注】
  * 如果你需要将多行代码解释为同一表达式，却被系统视为多个表达式，可以使用 REPL  的 :paste 模式。
  * 输入 :paste ，然后输入你的代码，最后用 Ctrl-D  结束。
  */
object Course01Semicolons {

  //  末尾的等号表明下一行还有未结束的代码。
  def equalsign(s: String) =
    println("equalsign:" + s)

  //  末尾的花括号表明下一行还有未结束的代码
  def equalsign2(s: String) = {
    println("equalsign2:" + s)
  }

  //  末尾的逗号、句号和操作符都可以表明，下一行还有未结束的代码
  def commas(s1: String,
             s2: String) = Console.
    println("commas:" + s1 +
      "," + s2)


  def main(args: Array[String]): Unit = {
    equalsign("test1")
    equalsign2("test2")
    commas("test1", "test2")
  }
}
