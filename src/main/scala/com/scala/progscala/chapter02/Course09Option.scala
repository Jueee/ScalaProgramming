package com.scala.progscala.chapter02

/**
  * Option 、 Some 和 None ：避免使用 null
  *
  * Option 、 Some 和 None ，它们可以表示 “ 有值 ” 或者 “ 没有值 ” 。
  * 作为一个抽象类， Option 却有两个具体的子类Some和 None。Some 用于表示有值，None用于表示没有值。
  *
  * 【注】假如你需要对 Option 对象进行检测，当它是 Some 对象时执行一些操作，而当它是 None 对象时则不进行任何操作，那么你可以使用 for 推导式
  */
object Course09Option {

  def main(args: Array[String]): Unit = {
    val stateCapitals = Map(
      "Alabama" -> "Montgomery",
      "Alaska" -> "Juneau",
      // ...
      "Wyoming" -> "Cheyenne"
    )
    println("Get the capitals wrapped in Options:")
    println("Alabama: " + stateCapitals.get("Alabama")) // 返回了 Option[T] ，这里类型 T 为 String
    println("Wyoming: " + stateCapitals.get("Wyoming"))
    println("Unknown: " + stateCapitals.get("Unknown")) // 返回 None ，而不是 null

    println("Get the capitals themselves out of the Options:")
    println("Alabama: " + stateCapitals.get("Alabama").get) // 取出其中包含的值
    println("Wyoming: " + stateCapitals.get("Wyoming").getOrElse("Oops!"))
    println("Unknown: " + stateCapitals.get("Unknown").getOrElse("Oops2!")) // getOrElse 的参数起到了默认值的作用
//    println("Unknown: " + stateCapitals.get("Unknown").get) // 会报错：NoSuchElementException: None.get

    println("[Option.empty]"+Option.empty)

  }
}
