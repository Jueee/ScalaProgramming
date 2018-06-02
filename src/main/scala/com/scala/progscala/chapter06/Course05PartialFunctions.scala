package com.scala.progscala.chapter06

/**
  * 偏应用函数与偏函数
  *
  * 对于拥有多个参数列表的函数而言，如果你希望忽略其中一个或多个参数列表，可以通过定义一个新函数来实现。
  * 为了避免潜在的表达式歧义， Scala  要求在后面加上下划线，用来告诉编译器你的真实目的。
  * 注意，这个特性只对函数的多个参数列表有效，对一个参数列表中的多个参数的情况并不适用。
  *
  * 偏作用函数是一个表达式，带部分而非全部参数列表的函数。
  * 返回值是一个新函数，新函数负责携带剩下的参数列表。
  *
  * 偏函数则是单参数的函数，并未对该类型的所有值都有定义。
  * 偏函数的字面量语法由包围在花括号中的一个或多个 case 语句构成。
  */
object Course05PartialFunctions {

  def main(args: Array[String]): Unit = {
    content1
    content2
    content3
  }

  /**
    * 带两个参数列表的简单方法
    */
  def content1: Unit ={
    def cat1(s1:String)(s2:String) = s1 + " test " + s2
    println(cat1("test1")("test2"))
  }

  /**
    * 如果我们需要一个专门的版本，要求第一个字符串总是 Hello
    * 我们可以通过偏应用函数来定义这样的函数
    *
    * 输出表明，hello 是一个 <function1> ，也就是带一个参数的函数。
    */
  def content2: Unit ={
    def cat1(s1:String)(s2:String) = s1 + " test " + s2
    val hello = cat1("Hello ")_
    println(hello)    // <function1>
    println(hello("world"))
    println(cat1("Hello ")("world"))
  }

  /**
    * 偏函数带一个某类型参数，但函数并未针对该类型的所有值实现相应处理逻辑。
    * 这里想说的是，这个 inverse 函数是 “ 偏 ” 的，只处理除以 0.0 外的所有 Double 类型。
    */
  def content3: Unit ={
    val inverse:PartialFunction[Double,Double] = {
      case d if d != 0.0 => 1.0/d
    }
    println(inverse)        // <function1>
    println(inverse(1.0))   // 1.0
    println(inverse(2.0))   // 0.5
  }
}
