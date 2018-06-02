package com.scala.progscala.chapter06

import scala.annotation.tailrec


/**
  * 尾部调用和尾部调用优化
  *
  * 有一种特殊的递归被称为尾递归。
  * 在尾递归中，函数可以调用自身，并且该调用是函数的最后一个（ “ 尾部 ” ）操作。
  * 尾递归非常重要，因为这是能把函数优化为循环的最重要的一种递归。
  * 循环可以消除潜在的栈溢出风险，同时也因为消除了函数调用开销而提升效率。
  *
  *  Scala  有一个标记 @tailrec ，可以添加在你认为是尾递归的递归函数中。
  *  如果编译器无法对它做尾递归优化，系统将抛出异常
  *
  *  定义一个嵌套的尾递归函数，将累积值作为参数，是将很多普通递归算法转为尾递归的实用技巧。
  *
  *  当一个调用了自身的方法，有可能被子类型中的同名方法覆写时，尾递归是无效的。
  *  所以，尾递归的方法必须用 private 或 final 关键字声明，或者将它嵌套在另一个方法中。
  */
object Course04TailCalls {

  /**
    * 计算阶乘
    * @param i
    * @return
    */
  def factorial(i:BigInt):BigInt = {
    @tailrec
    def fact(i:BigInt, accumulator:BigInt):BigInt = {
      if (i == 1) accumulator
      else fact(i-1, i*accumulator)
    }
    fact(i,1)
  }

  /**
    * trampoline （原意为 “ 蹦床 ” ）是指通过依次调用各个函数完成一系列函数之间的循环。暗喻在多个函数之间反复来回调用。
    * 递归：函数 A 不调用自身，而是调用了另一个函数 B ；而函数 B 又调用了 A ， A 再次调用 B ，如此循环。
    * 确定一个数是否为偶数的方法，但效率不高。（因为 isEven 和 isOdd 互相引用。）
    */
  def trampoline(): Unit ={
    import scala.util.control.TailCalls._

    def isEven(xs:List[Int]):TailRec[Boolean] =
      if (xs.isEmpty) done(true) else tailcall(isOdd(xs.tail))

    def isOdd(xs: List[Int]):TailRec[Boolean] =
      if (xs.isEmpty) done(false) else tailcall(isEven(xs.tail))

    for(i <- 1 to 5){
      val even = isEven((1 to i).toList).result
      println(s"$i is even? $even")
    }
  }


  def main(args: Array[String]): Unit = {
    for (i <- 1 to 100)
      println(s"$i:\t${factorial(i)}")

  }
}
