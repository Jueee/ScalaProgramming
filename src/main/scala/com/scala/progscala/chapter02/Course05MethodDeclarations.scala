package com.scala.progscala.chapter02

import scala.annotation.tailrec
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * 方法声明
  *
  * 本节我们会用到前文使用的 com.scala.progscala.chapter01.Shape 类的继承树并加以修改
  */
object Course05MethodDeclarations {

  def main(args: Array[String]): Unit = {
    content1 // 5.1 　方法默认值和命名参数列表
    content2 // 5.2 　方法具有多个参数列表
    content3 // 5.3 　 Future  简介
    content4 // 5.4 　嵌套方法的定义与递归
  }

  // 定义 Point 类，并提供默认的初始化值
  case class Point(x: Double = 0.0, y: Double = 0.0) {
    // 新的 shift 方法，用于从现有的 Point 对象中对 “ 点 ” 进行平移，从而创建一个新的Point 对象。
    // 它使用了 copy 方法， copy 方法也是 case  类自动创建的。
    // copy 方法允许你在创建 case  类的新实例时，只给出与原对象不同部分的参数
    def shift(deltax: Double = 0.0, deltay: Double = 0.0) = copy(x + deltax, y + deltay)
  }

  // 命名参数列表让客户端代码更具可读性。
  def content1: Unit = {
    println("-----5.1 　方法默认值和命名参数列表-----")

    val p1 = new Point(x = 3.3, y = 4.4)
    println(p1)
    println(p1.shift(2.0, 3.5))

    val p2 = p1.copy(y = 6.6)
    println(p2)
    println(p2.shift(1.2, 3.2))
  }

  abstract class Shape() {
    /**
      * draw  带两个参数列表
      * 每个参数列表都有一个参数，而不是拥有一个具有两个参数的参数列表。
      *
      * @param offset 表示绘制偏移量的参数
      * @param f      之前用过的函数参数
      */
    def draw(offset: Point = Point(0.0, 0.0))(f: String => Unit): Unit = {
      f(s"draw(offset = $offset), ${this.toString}")
    }
  }

  case class Circle(center: Point, radius: Double) extends Shape

  case class Rectangle(lowerLeft: Point, height: Double, width: Double) extends Shape

  // 只使用一个带两个参数值的参数列表
  abstract class Shape2() {
    def draw(offset: Point = Point(0.0, 0.0), f: String => Unit): Unit = {
      f(s"draw(offset = $offset), ${this.toString}")
    }
  }

  def content2: Unit = {
    println("-----5.2 　方法具有多个参数列表-----")
    val s = new Shape {}
    // 调用新的 draw 方法的表达方式
    s.draw(Point(1.0, 2.0))(str => println(s"ShapesDrawingActor:$str"))

    // Scala  允许我们把参数列表两边的圆括号替换为花括号
    s.draw(Point(1.0, 2.0)) { str => println(s"ShapesDrawingActor:$str") }

    // 如果函数字面量不能在一行内完成，我们可以重写为以下方式：
    s.draw(Point(1.0, 2.0)) { str =>
      println(s"ShapesDrawingActor:$str")
    }

    // 如果我们使用缺省的偏移量，第一个圆括号就不能省略：
    s.draw() { str => println(s"ShapesDrawingActor:$str") }

    val s2 = new Shape2 {}
    // 只使用一个带两个参数值的参数列表
    s2.draw(Point(1.0, 2.0), str => println(s"ShapesDrawingActor:$str"))

    // 使用默认值开启 offset 也没那么便捷，因此我们不得不对参数进行命名：
    s2.draw(f = str => println(s"ShapesDrawingActor:$str"))


    // 使用多个参数列表优势：在之后的参数列表中进行类型推断
    // 函数 m1 和函数 m2 看起来几乎一模一样，但我们需要注意用相同的参数调用它们时 m1 和 m2 的表现。
    def m1[A](a: A, f: A => String) = f(a)

    def m2[A](a: A)(f: A => String) = f(a)

    // Scala  无法推断m1 函数的参数 i ， m2 则可以。
    //    m1(100,i=> s"$i + $i")  // 编译报错
    m2(100)(i => s"$i + $i")

  }

