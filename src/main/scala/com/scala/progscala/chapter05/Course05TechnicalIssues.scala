package com.scala.progscala.chapter05

/**
  * 隐式所导致的技术问题
  *
  * 1、编译那些大量应用隐式的项目需要耗费很多的时间。
  * 2、隐式转换同样会造成额外的运行开销，这是因为封装类型会引入额外的中间层。
  * 3、当隐式特征与其他 Scala  特征，尤其是子类型特征发生交集时，会产生一些技术问题
  *
  * 无论何时都要为隐式转换方法指定返回类型。否则，类型推导推断出的返回类型可能会导致预料之外的结果。
  *
  */
object Course05TechnicalIssues {
  trait Stringizer[+T]{
    def stringize:String
  }

  implicit class AnyStringizer(a:Any) extends Stringizer[Any]{
    override def stringize: String = a match {
      case s:String => s
      case i:Int => (i*10).toString
      case f:Float => (f*10.1).toString
      case other => throw new UnsupportedOperationException(s"Can't stringize $other")
    }
  }

  def main(args: Array[String]): Unit = {
    val list:List[Any] = List(1, 2.2F, "three", 'symbol)

    list.foreach((x:Any) =>
      try {
        println(s"$x:${x.stringize}")
      } catch {
        case e:java.lang.UnsupportedOperationException => println("ERROR:"+e)
      }
    )
  }
}
