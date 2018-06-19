package com.scala.progscala.chapter06

/**
  * 过滤
  *
  * 在 scala.collection.TraversableLike 中有若干不同的方法，用于完成集合的过滤作用或者返回原始集合中的一部分元素：
  *
  * def drop (n : Int) : TraversableLike.Repr
  * 除起始的 n  个元素，选择其他所有的元素组成一个新的集合并返回。如果原始集合包含的元素个数小于 n  ，则方法会返回一个空集合。
  *
  * def dropWhile (p : (A) => Boolean) : TraversableLike.Repr
  * 从头遍历，丢弃满足一定谓词的最长集合前缀。返回一个最长集合后缀，其第一个元素不满足指定的谓词 p 。
  *
  * def exists (p : (A) => Boolean) : Boolean
  * 测试在集合中是否至少有一个元素满足给定的谓词，如果存在则返回 true ，否则返回 false 。
  *
  * def filter (p : (A) => Boolean) : TraversableLike.Repr
  * 选择集合中所有满足一定谓词的元素，返回的新集合中包含了所有满足该谓词 p  的元素。元素在原集合中的顺序可以得到保持。
  *
  * def filterNot (p : (A) => Boolean) : TraversableLike.Repr
  * 是 filter 的 “ 反义词 ” 。在遍历原集合时，选择那些不满足给定谓词 p 的元素并组成新集合返回。
  *
  * def find (p : (A) => Boolean) : Option[A]
  * 遍历原集合，寻找第一个满足给定谓词的元素。如果存在这一元素，返回 Option ，且 Option 中包含满足谓词 p 的第一个元素；否则返回 None 。
  *
  * def forall (p : (A) => Boolean) : Boolean
  * 测试集合中所有元素是否均满足给定的谓词。如果所有元素均满足谓词 p ，则返回 true ，否则返回 false 。
  *
  * def partition (p : (A) => Boolean): (TraversableLike.Repr, TraversableLike.Repr)
  * 根据谓词，将可遍历集合分成两个子集合。
  * 返回值是两个集合：第一个集合包含所有满足谓词 p 的元素，而第二个集合包含所有不满足谓词 p 的元素。
  * 两个集合中元素间的顺序均与原集合保持一致。
  *
  * def take (n : Int) : TraversableLike.Repr
  * 选择前 n  个元素。返回一个可遍历集合，包含原集合的前 n  个元素，如果原集合包含的元素小于 n  个，则返回原集合本身。
  *
  * def takeWhile (p : (A) => Boolean) : TraversableLike.Repr
  * 选择满足特定谓词的最长集合前缀。返回的可遍历集合包含一个最长集合前缀，其中的每个元素均满足谓词 p 。
  */
object Course084Filtering {
  def main(args: Array[String]): Unit = {
    val stateCapitals = Map(
      "Alabama" -> "Montgomery",
      "Alaska" -> "Juneau",
      "Wyoming" -> "Cheyenne")
    println(stateCapitals)

    val map2 = stateCapitals filter{kv => kv._1 startsWith "A"}
    println(map2)   // Map(Alabama -> Montgomery, Alaska -> Juneau)
  }

}
