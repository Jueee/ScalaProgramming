package com.scala.progscala.chapter06

object Course02inScala {

  /**
    * 对一个整数列表进行遍历，过滤出其中的偶数，对每个偶数乘以 2 ，再使用 reduce 函数将各个整数乘在一起：
    */
  def main(args: Array[String]): Unit = {
    content1
    content2
    content3
    content4
  }

  /**
    *  _ % 2 == 0 ， _ * 2 ，与 _ * _ 是函数字面量。
    *  前两个函数只有一个参数，赋值给占位符 _ ；最后一个函数带两个参数，该函数本身是 reduce 函数的参数。
    */
  def content1: Unit ={
    val a = (1 to 10) filter(_%2 == 0) map (_ * 2) reduce(_ * _)
    println(a)
  }

  /**
    * 定义一个名为 factor 的变量，作为累乘因子。
    * 而之前的匿名函数 _ * 2 则替换为一个名为 multiplier 的变量，变量的值由 factor 决定。
    *
    * 尽管 multiplier 是一个不可变的函数字面量，当 factor 改变时， multiplier 的行为也跟着改变。
    * 在 multiplier 函数中有两个变量 i 和 factor 。 i 是一个函数的参数，所以每次调用时， i 都绑定了一个新的值。
    */
  def content2: Unit ={
    var factor = 2
    val multiplier = (i:Int) => i*factor
    val b = (1 to 10) filter(_%2 ==0) map multiplier reduce(_ * _)
    println(b)  // 122880
    factor = 3
    val c = (1 to 10) filter(_%2 ==0) map multiplier reduce(_ * _)
    println(c)  // 933120
  }

  /**
    * 即使 factor 处于某个局部作用域（如某个方法）中，而我们将 multiplier 传递给其他作用域（如另一个方法）中时，这一机制仍然有效。
    */
  def content3: Unit ={
    def m1(multiplier: Int=>Int) = {
      (1 to 10) filter(_%2 ==0) map multiplier reduce(_ * _)
    }

    def m2:Int => Int = {
      val factor = 2
      val multiplier = (i:Int) => i*factor
      multiplier
    }

    println(m1(m2)) // 122880
  }

  /**
    * 可以用方法代替函数
    */
  def content4: Unit ={
    object Multiplier{
      var factor = 2
      def multiplier(int: Int) = int * factor
    }
    def getValue={
      (1 to 10) filter(_ % 2 == 0) map Multiplier.multiplier reduce(_ * _)
    }
    println(getValue)  // 122880
    Multiplier.factor = 3
    println(getValue)  // 933120
  }
}
