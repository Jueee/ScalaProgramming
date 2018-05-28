package com.scala.progscala.chapter02

/**
  * Range
  *
  * 创建的 Range 可以包含区间上限，也可以不包含区间上限；步长默认为 1 ，也可以指定一个非 1  的步长.
  *
  * 支持Range的类型包括Int、Long、Float、Double、Char、BigInt（支持任意大小的整数）和BigDecimal（支持任意大小的浮点数）
  */
object Course03Range {

  def main(args: Array[String]): Unit = {
    // Int 类型的 Range ，包括区间上限，步长为 1 （从 1 到 10 ）
    println(1 to 10)

    // Int 类型的 Range ，不包括区间上限，步长为 1 （从 1 到 9 ）
    println(1 until 10)

    // Int 类型的 Range ，包括区间上限，步长为 3
    println(1 to 10 by 3)

    // Int 类型的递减 Range ，包括区间下限，步长为 -3
    println(10 to 1 by -3)

    // Long 类型
    println(1L to 10L by 3)

    // Float 类型的 Range ，步长可以不等于 1
    println(1.1f to 10.3f by 3.1f)

    // Float 类型的 Range ，步长可以小于 1
    println(1.1f to 10.3f by 0.5f)

    // Double 类型
    println(1.1 to 10.3 by 3.1)

    // Char 类型
    println('a' to 'g' by 2)

    // BigInt 类型
    println(BigInt(1) to BigInt(10) by 3)

    // BigDecimal 类型
    println(BigDecimal(1.1) to BigDecimal(10.3) by 3.1)
  }
}
