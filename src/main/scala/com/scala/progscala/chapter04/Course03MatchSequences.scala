package com.scala.progscala.chapter04

/**
  * 序列的匹配
  *
  * Seq 是具体的集合类型的父类型，这些集合类型支持以确定顺序遍历其元素，如 List 和 Vector 。
  */
object Course03MatchSequences {

  def main(args: Array[String]): Unit = {
    example1
    example2
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
      //  case 子句只匹配至少包含一个头部元素的非空序列，它将序列的头部和剩下的部分分别提取到可变变量 head 和 tail 中。
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

    // 上述的 head 和 tail 是任意的两个变量名。
    // Seq 类型也分别存在两个名为 head 和 tail 的方法，用于提取序列的头部和尾部元素。
    println(nonEmptySeq.head)
    println(nonEmptySeq.tail)

    /**
      * Seq 的行为很符合链表的定义，因为在链表中，每个头结点除了含有自身的值以外，还指向链表的尾部（即链表剩下的元素），从而创建了一种层级结构。
      * 类似以下四个节点所组成的的序列。在这里尾部添加了一个空序列：
      * (node1, (node2, (node3, (node4, (end))))
      */
    /**
      * 上述程序的一个变体，增加了括号：
      * @param seq
      * @tparam T
      * @return
      */
    def seqToString2[T](seq: Seq[T]):String = seq match {
      //  case 子句只匹配至少包含一个头部元素的非空序列，它将序列的头部和剩下的部分分别提取到可变变量 head 和 tail 中。
      case head +: tail => s"($head +: ${seqToString2(tail)})"
      case Nil => "(Nil)"
    }
    for (seq <- Seq(
      nonEmptySeq,emptySeq,nonEmptyList,emptyList,
      nonEmptyVector,emptyVector,nonEmptyMap.toSeq,emptyMap.toSeq)){
      println(seqToString2(seq))
    }
  }

  /**
    * 可以通过复制、粘贴上一个示例的输出，重新构造出原始的对象：
    */
  def example2: Unit ={
    val s1 = (1 +: (2 +: (3 +: (4 +: (5 +: (Nil))))))
    println(s1) // List(1, 2, 3, 4, 5)

    val l = (1 :: (2 :: (3 :: (4 :: (5 :: Nil)))))
    println(l)  // List(1, 2, 3, 4, 5)

    val s2 = (("one",1) +: (("two",2) +: (("three",3) +: (Nil))))
    println(s2) // List((one,1), (two,2), (three,3))
  }
}
