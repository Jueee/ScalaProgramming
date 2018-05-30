package com.scala.progscala.chapter03

/**
  * 可插入字符串
  *
  * 假如某一字符串的形式为 s"foo ${bar}" ，那么表达式 bar 将会被转化为字符串并被插入到原字符串中的 $"{bar}" 的位置中。
  * 假如表达式 bar 返回的不是字符串，而是某一类型的实例，那么只要该实例中定义了 toString 方法，系统便会调用该方法将该实例转化成字符串。
  * 如果 bar 表达式返回值无法转换成字符串，那么程序将会报错。
  *
  * Scala  中存在两类可插入字符串：
  * 第一类采用 printf  格式，这类可插入字符串以 f 为前缀。
  * 第二类也被称为 “ 原生的 ” 可插入字符串，这类字符串并不会对像 \n 这样的逃逸字符串进行扩展。
  */
object Course13InterpolatedStrings {

  def main(args: Array[String]): Unit = {
    // 如果 bar 是一个变量引用，那么可以省略字符串中的大括号
    val name = "Buck Trends"
    println(s"Hello, ${name}")
    println(s"Hello, $name")

    // 如果想在可插入字符串中输入美元符，那么请连续输入两个美元符 $$ 。
    val num = 6.43
    println(s"price is $$$num")

    /**
      * 为了打印出一个美元符号，我们使用了两个美元符 $$
      * 使用了两个百分号 %% 以打印出一个百分号
      * 表达式 ${gross}%.2f 会格式化 gross 的变量值，保留其小数点后两位数字。
      */
    val gross = 100000F
    val net = 64000F
    val percent = (net / gross) * 100
    println(f"$$${gross}%.2f vs. $$${net}%.2f or ${percent}%.1f%%") // $100000.00 vs. $64000.00 or 64.0%
    println(f"$name:$$${gross}%.2f vs. $$${net}%.2f or ${percent}%.1f%%") // Buck Trends:$100000.00 vs. $64000.00 or 64.0%
    println(s"$name:$$${gross}%.2f vs. $$${net}%.2f or ${percent}%.1f%%") // Buck Trends:$100000.0%.2f vs. $64000.0%.2f or 64.0%.1f%%

    //
    val i = 200
    println(i)
    println(f"${i}%.2f")
    val d = 100.22
    println(f"${d}%.2f")
//    println(f"{d}%2d")  // 尝试将 Double 类型值展现为 Int 类型，编译报错

    // String.format 将按照 printf 的格式对字符串格式化。
    val s = "%04d:name = %s".format(5,"Dean Wampler")
    println(s)  // 0005:name = Dean Wampler

    println(s"123\n$name\n456")
  }
}
