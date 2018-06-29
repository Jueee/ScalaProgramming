package com.scala.progscala.chapter06

/**
  * 向左遍历与向右遍历
  *
  *
  */
object Course09Traversals {

  def main(args: Array[String]): Unit = {
    content1
    content2
    content3
    content4
    content5
    content6
  }

  /**
    * 选择不同的方法似乎对结果并没有产生影响。
    * 原因在于我们使用的匿名函数 _ * _ 和 _ + _ 满足交换律和结合律。
    */
  def content1={
    val a = (List(1,2,3,4,5) fold 10)(_ * _)
    println(a)  // 1200

    val b = (List(1,2,3,4,5) foldLeft 10)(_ * _)
    println(b)  // 1200

    val c = (List(1,2,3,4,5) foldRight 10)(_ * _)
    println(c)  // 1200

    val d = List(1,2,3,4,5) reduce (_ + _)
    println(d)  // 15

    val e = List(1,2,3,4,5) reduceLeft (_ + _)
    println(e)  // 15

    val f = List(1,2,3,4,5) reduceRight (_ + _)
    println(f)  // 15
  }

  /**
    * 用满足交换律和结合律的函数来比较 foldLeft 和 foldRight ，以便使得参数更清晰
    */
  def content2={
    val facLeft = (accum:Int, x:Int) => accum + x
    val facRight = (x:Int, accum:Int) => accum + x

    val list1 = List(1,2,3,4,5)

    //  ((((1 + 2) + 3) + 4) + 5)
    println(list1 reduceLeft( facLeft))  // 15

    //  ((((5 + 4) + 3) + 2) + 1)
    println(list1 reduceRight(facRight))  // 15
  }

  /**
    * 采用了一个满足结合律但不满足交换律的函数
    */
  def content3={
    val facLeft = (accum:Int, x:Int) => accum - x
    val facRight = (x:Int, accum:Int) => accum - x

    val list1 = List(1,2,3,4,5)

    //  ((((1 - 2) - 3) - 4) - 5)
    println(list1 reduceLeft( facLeft))  // -13

    //  ((((5 - 4) - 3) - 2) - 1)
    //  (-1 + (-2 + (-3 + (-4 + 5)))) // 重新排列的形式
    println(list1 reduceRight(facRight))  // -5
  }

  /**
    * 使用既不满足交换律，也不满足结合律的函数
    */
  def content4={
    val fnacLeft = (x:String, y:String) => s"($x-$y)"
    val fnacRight = (x:String, y:String) => s"($y-$x)"

    val list1 = List(1,2,3,4,5) map(_.toString)

    println(list1 reduceLeft( fnacLeft))  // ((((1-2)-3)-4)-5)

    println(list1 reduceRight(fnacRight))  // ((((5-4)-3)-2)-1)
  }

  def content5={

  }

  /**
    * 无限数字流的有趣例子 —— 斐波那契序列
    */
  def content6={
//    import  scala.math.BigInt
//    val fibs:Stream[BigInt] = BigInt(0) #:: BigInt(1) #:: fibs.zip(fibs.tail).map(n => n._1+n._2)
//    fibs take 10 foreach(i=>print(s"$i "))
  }
}
