package com.scala.progscala.chapter04

/**
  * 序列的匹配
  *
  * Seq 是具体的集合类型的父类型，这些集合类型支持以确定顺序遍历其元素，如 List 和 Vector 。
  */
object Course03MatchSequences {

  def main(args: Array[String]): Unit = {
    example1
  }

  /**
    * 用模式匹配和递归方法遍历 Seq 的传统方法
    */
  def example1: Unit ={

    val nonEmptySeq = Seq(1,2,3,4,5)    // 非空的 Seq[Int] （事实上返回了一个 List ）
    val emptySeq = Seq.empty[Int]
    val nonEmptyList = List(1,2,3,4,5)  // 非空的 List[Int] （ Seq 的一个子类型）
    val emptyList = Nil
    val nonEmptyVector = Vector(1,2,3,4,5)  // 非空的 Vector[Int]（ Seq 的一个子类型）
    val emptyVector = Vector.empty[Int]
    val nonEmptyMap = Map("one" -> 1, "two" -> 2, "three" -> 3)
    val  emptyMap = Map.empty[String,Int]

    /**
      * 从 Seq[T] 中构造 String ， T 为某种待定的类型。方法体是用来与输入的 Seq[T] 相匹配。
      * @param seq
      * @tparam T
      * @return
      */
    def seqToString[T](seq: Seq[T]):String = seq match {
      case head +: tail => s"$head +: " + seqToString(tail)
      case Nil => "Nil"
    }

    // 任何 Seq 的尾部都可以认为是以一个相应类型的空 Seq ，事实上， List 就是这么实现的。
    // List 打印结果：1 +: 2 +: 3 +: 4 +: 5 +: Nil
    for (seq <- Seq(
        nonEmptySeq,emptySeq,nonEmptyList,emptyList,
        nonEmptyVector,emptyVector,nonEmptyMap.toSeq,emptyMap.toSeq)){
      println(seqToString(seq))
    }
  }
}
