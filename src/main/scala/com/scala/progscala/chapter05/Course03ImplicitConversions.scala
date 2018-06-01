package com.scala.progscala.chapter05

import scala.util.parsing.json.JSONObject

/**
  * 隐式转换
  *
  * 滥用隐式方法会导致难以调试的诡异行为。
  * 因此从 Scala 2.10  开始，隐式方法变成了 Scala  的 可选特性。
  * 假如你希望使用这一特性，你应该通过 import 语句 import scala.langage.implicitConversions 开启这一特性，你也可以使用全局的编译器选项 -language:implictConversions 开启该特性。
  *
  * 以下是编译器进行查找和使用转换方法时的查询规则。
  * (1)  假如调用的对象和方法成功通过了组合类型检查，那么类型转换不会被执行。
  * (2)  编译器只会考虑使用了 implicit 关键字的类和方法。
  * (3)  编译器只会考虑当前作用域内的隐式类，隐式方法，以及目标类型的伴生对象中定义的隐式方法（本文后续部分将讲讨论这种情况）。
  * (4)  隐式方法无法串行处理，我们无法通过一个中间类型，使用串行的隐式方法将起始类型转换成目标类型。
  * 编译器执行隐式转换时只会考虑那些接受单一类型实例输入且返回目标类型实例的方法。
  * (5)  假如当前适用多条转换方法，那么将不会执行转换操作。编译器要求有且必须只有一条满足条件的隐式方法，以免产生二义性。
  *
  * 在不修改源代码的情况下扩展模块的期望被称为 表达式问题 （ expression problem ）。
  *
  */
object Course03ImplicitConversions {

  def main(args: Array[String]): Unit = {
    example1
    example2
    example3
    example4
    example5
    example6
  }

  /**
    * 在 Scala  自带的 “ 封装 ” 类型中定义的隐式转换会一直出现在当前作用域中。这些隐式转换更确切的讲被定义在 Predef 中。
    */
  def example1: Unit ={
    val m = Map("one" -> 1, "two" -> 2)
    println(m)
    println(m.get("one"))

    /**
      * 定义该方法的技巧是使用一个具有 -> 方法的 “ 封装 ” 对象， Scala  已经在 Predef 对象中定义了该对象
      */
    """
    final class ArrowAssoc[A](val __leftOfArrow: A) extends AnyVal {
      // `__leftOfArrow` must be a public val to allow inlining. The val
      // used to be called `x`, but now goes by `__leftOfArrow`, as that
      // reduces the chances of a user's writing `foo.__leftOfArrow` and
      // being confused why they get an ambiguous implicit conversion
      // error. (`foo.x` used to produce this error since both
      // any2Ensuring and any2ArrowAssoc pimped an `x` onto everything)
      @deprecated("Use `__leftOfArrow` instead", "2.10.0")
      def x = __leftOfArrow

      @inline def -> [B](y: B): Tuple2[A, B] = Tuple2(__leftOfArrow, y)
      def →[B](y: B): Tuple2[A, B] = ->(y)
    }
     |由于 ArrowAssoc 类被声明为 implicit 类，因此编译器将执行下列逻辑。
     |(1)  编译器发现我们试图对 String 对象执行 -> 方法（例如 “ one ” -> 1 ）。
     |(2)  由于 String 未定义 -> 方法，编译器将检查当前作用域中是否存在定义了该方法的隐式转换。
     |(3)  编译器发现了 ArrowAssoc 类。
     |(4)  编译器将创建 ArrowAssoc 对象，并向其传入 one  字符串。
     |(5)  之后，编译器将解析表达式中的 -> 1 部分代码，并确认了整个表达式的类型与 Map.apply 方法的预期类型相吻合，即两者均为 pair  实例。
    """
    // 我们可以依照下列代码构造 Map 对象：
    val m2 = Map(ArrowAssoc("one")->1,ArrowAssoc("two")->2)
    println(m2)   // Map(one -> 1, two -> 2)
  }

  /**
    * 假如转换的目标类型的伴生对象中定义了转换方法，那么编译器会自动导入伴生对象作用域，并最后查找该作用域
    * Foo 类型的伴生对象定义了基于 String 类型的转换。
    * 而 0.m 方法试图在调用 0.m1 方法时传入 String 类型，但 0.m1 方法却期望输入 Foo 对象。
    * 尽管我们并未明确地将 Foo 伴生对象导入当前的作用域，编译器还是能够发现 Foo.fromString 转换方法的存在。
    */
  def example2: Unit ={
    """
    import scala.language.implicitConversions

    case class Foo(string: String)
    object Foo{
      implicit def fromString(string: String):Foo = Foo(string)
    }
    class O{
      def m1(foo: Foo) = println(foo)
      def m(string: String) = m1(string)
    }
    """
  }

  /**
    *  Scala  为像 String 和 Array 这样的 Java  类型提供了一些隐式封装类型。
    *  例如：下面代码中出现的方法看上去像是 String 类方法，但是这些方法实际上是由 WrappedString 类型实现的：
    */
  def example3: Unit ={
    val s = "Programming Scala"
    println(s.reverse)
    println(s.capitalize)
    s.foreach(c => print(s"$c-"))
    println()
  }

  /**
    * 当编译器看到像 x"foo bar" 这样的表达式时，它会查找 scala.StringContext 中定义的 x 方法。
    * 传递给 StringContext.apply 方法的参数其实是 ${...} 表达式中抽取出的各个部分。传递给 s 的参数则是抽取后的表达式。
    */
  def example4: Unit ={
    val name = ("Buck","Trends")
    println(s"Hello, ${name._1} ${name._2}")
    // 上述代码可以被转换成：
    println(StringContext("Hello, "," ","").s(name._1,name._2))
  }

  /**
    * 编写一个能够将简单的 JSON  字符串转化为JSONObject 对象的插入器。
    */
  def example5: Unit ={
    object Interpolators{
      implicit class jsonForStringContext(val sc:StringContext){  // 限定隐式类的作用域，在对象内定义隐式类
        def json(values: Any*): JSONObject = {
          val keyRE = """^[\s{,]*(\S+):\s*""".r   // 从字符串片段中抽取 key  名称的正则表达式
          val keys = sc.parts.map{
            case keyRE(key) => key
            case str => str
          }
          val kvs = keys zip values
          JSONObject(kvs.toMap)
        }
      }
    }
    import Interpolators._
    val name = "Dean Wampler"
    val book = "Programming Scala, Second Edition"
    val jsonobj = json"{name:$name,book:$book}"
    println(jsonobj)
  }

  /**
    * 就像拉链一样，集合中的 zip 方法能很方便地将两个集合中的值缝合在一起。
    */
  def example6: Unit ={
    val keys = List(1,2,3,4)
    val values = List("a","b","c")
    val keysValues = keys zip values
    println(keysValues)   // List((1,a), (2,b), (3,c))

    val keys2 = List(1,2)
    val values2 = List("a","b","c")
    val keysValues2 = keys2 zip values2
    println(keysValues2)   // List((1,a), (2,b))
  }
}
