package com.scala.progscala.chapter02

/**
  * 字面量
  * 字面量（literal）是用于表达源代码中一个固定值的表示法（notation）。
  */
object Course08LiteralValues {

  def main(args: Array[String]): Unit = {
    /**
      * 8.1 　整数字面量
      * Long、Int、Short、Char、Byte
      */

    /**
      * 8.2 　浮点数字面量
      * Float、Double
      */

    /**
      * 8.3 　布尔型字面量
      * Boolean:  布尔型字面量可以为 true 或 false 。
      */

    /**
      * 8.4 　字符字面量
      * Unicode:  例如'\u0041' // Unicode 中的 'A'
      */

    /**
      * 8.5 　字符串字面量
      * 字符串字面量是被双引号或者三重双引号包围的字符串序列，如 """…""" 。
      * 对于双引号包围的字符串字面量，允许出现的字符与字符字面量相同。
      * 但是，如果双引号字符出现在字符串中，则必须使用反斜杠 \ 进行转义。
      *
      * 用三重双引号包含的字符串字面量也被称为多行字符串字面量。这些字符串可以跨越多行，换行符是字符串的一部分。
      * 可以包含任意字符，可以是一个双引号也可以是两个连续的双引号，但不允许出现三个连续的双引号。
      *
      * 使用多行字符串时，你可能希望各行子串有良好的缩进以使代码美观，但却不希望多余的空格出现在字符串的输出中。
      * String.stripMargin可以解决这个问题。它会移除每行字符串开头的空格和第一个遇到的垂直分隔符 | 。
      * 如果你需要自行添加空格制造缩进，你应该在 | 后添加你要的空格。
      */

    /**
      * 8.6 　符号字面量
      * 符号字面量是单引号（ ' ）后跟上一个或多个数字、字母或下划线（ “ _ ” ），但第一个字符不能为数字。
      */

    /**
      * 8.7 　函数字面量
      * (i: Int, s: String) => s+i 是一个类型为 Function2[Int,String,String]（返回值类型为 String ）的函数字面量。
      */

    /**
      * 8.8 　元组字面量
      * Scala  库中包含 TupleN 类（如 Tuple2 ），用于组建 N  元素组，它以小括号加上逗号分隔的元素序列的形式来创建元素组。
      * 元组的实例是不可变的、first-class的值，所以你可以将它们赋值给变量，将它们作为输入参数，或从方法中将其返回。
      */
    // 也可以用字面量语法来声明 Tuple 类型的变量：
    val t1:(Int,String) = (1,"two")
    val t2:Tuple2[Int,String] = (1,"two")

    // 用字面量语法构造一个三个参数的元组 Tuple3
    val t = ("Hello",1,2.3)
    println(t)
    println(t._1) // 从元组中提取第一个元素
    println(t._2)
    println(t._3)

    // 声明了三个变量， t1 、 t2 、 t3 ，用元组中三个对应的元素对其赋值。
    val (t4,t5,t6) = Tuple3("World","!",0x22)
    println(t4+","+t5+","+t6)

    val (t7,t8,t9) = ("World","!",0x22)
    println(t7+", "+t8+", "+t9)

    /**
      * 一个两元素的元组，有时也被简称为 pair 。
      * 有很多定义 pair  的方法，除了在圆括号中列出元素值以外，还可以把 “ 箭头操作符 ” 放在两个值之间，也可以用相应类的工厂方法
      */
    val pair1 = (1,"one")
    println(pair1)

    val pair2 = 1 -> "one"
    println(pair2)

    val pair3 = 1 → "one"     //  用 →  代替 ->
    println(pair3)

    val pair4 = Tuple2(1,"one")
    println(pair4)
  }
}
