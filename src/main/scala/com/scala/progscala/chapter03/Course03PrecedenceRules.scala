package com.scala.progscala.chapter03

/**
  * 优先级规则
  *
  * 按从低到高的顺序列出优先级规则：
    1. 所有字母
    2. |
    3. ^
    4. &
    5. < >
    6. = !
    7. :
    8. + -
    9. * / %
    10.  其他特殊字符
  *
  * 同一行的字符具有相同的优先级。不过有一个例外，当 =  用于赋值操作时，该符号的优先级最低。
  */
object Course03PrecedenceRules {

  def main(args: Array[String]): Unit = {
    // 因为 * 和 / 的优先级相同，因此下面的两段 scala 会话将返回相同值
    println(2.0 * 4.0 / 3.0 * 5.0)
    println(((2.0 * 4.0) / 3.0) * 5.0)

    // 任何名字以冒号 (:) 结尾的方法都与右边的对象所绑定，其他方法则是左绑定的。
    // 例如，你可以通过 :: 方法将某一元素放置到列表前面，这一操作成为 cons  操作， cons  是 constructor  的缩写
    val list = List('b','c','d')
    println(list)
    'a' :: list   // 等价
    list.::('a')
    println(list)
  }
}
