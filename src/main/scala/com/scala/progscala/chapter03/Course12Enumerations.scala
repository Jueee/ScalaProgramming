package com.scala.progscala.chapter03

import com.scala.progscala.chapter03.Breed.Breed
import com.scala.progscala.chapter03.WeekDay.WeekDay

/**
  * 枚举
  *
  *
  */
/**
  * 每个犬种声明均调用了接收单一字符串参数的 Value 方法。
  * 调用 Value.toString 方法将返回该字符串。
  */
object Breed extends Enumeration{
  type Breed = Value
  val doberman = Value("Doberman Pinscher")
  val yorkie = Value("Yorkshire Terrier")
  val scottie = Value("Scottish Terrier")
  val dane = Value("Great Dane")
  val portie = Value("Portuguese Water Dog")
}

object WeekDay extends Enumeration{
  type WeekDay = Value
  val Mon, Tue, Wed, Thu, Fri, Sat, Sun = Value
}

object Course12Enumerations {
  def main(args: Array[String]): Unit = {
    //  打印所有犬种及其 ID 列表
    println("ID\tBreed")
    for (breed <- Breed.values) println(s"${breed.id}\t$breed")

    //  打印所有犬种列表
    println("\nJust Terriers:")
    Breed.values filter(_.toString.endsWith("Terrier")) foreach println

    println("\nTerriers Again:")
    def isTerrier(breed: Breed) = breed.toString.endsWith("Terrier")
    Breed.values filter isTerrier foreach println

    println("\nPrint One:")
    println(Breed.doberman)
    println(Breed.doberman.toString)


    def isWordingDay(d:WeekDay) = !(d==WeekDay.Sat || d== WeekDay.Sun)
    WeekDay.values filter isWordingDay foreach println
  }

}
