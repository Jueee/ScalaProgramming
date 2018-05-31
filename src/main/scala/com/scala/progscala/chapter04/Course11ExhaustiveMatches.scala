package com.scala.progscala.chapter04

/**
  * 封闭继承层级与全覆盖匹配
  *
  * 对封闭基类的实例做模式匹配时，如果 case 语句覆盖了所有当前文件定义的类型，那么匹配就是全覆盖的。
  * 由于不允许有其他用于自定义的子类型，随着项目演进，匹配的全覆盖性也不会丧失。
  *
  * 如果类型的继承层级可能发生变化，就应该避免使用 sealed 。
  * 这取决于你原有的面向对象继承规则，包括多态方法的设计情况。
  */
/**
  * 示例：使用封闭继承层级类来表示 HTTP  协议的合法消息类型（或称为 “ 方法 ” ）。
  *
  * 如果你去掉 HttpMethod 的 sealed 关键字，然后在本文件或其他文件新定义一个子类型，会怎么样呢？
  * 你必须在代码库以及客户端的代码库中找出并修改所有关于 HttpMethod 实例的模式匹配代码。
  */
object Course11ExhaustiveMatches {

  // 定义了一个封闭的抽象基类
  sealed abstract class HttpMethod{
    def body:String     // 为 HTTP  报文的消息体定义了一个方法
    def bodyLength = body.length
  }

  // 定义了 8  个继承自 HttpMethod 的 case  类，每个类均在构造方法中声明了参数 body: String 。
  case class Connect(body:String) extends HttpMethod
  case class Delete (body:String) extends HttpMethod
  case class Get    (body:String) extends HttpMethod
  case class Head   (body:String) extends HttpMethod
  case class Options(body:String) extends HttpMethod
  case class Post   (body:String) extends HttpMethod
  case class Put    (body:String) extends HttpMethod
  case class Trace  (body:String) extends HttpMethod

  // 全覆盖的模式匹配表达式。
  // 即使我们没有默认的匹配子句，也可以达到全覆盖，因为输入的参数 method 只可能是我们定义的 8  个 case  类的实例。
  def handle(method: HttpMethod) = method match {
    case Connect  (body) => s"connect :(length:${method.bodyLength}) $body"
    case Delete   (body) => s"Delete  :(length:${method.bodyLength}) $body"
    case Get      (body) => s"Get     :(length:${method.bodyLength}) $body"
    case Head     (body) => s"Head    :(length:${method.bodyLength}) $body"
    case Options  (body) => s"Options :(length:${method.bodyLength}) $body"
    case Post     (body) => s"Post    :(length:${method.bodyLength}) $body"
    case Put      (body) => s"Put     :(length:${method.bodyLength}) $body"
    case Trace    (body) => s"Trace   :(length:${method.bodyLength}) $body"
  }

  def main(args: Array[String]): Unit = {

    val methods = Seq(
      Connect ("Connet body..."),
      Delete  ("Delete body..."),
      Get     ("Get body..."),
      Head    ("Head body..."),
      Options ("Options body..."),
      Post    ("Post body..."),
      Put     ("Put body..."),
      Trace   ("Trace body...")
    )

    methods foreach(method => println(handle(method)))
  }
}
