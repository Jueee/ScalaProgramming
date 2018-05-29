package com.scala.progscala.chapter02

/**
  * 封闭类的继承
  *
  * Option 的一个关键点在于它只有两个有效的子类。
  * 如果我们有值，则对应 Some 子类；如果没有值，则对应 None 子类。没有其他有效的 Option 子类型。
  * 所以，我们可以防止用户创建一个他们自己的子类。
  *
  * 为了达到这个目的， Scala  设计了关键字 sealed 。
  * 关键字 sealed 告诉编译器，所有的子类必须在同一个源文件中声明。
  *
  * 如果需要防止用户派生任何子类，也可以用 final 关键字进行声明。
  */
object Course10SealedClass {

  /**
    * Option 类的声明：
    * sealed abstract class Option[+A] extends Product with Serializable {
    *
    */
}
