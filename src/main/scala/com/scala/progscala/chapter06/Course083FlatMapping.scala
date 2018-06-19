package com.scala.progscala.chapter06

/**
  * 扁平映射
  *
  * flatMap 是 Map 操作的一种推广。
  * 在 flatMap 中，我们对原始集合中的每个元素，都分别产生零或多个元素。
  * 我们传入一个函数，该函数对每个输入返回一个集合，而不是一个元素。
  * 然后 flatMap 把生成的多个集合 “ 压扁 ” 为一个集合。
  *
  * 以下就是 TraversableLike 中 flatMap 方法的简化版签名，同时还给出了 map 方法的签名作为对比：
  * def flatMap[B](f: A => GenTraversableOnce[B]): Traversable[B]
  * def map[B](f: (A) => B): Traversable[B]
  *
  * 要注意的是， flatMap 不能处理超过一层的集合。
  * 如果函数返回的是深层嵌套的集合，那么集合只能被压扁一层。
  */
object Course083FlatMapping {

  def main(args: Array[String]): Unit = {
    /**
      * 对每个字符串调用 toList ，生成 List[Char] 。
      * 这些嵌套的列表随后被 “ 压扁 ” 为最终的 List[Char] 。
      */
    val list = List("now","is","","the","time")
    println(list)
    // List(now, is, , the, time)
    println(list flatMap(s => s.toList))
    // List(n, o, w, i, s, t, h, e, t, i, m, e)


    /**
      * 在这里，起媒介作用的临时变量是集合 List[List[Char]] 。
      * 然而， flatMap 比连续调用以上两个方法更高效，因为 flatMap 不需要创建临时变量。
      */
    val list2 = List("now","is","","the","time") map(s=>s.toList)
    println(list2)
    // List(List(n, o, w), List(i, s), List(), List(t, h, e), List(t, i, m, e))
    println(list2.flatten)
    // List(n, o, w, i, s, t, h, e, t, i, m, e)

  }
}
