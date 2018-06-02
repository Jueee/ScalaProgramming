package com.scala.progscala.chapter06

/**
  * 递归
  *
  * 在函数式编程中，递归比在命令式编程中更为重要。
  * 递归是实现 “ 循环 ” 的唯一方法，因为你无法修改循环变量。
  *
  * 递归是表达函数的最常用方式。
  * 然而，递归也有两个缺点：反复调用函数带来的开销；栈溢出的风险。
  */
object Course03Recursion {

  def factorial(i:BigInt):BigInt = {
    if (i == 1) i
    else i * factorial(i - 1)
  }

  def main(args: Array[String]): Unit = {
    for (i <- 1 to 10)
      println(s"$i:\t${factorial(i)}")
  }
}
