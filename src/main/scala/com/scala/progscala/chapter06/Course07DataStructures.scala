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
    content6
    content7
    content8
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

    val list4 = "People" :: "shoule" :: "read" :: Nil
    println(list4)  // List(People, shoule, read)
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
    * Seq 的构造方法是 +: 而不是 :: 。
    * 当对伴随对象使用 Seq.apply 方法时，将创建出一个 List ，这是因为 Seq 只是一个特征，而不是具体的类。
    */
  def content4: Unit ={
    println("---")
    val seq1 = Seq("Programming","Scala")
    println(seq1)   // List(Programming, Scala)
    val seq2 = "People" +: "should" +: "read" +: seq1
    println(seq2)   // List(People, should, read, Programming, Scala)
    val seq3 = "Programming" +: "Scala" +: Seq.empty
    println(seq3)   // List(Programming, Scala)
    val seq4 = "People" +: "should" +: "read" +: Seq.empty
    println(seq4)   // List(People, should, read)
    val seq5 = seq4 ++ seq3
    println(seq5)   // List(People, should, read, Programming, Scala)
  }

  /**
    * 序列类型还定义了 :+ 和 +: 方法。
    */
  def content5: Unit ={
    val seq1 = Seq("Programming","Scala")
    println(seq1)   // List(Programming, Scala)
    val seq2 = seq1 :+ "Rocks!"
    println(seq2)   // List(Programming, Scala, Rocks!)
  }

  /**
    * 也可以考虑用 immutable.Vector 代替 List
    * 因为 immutable.Vector 的所有操作都是 O (1) （常数时间）
    * 而 List 对于那些需要访问头部以外元素的操作，都需要 O (n )  操作。
    */
  def content6: Unit ={
    println("---")
    val vect1 = Vector("Programming","Scala")
    println(vect1)   // Vector(Programming, Scala)
    val vect2 = "People" +: "should" +: "read" +: vect1
    println(vect2)   // Vector(People, should, read, Programming, Scala)
    val vect3 = "Programming" +: "Scala" +: Vector.empty
    println(vect3)   // Vector(Programming, Scala)
    val vect4 = "People" +: "should" +: "read" +: Vector.empty
    println(vect4)   // Vector(People, should, read)
    val vect5 = vect4 ++ vect3
    println(vect5)   // Vector(People, should, read, Programming, Scala)
    // 能够以常数时间复杂度获取任意元素：
    println(vect5(3)) // Programming
  }

  /**
    * 映射表
    * Scala  支持对映射表采用以下特殊的初始化语法
    * 映射表与 map 方法有一定程度的类似，前者每个键都对应一个值，后者每个输入元素都产生一个输出元素。
    */
  def content7: Unit ={
    val stateCapitals = Map(
      "Alabama" -> "Montgomery",
      "Alaska" -> "Juneau",
      "Wycming" -> "Cheyenne"
    )
    println(stateCapitals)

    val lengths = stateCapitals map {kv => (kv._1,kv._2.length)}
    println(lengths)

    val caps = stateCapitals map {case(k,v) => (k,v.toUpperCase)}
    println(caps)

    val stateCapitals2 = stateCapitals + (
      "Virginia" -> "Richmond"
    )
    println(stateCapitals2)

    val stateCapitals3 = stateCapitals2 + (
      "New York" -> "Albany","Illinois" -> "Springfield"
    )
    println(stateCapitals3)
  }

  /**
    * 集合
    *
    * 集合是无序集合类型的一个例子，所以集合不是序列。集合同样要求元素具有唯一性
    */
  def content8: Unit ={
    val states = Set("Alabama","Alaska","Wyoming")
    println(states)

    val lengths = states map (st => st.length)
    println(lengths)

    val states2 = states + "Virginia"
    println(states2)

    val states3 = states2 + ("New York","Illinois")
    println(states3)
  }
}
