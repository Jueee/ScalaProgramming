package com.scala.progscala.chapter03

import java.io.File

import scala.io.Source
import scala.util.control.NonFatal

/**
  * 使用try、catch和final子句
  *
  * Scala  推崇通过使用函数式结构和强类型以减少对异常和异常处理的依赖的编码范式。
  * 尽管如此，异常仍然有用，而当 Scala  需要与普遍使用异常的 Java  代码交互时，异常尤为重要。
  *
  * Scala  将异常处理作为另一类模式匹配来进行处理，因此我们可以简洁地对各种不同类型的异常进行处理。
  *
  * 【注】假如你需要对 Option 对象进行检测，当它是 Some 对象时执行一些操作，而当它是 None 对象时则不进行任何操作，那么你可以使用 for 推导式
  */
object Course09TryCatch {

  def main(args: Array[String]): Unit = {
    new File(".").listFiles().foreach(file => { // 使用 foreach 循环遍历参数列表并对各个参数进行处理
      if (file.isFile) countLines(file.getAbsolutePath)
    })
  }

  /**
    * 统计每个文件名所对应文件的行数
    *
    * @param fileName
    */
  def countLines(fileName: String) = {
    // 将变量 source 声明为 Option 类型，因此我们在 finally 子句中能分辨出 source 对象是否是真正的实例。
    var source: Option[Source] = None
    try {
      source = Some(Source.fromFile(fileName))
      val size = source.get.getLines().size
      println(s"file $fileName has $size lines")
    } catch { //  捕获那些非致命的错误
      //  Scala  会使用模式匹配来捕捉你所希望捕获的异常，而 Java  则使用单独的 catch 子句来捕获各个异常。
      case NonFatal(ex) => println(s"file $fileName Non fatal exception!$ex") // 匹配 nonfatal  异常的
    } finally {
      for (s <- source) { // 使用 for 推导式从 Some 类型的对象中提取 Source 实例
        s.close() // 关闭文件
      }
    }
  }

}
