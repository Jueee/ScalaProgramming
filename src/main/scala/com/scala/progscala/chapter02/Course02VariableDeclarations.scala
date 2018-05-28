package com.scala.progscala.chapter02

/**
  * 变量声明
  *
  * 在声明变量时， Scala  允许你决定该变量是不可变（只读）的，还是可变的（读写）。
  */
object Course02VariableDeclarations {

  def main(args: Array[String]): Unit = {

    // 不可变的 “ 变量 ” 用 val 关键字声明
    val array:Array[String] = new Array[String](5)

    // Scala  的大部分变量事实上是指向堆内存对象的引用，这一点与 Java  一致。
    // 所以，以上代码中的 array 也是一个引用，它不能指向其他 Array，但所指向的 Array 中的元素是可变的
//    array = new Array[String](2)  // 编译报错

    array(0) = "Hello"
    println(array.mkString(","))  // 打印数组

    // 一个 val 变量在声明时必须被初始化
//    var a1;   // 编译报错
    val a2 = 10;


    // 一个可变变量用关键字 var 来声明
    var stockPrice:Double = 100.0
    println(stockPrice)

    // 声明后可以再次对其赋值，也必须在声明的同时立即初始化
    // 【注】我们修改了stockPrice本身，然而stockPrice所引用的“对象”没有被修改，因为在Scala中Double类型不可变的。
    stockPrice = 200.0
    println(stockPrice)



    // 定义Person 类，其中包含表示姓和名的不可变变量，而年龄则是可变的
    class Person(val name:String,var age:Int)

    val p = new Person("Dean Wampler",29)
    println(p.name+"-"+p.age)
//    p.name = "Buck Trends"  // 编译报错
    p.age = 30
    println(p.name+"-"+p.age)

    // 【注】var 和 val 关键字只标识引用本身是否可以指向另一个不同的对象，它们并未表明其所引用的对象是否可变。

  }
}
