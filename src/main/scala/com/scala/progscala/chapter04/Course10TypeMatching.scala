package com.scala.progscala.chapter04

/**
  * 再谈类型匹配
  *
  *
  */
object Course10TypeMatching {

  /**
    * 试图将输入的 List[Double] 和 List[String] 区分开：
    * @param args
    */
  def main(args: Array[String]): Unit = {
    example1
    example2
  }

  /**
    * 错误版本，输出如下：
    * (seq double,List(1, 2, 3, 4, 5))
    * (seq double,List(a, b, c))
    *
    * JVM  的字节码不会记住一个泛型实例（如 List ）中实际传入的类型参数的信息。
    * 编译器认为第二个匹配 List[String] 的 case 子句是不可达代码
    * 意味着第一个匹配 List[Double] 的 case 子句可以匹配任意 List 。
    */
  def example1: Unit ={
    for (x <- Seq(List(1,2,3,4,5), List("a","b","c"))) yield {
      val result = x match {
        case seqd: Seq[Double] => ("seq double", seqd)
        case seqs: Seq[String] => ("seq string", seqs)
        case _                 => ("unknown!",x)
      }
      println(result)
    }
  }

  /**
    * 一个不太美观但却有效的解决方法是：
    * 首先匹配集合，然后用一个嵌套的匹配语句去匹配集合中的第一个元素，从而决定其类型。
    * 这样的话，我们也就必须单独处理空序列
    */
  def example2: Unit ={
    def doSeqMatch[T](seq: Seq[T]):String = seq match {
      case Nil => "Nothing"
      case head +: _ => head match {
        case _ : Double => "Double"
        case _ : String => "String"
        case _ : Int => "Int"
        case _ => "Unmatched seq element"
      }
    }

    for (x <- Seq(List(1,2,3,4,5),List(1,2,3,4,5.4), List("a","b","c"), Nil)) yield {
      val result = x match {
        case seq:Seq[_]  => (s"seq ${doSeqMatch(seq)}",seq)
        case _  => ("unknoen!",x)
      }
      println(result)
    }
  }
}
