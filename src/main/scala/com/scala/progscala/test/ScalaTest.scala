package com.scala.progscala.test

object ScalaTest {

  def main(args: Array[String]): Unit = {
    val a = (1 to 10) filter(_%2 == 0) map (_ * 2) reduce(_ * _)
    println(a)

    var factor = 2
    val multiplier = (i:Int) => i*factor
    val b = (1 to 10) filter(_%2 ==0) map multiplier reduce(_ * _)
    println(b)  // 122880
    factor = 3
    val c = (1 to 10) filter(_%2 ==0) map multiplier reduce(_ * _)
    println(c)  // 933120

    def m1(multiplier: Int=>Int) = {
      (1 to 10) filter(_%2 ==0) map multiplier reduce(_ * _)
    }

    def m2:Int => Int = {
      val factor = 2
      val multiplier = (i:Int) => i*factor
      multiplier
    }

    println(m1(m2))
  }
}
