package com.scala.progscala.chapter06

/**
  * 函数式编程的数据结构
  *
  * 函数式语言没有那么多的重复发明，它将重点放在使用核心数据结构和算法实现业务逻辑上。
  */
/**
  * 一、序列
  *  collection.Seq 是一个 trait ，是所有可变或不可变序列类型的抽象
  *  其子 trait collection.mutable.Seq 及 collection.immutable.Seq 分别对应可变和不可变序列。
  *
  *  通常，向列表里追加元素时，该元素会被追加到列表的开头，成为新列表的 “ 头部 ” 。
  *  除了头部，剩下的部分就是原列表的元素，这些元素并没有被修改，它们变成了新列表的 “ 尾部 ” 。
  *
  *
  */
object Course07DataStructures {

  def main(args: Array[String]): Unit = {
    content1
    content2
    content3
    content4
    content5
  }

  /**
    * 如何在 Scala  中创建队列
    * 可以用 List.apply 方法创建队列，然后用 :: 方法（称为 cons ，意为构造）向队列头部追加数据，从而创建新的列表。
    */
  def content1: Unit ={
    val list1 = List("Programming","Scala")
    println(list1)  // List(Programming, Scala)

    val list2 = "Peoplr" :: "shoule" :: "read" :: list1
    println(list2)  // List(Peoplr, shoule, read, Programming, Scala)
  }

  /**
    * 用 :: 方法向 Nil 空队列追加元素创建新队列
    * Nil 与 List.empty[Nothing] 是等价的，其中 Nothing 是 Scala  中所有其他类型的子类型。
    */
  def content2: Unit ={
    val list3 = "Programming" :: "Scala" :: Nil
    println(list3)  // List(Programming, Scala)

    val list4 = "Peoplr" :: "shoule" :: "read" :: Nil
    println(list4)  // List(Peoplr, shoule, read)
  }

  /**
    * 用 ++  方法将两个列表（或其他任何序列类型）连接起来
    * 不推荐方法将列表作为参数或作为返回值。而应该用 Seq ，这样的话， Seq 的任何子类型就都可以用了，包括 List 和 Vector 。
    */
  def content3: Unit ={
    val list3 = "Programming" :: "Scala" :: Nil
    val list4 = "People" :: "shoule" :: "read" :: Nil
    val list5 = list3 ++ list4
    println(list5)  // List(Programming, Scala, People, shoule, read)
  }

  /**
    *
    */
  def content4: Unit ={

  }

  /**
    *
    */
  def content5: Unit ={

  }
}
