package com.scala.progscala.chapter04

/**
  * case 类的匹配
  *
  *
  */
object Course06CaseClasses {

  def main(args: Array[String]): Unit = {
    example1
    example2
    example3
    example4
    example5
    example6
    example7
  }

  /**
    * 可以匹配嵌套类型的内容
    */
  def example1: Unit ={
    case class Address(street:String, city:String, country:String)
    case class Person(name:String, age:Int, address: Address)

    val alice = Person("Alice", 25, Address("1 Scala Lane", "Chicago", "USA"))
    val bob = Person("Bob", 29, Address("2 Java Ave.", "Miami", "USA"))
    val charlie = Person("Charlie", 32, Address("3 Python Ct.", "Boston", "USA"))

    for (person <- Seq(alice, bob, charlie)){
      person match {
        case Person("Alice", 25, Address(_,"Chicago",_)) => println("Hi Alice")
        case Person("Bob", 29, Address("2 Java Ave.","Miami", "USA" )) => println("Hi Bob")
        case Person(name,age,_) => println(s"Who are you, $age year-old person named $name")
      }
    }
  }

  /**
    * 想要将元组组成的序列连同序号一起打印出来。可以用到 Seq.zipWithIndex 方法
    * 调用 zipWithIndex 时，返回的元组形式为 ((name,cost),index) 。
    */
  def example2: Unit ={
    val itemsCosts = Seq(("Pencil", 0.52), ("Paper", 1.35), ("Notebook", 2.43))
    val itemsCostsInDices = itemsCosts.zipWithIndex
    for (itemCostIndex <- itemsCostsInDices){
      itemCostIndex match {
        case ((item,cost),index) => println(s"$index: $item costs $cost each")
      }
    }
  }

  def example3: Unit ={
    def processSeq[T](seq: Seq[T]):Unit = seq match {
      case +:(head,tail)  => printf("%s +: ",head)
        processSeq(tail)
      case Nil => print("Nil")
    }
    processSeq(List(1, 2, 3, 4, 5)) // 1 +: 2 +: 3 +: 4 +: 5 +: Nil
  }

  /**
    * 包含有两个类型参数的类型可以写为中缀表达式
    * 可以用两种形式书写类型签名： With[String,Int] 或者 String With Int 。
    */
  def example4: Unit ={
    case class With[A,B](a:A,b: B)

    val with1:With[String,Int] = With("Foo",1)
    val with2:String With Int = With("Bar",2)

    Seq(with1, with2) foreach{ w=>
      w match {
        case s With i => println(s"$s with $i")
        case _        => println(s"Unknown:$w")
      }
    }
  }

  /**
    *  Scala  库的对象 :+ 可以让你匹配 List 的最后一个元素，然后从后往前依次访问各元素
    */
  def example5: Unit ={
    val nonEmptyList = List(1,2,3,4,5)
    val nonEmptyVector = Vector(1,2,3,4,5)
    val nonEmptyMap = Map("one" -> 1, "two" -> 2, "three" -> 3)

    def reverseSeqToString[T](seq: Seq[T]) :String= seq match {
      case prefix :+ end => reverseSeqToString(prefix) + s" :+ $end"
      case Nil => "Nil"
    }

    for (seq <- Seq(nonEmptyList, nonEmptyVector, nonEmptyMap.toSeq)){
      println(reverseSeqToString(seq))
    }
  }

  /**
    * 触发 unapplySeq 方法的调用，提取元素的 “ 滑动窗口 ” ：
    */
  def example6: Unit ={
    val nonEmptyList = List(1,2,3,4,5)
    val emptyList = Nil
    val nonEmptyMap = Map("one" -> 1, "two" -> 2, "three" -> 3)

    def windows[T](seq: Seq[T]):String = seq match {
      // 提取了前两个元素，忽略了用 _* 表示的其他可变参数。 * 表示另个或多个元素，与正则表达式中的 * 类似。
      case Seq(head1,head2,_*) =>
        // 用匹配到的前两个元没素格式化字符串，同时调用 seq.tail 将提取的 “ 窗口 ” 向后移动一位。
        s"($head1, $head2), " + windows(seq.tail)
      // 匹配只有一个元素的序列，否则匹配就不完全。用 _ 表示不存在的 “ 第二个 ” 元素。
      case Seq(head,_*) => s"($head, _), " + windows(seq.tail)
      case Nil => "Nil"
    }
    for (seq <- Seq(nonEmptyList, emptyList, nonEmptyMap.toSeq)){
      println(windows(seq))
    }

    /**
      * 改写：在匹配中使用 +: ，这个写法更加优雅：
      */
    def windows2[T](seq: Seq[T]):String = seq match {
      case head1 +: head2 +: tail =>
        s"($head1, $head2), " + windows(seq.tail)
      case head +: tail => s"($head, _), " + windows(tail)
      case Nil => "Nil"
    }
    for (seq <- Seq(nonEmptyList, emptyList, nonEmptyMap.toSeq)){
      println(windows2(seq))
    }
  }

  /**
    * 滑动窗口如此有用， Seq 甚至提供了两个方法用于创建窗口
    * toSeq：惰性列表，创建时即对列表的头部元素求值，但只在需要的时候才对尾部元素求值。
    * toList：返回一个 List ，创建时就求出了所有元素的值。
    */
  def example7: Unit ={
    val seq = Seq(1,2,3,4,5)
    val slide2 = seq.sliding(2)
    println(slide2.toSeq)
    println(slide2.toList)
    println(slide2.toList)  // 再次求值，元素为空
    println(seq.sliding(3,2).toList)
  }
}
