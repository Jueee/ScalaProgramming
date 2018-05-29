package com.scala.progscala.chapter03

import java.io.File

import scala.io.Source
import scala.util.control.NonFatal

/**
  * 名字调用和值调用
  *
  * 传名参数的行为与函数相似；每次使用该参数时便会执行表达式。
  */

/**
  * 将 关注点分离 （ separation of concern ， SOC ）
  * 演示了如何使用 Scala  工具构建小型 领域特定语言 （ DSL ）。
  */
object manage{
  /**
    * <: 意味着 R 属于某其他类型的子类。
    * @param resource 传名参数
    * @param f  用于处理资源的匿名函数,一个输入为 resource 、返回值类型为 T 的匿名函数
    * @tparam R 表示我们将要管理的资源类型
    * @tparam T 表示该匿名函数的返回值
    * @return
    */
  def apply[R <: {def close():Unit},T](resource: => R)(f: R=> T) = {
    var res:Option[R] = None
    try {
      res = Some(resource)  //  只会引用 "resource" 一次 !!
      f(res.get)    // 将 res 值传入工作函数 f 中
    } catch {
      case NonFatal(ex) => println(s"Non fatal exception!$ex")
    } finally {
      if (res != None){
        res.get.close()
      }
    }
  }
}

object Course10CallByName {

  def countLines(fileName:String)={
    // manage 对象在这段代码中看上去就像是一个 Scala  自带的控制结构
    // 该控制结构包含了两个参数列表：一个用于创建 Source 对象，而另一个则是处理 Source 对象的代码块。
    // 这样一来， manage 对象看起来就像是一个普通 while 语句了。
    manage(Source.fromFile(fileName)){source=>
      val size = source.getLines().size
      println(s"file $fileName has $size lines")
      if(size > 20) throw new RuntimeException("Big file!")
    }
  }

  def main(args: Array[String]): Unit = {
    new File(".").listFiles().foreach(file => { // 使用 foreach 循环遍历参数列表并对各个参数进行处理
      if (file.isFile) countLines(file.getAbsolutePath)
    })


    /**
      * 下面的示例中通过定义 continue 结构，实现了一个简单的类似于 while 循环的结构体：
      */
    /**
      * 使用递归取代循环结构
      * 由于传名参数的求值会被推迟，并可能会一再地被重复调用，因此此类参数具有惰性。
      * @param confitional  第一个列表中仅包含了一个传值参数 conditional
      * @param body 第二个列表则包含了传值参数 body,body  代表了每次迭代都会执行的代码体
      */
    @annotation.tailrec   // 确保了调用实现体时将采用尾递归的方式
    def continue(confitional: => Boolean)(body: => Unit): Unit ={
      if(confitional){  //  检查当前是否满足条件
        body  // 执行 body  参数
        continue(confitional)(body) // 递归调用 continue 函数
      }
    }

    var count = 0
    continue(count < 5){
      println(s"at $count")
      count += 1
    }

  }
}
