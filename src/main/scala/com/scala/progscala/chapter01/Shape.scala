package com.scala.progscala.chapter01

// 表示二维点的类
case class Point(x: Double = 0.0, y: Double = 0.0)

// 表示几何形状的抽象类
abstract class Shape() {
  /**
    * 实现了一个 “ 绘制 ” 形状的 draw 方法，该方法中仅输出了一个格式化的字符串
    * 每个图形对象都会将自己的字符格式传给函数 f ， 由函数 f 执行绘制工作。
    *
    * @param f
    */
  def draw(f: String => Unit): Unit = f(s"draw: ${this.toString}")
}

// 圆：圆心和半径组成
case class Circle(center: Point, radius: Double) extends Shape

// 矩形：位于左下角的点、高度和宽度这三个属性构成。
case class Rectangle(lowerLeft: Point, height: Double, width: Double) extends Shape

// 三角形：三个点所构成。
case class Triangle(point1: Point, point2: Point, point3: Point) extends Shape


object ShapeTest {
  def main(args: Array[String]): Unit = {
    val p00 = new Point()
    println(p00)

    val p20 = new Point(2.0)
    println(p20)

    val p02 = new Point(y = 2.0)
    println(p02)

    println(p00 == p20)


    // 伴生对象：下面两行代码是等价的
    val p1 = Point.apply(1.0, 2.0)
    val p2 = Point(1.0, 2.0)
    println(p1 == p2)

  }
}