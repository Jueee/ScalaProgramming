package com.scala.progscala.chapter03

/**
  * 条件操作符
  *
  *
  */
object Course08ConditionalOperators {

  /*

Scala  从 Java  及其祖先处继承了大多数的条件操作符：

操作符		操作			  描述
&&			和操作			操作符左边和右边的值都为true。只有当操作符左边的值为真值时才会评估右边值是否为真
||			或操作			操作符左边和右边值至少有一个为true。只有当操作符左边值为false时才会评估右边值是否为真
>			  大于			  操作符左边的值应大于右边的值
>=			大于或等于	操作符左边的值大于或等于右边的值
<			  小于			  操作符左边的值应小于右边的值
<=			小于或等于	操作符左边的值小于或等于右边的值
==			等于			  操作符左边的值等于右边的值
!=			不等于			操作符左边的值不等于右边的值

 && 和 || 是 短路 （ short-circuiting ）操作符，一旦得知结果，这些操作便会停止对表达式估值。

 Scala  使用 == 符执行逻辑意义上的相等检查，不过该操作符也调用了 equals 方法。
 假如你并不希望进行逻辑相等检查，而只想比较引用，你可以使用 Scala  提供的新的方法 eq 。
   */
}
