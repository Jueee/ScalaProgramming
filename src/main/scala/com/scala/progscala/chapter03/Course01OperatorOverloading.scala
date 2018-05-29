package com.scala.progscala.chapter03

/**
  * 操作符重载
  *
  * 在 Scala  中，几乎所有的 “ 操作符 ” 其实都是方法。
  *
  * 如果你沉迷于创建新的操作符，一旦该操作符无法带来便利，那就意味着你凭空牺牲了方法命名的可读性。
  */
object Course01OperatorOverloading {

  def main(args: Array[String]): Unit = {

    // 由于使用中缀表示法表示单参数方法时，其中的点号和括号可以省略，因此 1 + 2 等价于 1.+(2)
    println(1 + 2)
    println(1.+(2))

    /**
      * 实际上根据操作符优先级规则，包含点号和省去点号的表达式并不总是完全一致的。 1 + 2 * 3 = 7,  而 1.+(2)*3 = 9  。
      * 如果表达式中包含点号，那么优先执行点号对应的操作，再执行乘法运算。
      * 另外如果你使用了 2.11  版本之前的 Scala ，请在数字 1  后添加一个空格，否则 1  将会被解析为 Double  类型！
      */
    println(1 + 2 * 3)  // 7
    println(1.+(2)* 3)  // 9

    // 调用无参方法时也可以省略点号。这种写法也被称为后缀表示法。
    println(1 toString)

    // 使用反引号定义标示符
    // 应用反引号定义测试名称的方法：
    def`test that addition works` = assert(1 + 1 == 2)
    println(`test that addition works`)

    /**
      * 如果需要访问的 Java  类方法或变量的名与 Scala  类的保留字相同，我们需要使用反引号命名。
      * 如： java.net.Proxy.`type`() 。
      */
  }
}
