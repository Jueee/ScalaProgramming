package com.scala.progscala.chapter03

import java.io.File

/**
  * Scala 中的 if 语句
  *
  * Scala  中的 if 语句是一类表示式，像 predicate ? trueHandler() : falseHandler() 这种三元表达式对于 Scala  来说是多余的，因此 Scala 并不支持三元表达式。
  */
object Course05If {

  def main(args: Array[String]): Unit = {
    /**
      * 执行 if 语句时先对 if 条件表达式进行估值。
      * 假如表达式结果为 true ，那么将执行对应的代码块。反之，将测试下一条件分支，以此类推。
      */
    if (2 + 2 == 5){
      println("Hello from 1984.")
    } else if(2 + 2 == 3) {
      println("Hello from Remedial Math class?")
    } else {
      println("Hello from a non-Orwellian future.")
    }

    /**
      *  Scala  中的 if 语句和几乎所有的其他语句都是具有返回值的表达式。
      *  因此我们能像下面展示的代码那样，将 if 表达式的结果值赋给其他变量。
      */
    val configFile = new File("somefile.txt")
    val configFilePath = if(configFile.exists()){
      configFile.getAbsoluteFile
    } else {
      configFile.createNewFile()
      configFile.getAbsoluteFile
    }
    println(configFilePath)
    configFile.delete()
  }
}
