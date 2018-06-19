package com.scala.progscala.chapter06

/**
  * 映射
  *
  * map 方法返回一个与原集合类型大小相同的新集合，其中的每个元素均由原集合的对应元素转换得到。
  * map 方法被定义于 scala.collection.TraversableLike ，被大部分集合类型继承。
  */
object Course082Mapping {

  def main(args: Array[String]): Unit = {

    // 定义了类型为 Int => String 的函数 intToString 。
    // intToString 对 List 一无所知。
    val intToString = (i:Int) => s"N=$i"
    println(intToString)

    // 用 Combinators.map 定义了一个新函数， flist 的类型是 List[Int] => List[String] 。
    // 用 map 将一个类型为 Int => String 的函数提升为类型是 List[Int] =>List[String] 的函数。
    val flist = Combinators2.map(intToString) _
    println(flist)  // <function1>

    val list = flist(List(1,2,3,4))
    println(list)   // List(N=1, N=2, N=3, N=4)
  }

  object Combinators1{
    def map[A,B](list: List[A])(f:(A) ⇒ B):List[B] = list map f
  }

  object Combinators2{
    def map[A,B](f: (A) ⇒ B)(list: List[A]): List[B] = list map f
  }
}
