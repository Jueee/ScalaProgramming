package com.scala.progscala.chapter06

/**
  * Curry 化与函数的其他转换
  *
  * Curry  将一个带有多参数的函数转换为一系列函数，每个函数都只有一个参数。
  *
  * 在 Scala  中， Curry  化的函数带有多个参数列表，处理后，每个函数都只有一个参数列表。
  */
object Course06Currying {

  def main(args: Array[String]): Unit = {
    content1
    content2
    content3
    content4
    content5
    content6
    content7
  }

  /**
    * 第一种语法更具可读性。而第二种语法在将 Curry  化的函数作为偏应用函数时，不需要在后面加上下划线
    */
  def content1: Unit ={
    // 函数带有多个参数列表
    def cat1(s1:String)(s2:String) = s1 + s2

    // 可以用以下语法来定义 curry  化的函数：
    def cat2(s1:String) = (s2:String) => s1 + s2


    val cat2hello = cat2("Hello ")     //  没有 _
    println(cat2hello)    // <function1>

    val cat2world = cat2hello("world")
    println(cat2world)    // Hello world

    // 调用两个函数的写法相同，返回结果也相同：
    println(cat1("foo")("bar"))
    println(cat2("foo")("bar"))
  }

  /**
    * 还可以将一个带有多个参数的方法转为 Curry  化的形式
    * 将带有两个参数的方法 cat3 ，转为其 Curry  化的等价函数，该函数带两个参数列表。
    * 如果 cat3 带三个参数，则相应的 Curry  也就带有三个参数列表，以此类推。
    */
  def content2: Unit ={
    def cat3(s1:String, s2:String) = s1 + s2
    println(cat3("hello","world"))

    val cat3Curried = (cat3 _).curried
    println(cat3Curried)  // <function1>
    println(cat3Curried("hello")("world"))  // helloworld
  }

  /**
    * 类型签名 String => String => String 与 String => (String => String) 是等价的。
    * 调用 f1 或 f2 时绑定第一个参数列表，将会返回一个类型为 String => String 的新函数。
    */
  def content3: Unit ={
    val f1:String => String => String =
      (s1:String) => (s2:String) => s1 + s2
    val f2:String => (String => String) =
      (s1:String) => (s2:String) => s1 + s2

    println(f1)   // <function1>
    println(f2)   // <function1>

    println(f1("hello")("world")) // helloworld
    println(f2("hello")("world")) // helloworld
  }

  /**
    * 也可以用 Function 中的一个方法对函数做 “ 去 Curry” ：
    */
  def content4: Unit ={
    def cat3(s1:String, s2:String) = s1 + s2
    val cat3Curried = (cat3 _).curried

    val cat3Uncurried = Function.uncurried(cat3Curried)
    println(cat3Curried)  // <function1>
    println(cat3Uncurried("hello","world")) // helloworld

    val f1:String => String => String =
      (s1:String) => (s2:String) => s1 + s2
    println(f1) // <function1>

    val ff1 = Function.uncurried(f1)
    println(ff1)  // <function2>
    println(ff1("hello","world"))   // helloworld
  }

  /**
    * Curry  的一个实际用处是对特定类型的数据函数做特殊化。
    * 函数可以接受通用的类型，而 Curry  化的函数形式则只接受特定的类型。
    *
    * 示例。原函数用于计算连乘， Curry  化的函数是原函数的特化版本：
    */
  def content5: Unit ={
    def multiplier(int: Int)(factor:Int) = int * factor

    val byFive = multiplier(5)_
    val byTen = multiplier(10)_

    println(byFive(2))  // 10
    println(byTen(2))   // 20
  }

  /**
    * 你有一个元组，例如，一个三元素元组，而你需要调用一个包含三个参数的函数
    * Function 对象为我们提供了元组形式和非元组形式的方法
    */
  def content6: Unit ={
    def mult(d1:Double,d2:Double,d3:Double) = d1 * d2 * d3

    val d3 = (2.2,3.3,4.4)
    println(mult(d3._1, d3._2, d3._3))        // 31.944000000000003

    val multTupled = Function.tupled(mult _)  // 31.944000000000003
    println(multTupled(d3))

    val multUntupled = Function.untupled(multTupled)
    println(multUntupled(d3._1, d3._2, d3._3))// 31.944000000000003
  }

  /**
    * 偏函数与返回 Option 函数之间是可以相互转化的
    * 如果我们有一个偏函数，同时又不希望发生抛出异常的情况，可以将偏函数提升为一个返回 Option 的函数，也可以将返回 Option 的函数 “ 降级 ” 为偏函数。
    */
  def content7: Unit ={
    val finicky:PartialFunction[String,String] = {
      case "finicky" => "FINICKY"
    }
    println(finicky("finicky")) // FINICKY
//    println(finicky("other"))   // Exception in thread "main" scala.MatchError: other (of class java.lang.String)

    val finickyOption = finicky.lift
    println(finickyOption("finicky")) // Some(FINICKY)
    println(finickyOption("other")) // None

    val finicky2 = Function.unlift(finickyOption)
    println(finicky2("finicky"))  // FINICKY
//    println(finicky2("other"))  // Exception in thread "main" scala.MatchError: other (of class java.lang.String)
  }
}
