package com.scala.progscala.chapter03

/**
  * 无参数方法
  *
  * 对于那些不包含参数的方法而言，除了可以选择使用中缀调用或后缀调用方式之外， Scala  还允许用户灵活决定是否使用括号。
  *
  * 我们在定义无参方法时可以省略括号。一旦定义无参方法时省略了括号，那么在调用这些方法时必须省略括号。
  * 与之相反，假如在定义无参方法时添加了空括号，那么调用方可以选择省略或是保留括号。
  *
  * 为了实现与 Java  语言的互操作，无参方法定义体中出现了是否包含空括号这两种情况的处理规则之间的不一致性。
  */
object Course02EmptyArgument {

  def main(args: Array[String]): Unit = {

    // List.size 的方法定义体中省略了括号
    println(List(1,2,3).size)
//    println(List(1,2,3).size())   // 报错！

    // java.lang.String 的 length 方法定义体中则包含了括号（这是为了能在 Java  中运行）
    println("hello".length)
    println("world".length())

    // 合理考虑是否使用括号有助于构建更具表现力的方法调用链
    // 如下所示，示例中的代码看上去就像是一目了然的"句子"
    def isEven(n:Int) = (n % 2) == 0
    List(1,2,3,4) filter isEven foreach println

    // 逐步分解如下：
    List(1,2,3,4).filter((i:Int) => isEven(i)).foreach((i:Int) => println(i))
    List(1,2,3,4).filter(i=>isEven(i)).foreach(i=>println(i))
    List(1,2,3,4).filter(isEven).foreach(println)
    List(1,2,3,4) filter isEven foreach println

    /**
      * 需要澄清的是，由于上面的每个方法都接收单一参数，因此该表达式能正常运行。
      * 假如方法链中某一方法接收 0  个或大于 1  个的参数，编译器会困惑。
      * 如果出现了这种情况，请为部分或全部方法补上点号。
      */
  }
}
