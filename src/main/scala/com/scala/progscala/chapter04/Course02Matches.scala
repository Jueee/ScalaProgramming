package com.scala.progscala.chapter04

/**
  * match 中的值、变量和类型
  *
  *
  */
object Course02Matches {

  def main(args: Array[String]): Unit = {
    example1
    example2
    example3
  }

  def example1: Unit ={

    /**
      * match 匹配特定的某个值，也能匹配特定类型的所有值
      * 同时展示了 default  语句的写法来匹配任意输入值。
      */
    for {
      x <- Seq(1, 2, 2.7, "one", "two", 'four,true)  // 由于序列元素包含不同类型，因此序列的类型为 Seq[Any]
    } {
      // 编译器会推断所有 case 子句返回值类型的最近公共父类型 （也称为最小公共上限）作为返回值类型。
      val str = x match {   //  x 的类型为 Any
        case 1 => "int 1"
        case i:Int => "other int:"+i  //  匹配除 1 外的其他任意整数值
        case d:Double => "a double :" + x //  匹配所有 Double 类型， x 的值被赋给 Double 型变量 d 。
        case "one" => "String one"
        case s:String => "other string:"+s  //  匹配除 “one” 外的其他任意字符串
        case unexpected => "unexpected value:" + unexpected //  匹配其他任意输入， x 的值被赋给 unexpected 这个变量。
        case "error" => "错误！默认子句必须是最后一个子句。"
      }
      println(str)
    }

    /**
      * 用占位符 _ 替换了变量 i 、 d 、 s 和 unexpected 。
      * 事实上我们并不需要这些类型的对应变量值，只需要产生字符串。所以，可以在所有子句中使用 x 。
      */
    for {
      x <- Seq(1, 2, 2.7, "one", "two", 'four,true)  // 由于序列元素包含不同类型，因此序列的类型为 Seq[Any]
    } {
      val str = x match {   //  x 的类型为 Any
        case 1 => "int 1"
        case _:Int => "other int:"+x  //  匹配除 1 外的其他任意整数值
        case _:Double => "a double :" + x //  匹配所有 Double 类型， x 的值被赋给 Double 型变量 d 。
        case "one" => "String one"
        case _:String => "other string:" + x  //  匹配除 “one” 外的其他任意字符串
        case _ => "unexpected value:" + x //  匹配其他任意输入， x 的值被赋给 unexpected 这个变量。
      }
      println(str)
    }
  }

  def example2: Unit ={

    /**
      * 输出结果为，不符合预期：
      * found y!
        found y!
        found y!
      * case y 的含义其实是：匹配所有输入（由于这里没有类型注解），并将其赋值给新的变量 y 。
      * @param y
      */
    def checkY(y:Int) = {
      for {
        x <- Seq(99, 100, 101)
      } {
        val str = x match {
          case y => "found y!"
          case i:Int => "int:" + i
        }
        println(str)
      }
    }
    checkY(100)

    /**
      * 修改版本
      * 在 case 子句中，以小写字母开头的标识符被认为是用来提取待匹配值的新变量。
      * 如果需要引用之前已经定义的变量时，应使用反引号将其包围。
      * 与此相对，以大写字母开头的标识符被认为是类型名称。
      * @param y
      */
    def checkY2(y:Int) = {
      for {
        x <- Seq(99, 100, 101)
      } {
        val str = x match {
          case `y` => "found y!"  //  只修改了这一行 : `y`
          case i:Int => "int:" + i
        }
        println(str)
      }
    }
    checkY2(100)
  }

  /**
    * 有时不同的匹配子句需要使用相同的处理代码
    * 此时，为了避免代码冗余，我们可以将相同处理代码重构为一个单独的方法。
    * 同时， case 子句也持 “ 或 ” 逻辑，使用 |  方法即可：
    */
  def example3: Unit ={
    for {
      x <- Seq(1, 2, 2.7, "one", "two", 'four,true)  // 由于序列元素包含不同类型，因此序列的类型为 Seq[Any]
    } {
      val str = x match {   //  x 的类型为 Any
        case _:Int | _:Double => "a number:"+x  //  匹配除 1 外的其他任意整数值
        case "one" => "String one"
        case _:String => "other string:" + x  //  匹配除 “one” 外的其他任意字符串
        case _ => "unexpected value:" + x //  匹配其他任意输入， x 的值被赋给 unexpected 这个变量。
      }
      println(str)
    }
  }
}
