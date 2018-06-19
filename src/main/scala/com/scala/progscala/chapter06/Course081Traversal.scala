package com.scala.progscala.chapter06

/**
  * 遍历
  *
  * Scala  容器类型的标准遍历方法是 foreach ， foreach 定义于 scala.collection.IterableLike
  */
object Course081Traversal {

  def main(args: Array[String]): Unit = {
    List(1,2,3,4,5) foreach { i => println("Int:"+i)}

    val stateCapitals = Map(
      "Alabama" -> "Montgomery",
      "Alaska" -> "Juneau",
      "Wyoming" -> "Cheyenne")
    println(stateCapitals)
    println(stateCapitals foreach {case(k,v) => println(k+":"+v)})
  }
}
