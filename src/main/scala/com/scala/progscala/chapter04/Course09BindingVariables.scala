package com.scala.progscala.chapter04

/**
  * 再谈 case 语句的变量绑定
  *
  * 假设以下场景：你需要从对象中提取值，但你又想将一个变量赋给该对象的整体。
  *
  * p @ … 的语法将整个 Person 类的实例赋值给了变量 p ，类似地， a @ … 也将整个 Address 实例赋值给了变量。
  *
  * 如果不需要从 Person 实例中提取属性值，我们只要写为 p: Person => … 就可以了。
  */
object Course09BindingVariables {

  def main(args: Array[String]): Unit = {

    case class Address(street: String, city: String, country: String)
    case class Person(name: String, age: Int, address: Address)

    val alice = Person("Alice", 25, Address("1 Scala Lane", "Chicago", "USA"))
    val bob = Person("Bob", 29, Address("2 Java Ave.", "Miami", "USA"))
    val charlie = Person("Charlie", 32, Address("3 Python Ct.", "Boston", "USA"))

    for (person <- Seq(alice, bob, charlie)){
      person match {
        case p @ Person("Alice", 25, address) => println(s"Hi Alice! $p")
        case p @ Person("Bob", 29, a @ Address(street, city, country)) =>
          println(s"Hi ${p.name}! age ${p.age}, in ${a.city}")
        case p @ Person(name,age,_) =>
          println(s"Who are you, $age year-old person named $name? $p")
        case _ => println("other person!")
      }
    }
  }
}
