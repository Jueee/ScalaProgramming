package com.scala.progscala.chapter05

/**
  * 合理使用隐式
  *
  *
  * 在构建 DSL  以及简化代码的 API  的过程中，隐式参数机制是一种非常强大的工具。
  * 不过由于传递的隐式参数以及隐式值几乎是不可见的，这就增加了理解代码的难度。
  * 所以，我们应该合理地使用隐式。
  *
  * 有一种可以提高隐式可见性的方法，即将隐式值统一放到名为 implicts 的特殊包或名为 Implicits 的对象中。
  * 这种方式使得读者一旦遇到导入语句中的 implicit 字样时，便会留意到除了 Scala  内置的隐式之外，还存在一些其他的隐式。
  * 值得庆幸的是，目前有些 IDE  也能够指出代码中存在的隐式。
  */
object Course08WiseUseImplicits {

}
