package com.scala.progscala.chapter05

/**
  * 类型类模式
  *
  * 如果某对象属于某类型类，那么它必须实现类型类所定义的行为，与通常意义上的类相比，类型类更接近于接口
  */
object Course04TypeClass {

  case class Address(street: String, city: String)
  case class Person(name: String, address: Address)

  /**
    * 定义了默认的缩进字符串，也定义了用于计算字段实际缩进长度的方法以及 JSON  对象中包含的闭合括号 {...}
    */
  trait ToJSON{
    // toJSON 方法的输入参数指定了当前的缩进级别；也就是说，缩进多少个 INDENTATION 单元。
    def toJSON(level:Int = 0):String
    val INDENTATION = " "
    def indentation(level:Int = 0):(String,String) =
      (INDENTATION * level, INDENTATION * (level+1))
  }

  implicit class AddressToJSON(address: Address) extends ToJSON{
    override def toJSON(level: Int): String = {
      val (outdent,indent) = indentation(level)
      s"""{
         |${indent}"street":"${address.street}",
         |${indent}"city":"${address.city}"
         |$outdent}""".stripMargin
    }
  }

  implicit class PersonToJSON(person: Person) extends ToJSON{
    override def toJSON(level: Int): String = {
      val (outdent,indent) = indentation(level)
      s"""{
         |${indent}"street":"${person.name}",
         |${indent}"address":${person.address.toJSON(level + 1)}
         |$outdent}""".stripMargin
    }
  }

  def main(args: Array[String]): Unit = {
    val a = Address("1 Scala Lane", "Anytown")
    val p = Person("Buck Trends", a)
    println(a.toJSON())
    println(p.toJSON())
  }
}