  /**
    * 5.3 　 Future  简介
    * scala.concurrent.Future是Scala提供的一个并发工具，其中的API使用隐含参数来减少冗余代码。
    * 当你将任务封装在 Future 中执行时，该任务的执行是异步的。
    * Future API  提供了多种机制去获取执行结果，如提供回调函数。当结果就绪时，回调函数将被调用。
    */
  def content3: Unit = {
    println("-----5.3 　 Future  简介-----")

    def sleep(millis: Long): Unit = {
      Thread.sleep(millis)
    }
    //  繁忙的处理工作
    def doWork(index: Int) = {
      sleep((math.random * 1000).toLong)
      index
    }
    // 并发发出5个任务，并在任务结束时处理任务返回的结果：
    (1 to 5) foreach { index =>
      val future = Future {   // Future.apply 返回一个新的 Future 对象
        doWork(index)
      }
      future onSuccess {    // 当 future 成功执行完毕后，该回调会被执行
        case answer: Int => println(s"Success! returned:$answer")
      }
      future onFailure {    // 用 onFailure 注册了一个回调函数来处理错误
        case th: Throwable => println(s"FAILURE! returned:$th")
      }
    }
    sleep(1000) //  等待足够长的时间，以确保工作线程结束。
    println("Finish!")

    /**
      * 报错：Cannot find an implicit ExecutionContext. You might pass an (implicit ec: ExecutionContext) parameter to your method or import scala.concurrent.ExecutionContext.Implicits.global
      * 解决：引入 import scala.concurrent.ExecutionContext.Implicits.global
      * 原因：查看Fufure.scala的源码，注意apply方法
      * def apply[T](body: =>T)(implicit execctx: ExecutionContext): Future[T] = impl.Future(body)
      * 导入的 scala.concurrent.ExecutionContext.Implicits.global 是在 Future 中常用的默认 ExecutionContext 。
      * 它使用 implicit 关键字声明，因此如果调用时未显式给出 ExecutionContext 参数，编译器就会使用这个默认值，本例就是这种情况。
      * 只有由 implicit 关键字声明的，在当前作用域可见的对象才能用作隐含值；只有被声明为 implicit  的函数参数才允许调用时不给出实参，而采用隐含的值。
      */
  }

  /**
    * 5.4 　嵌套方法的定义与递归
    * 方法的定义还可以嵌套。
    * 当你将一个很长的方法重构为几个更小的方法时，如果这些小的辅助方法只在该方法中调用，就可以用嵌套方法。
    * 我们将这些辅助函数嵌套定义在原方法中，它们便对其他外层的代码不可见，包括类中的其他方法。
    */
  def content4: Unit ={
    println("-----5.4 　嵌套方法的定义与递归-----")

    // 在这个方法中，我们调用了另一个嵌套的方法去完成阶乘的实际计算
    // 在 fact 方法中使用的 i 参数 “ 屏蔽 ” 了外部 factorial 方法的 i 参数。
    // 这样做是允许的，因为我们在 fact 方法中并不需要外部的 i ，我们只在 factorial 结尾调用fact 的时候才需要它。
    // 【注： Scala  采用的是局部作用域类型推断，无法推断出递归函数的返回类型。】
    // 【尾递归】表示调用递归函数是该函数中最后一个表达式，该表达式的返回值就是所调用的递归函数的返回值。
    def factorial(i:Int):Long={
      @tailrec    // 加tailrec关键字,编译器会告诉你代码是否正确地实现了尾递归，否则会抛出错误。
      def fact(i:Int,accumulator:Int):Long={  // 辅助函数递归地调用它本身，并传入一个 accumulator 参数，阶乘的计算结果保存在该参数中
        if (i<=1) accumulator
        else fact(i-1, i* accumulator)
      }
      fact(i,1)
    }
    // 测试阶乘的计算
    (0 to 5) foreach( i => println(factorial(i)))

    // 外层方法所在作用域中的一切在嵌套方法中都是可见的，包括传递给外层方法的参数。
    def countTo(n:Int): Unit ={
      def count(i:Int): Unit ={
        if(i<=n) {println(i);count(i+1)}
      }
      count(1)
    }
    // 测试
    countTo(10)
  }
}
