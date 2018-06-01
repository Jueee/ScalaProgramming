package com.scala.progscala.chapter05

import scala.util.Random

/**
  * Scala  内置的各种隐式
  *
  * Scala 2.11  版库源代码中定义了超过 300  个隐式方法、隐式值和隐式类型。
  */
object Course07BuiltInImplicits {

  def main(args: Array[String]): Unit = {
    content1
    content2
    content3
    content4
    content5
    content6
    content7
    content8
  }

  /**
    * AnyVal 类型的所有伴生对象均提供了丰富的转换方法，例如：将 Int 值转换为 Long 值。
    * 大多数时候你只需要调用该类型的 toX 方法
    */
  def content1: Unit ={
    object Int {
      implicit def int2long(x: Int): Long = x.toLong
    }

    // Byte 伴生对象：
    implicit def byte2short(x: Byte): Short = x.toShort
    implicit def byte2int(x: Byte): Int = x.toInt
    implicit def byte2long(x: Byte): Long = x.toLong
    implicit def byte2float(x: Byte): Float = x.toFloat
    implicit def byte2double(x: Byte): Double = x.toDouble

    // Char 伴生对象：
    implicit def char2int(x: Char): Int = x.toInt
    implicit def char2long(x: Char): Long = x.toLong
    implicit def char2float(x: Char): Float = x.toFloat
    implicit def char2double(x: Char): Double = x.toDouble

    // Short 伴生对象：
    implicit def short2int(x: Short): Int = x.toInt
    implicit def short2long(x: Short): Long = x.toLong
    implicit def short2float(x: Short): Float = x.toFloat
    implicit def short2double(x: Short): Double = x.toDouble

    // Int 伴生对象：
    implicit def int2long(x: Int): Long = x.toLong
    implicit def int2float(x: Int): Float = x.toFloat
    implicit def int2double(x: Int): Double = x.toDouble

    // Long 伴生对象：
    implicit def long2float(x: Long): Float = x.toFloat
    implicit def long2double(x: Long): Double = x.toDouble

    // Float 伴生对象：
    implicit def float2double(x: Float): Double = x.toDouble

  }

  /**
    * BigInt 和 BigDecimal 类定义在 scala.math 包中，它们可以转换来自 AnyVal 类型和 Java  中对应的实现类型。
    */
  def content2: Unit ={
    """
    // BigDecimal 伴生对象：
    implicit def int2bigDecimal(i: Int): BigDecimal = apply(i)
    implicit def long2bigDecimal(l: Long): BigDecimal = apply(l)
    implicit def double2bigDecimal(d: Double): BigDecimal = ...
    implicit def javaBigDecimal2bigDecimal(x: BigDec): BigDecimal = apply(x)

    // BigInt 伴生对象：
    implicit def int2bigInt(i: Int): BigInt = apply(i)
    implicit def long2bigInt(l: Long): BigInt = apply(l)
    implicit def javaBigInteger2bigInt(x: BigInteger): BigInt = apply(x)
    """
  }

  /**
    * Option 对象可以转换成包含 0  个或 1  个元素的列表
    */
  def content3: Unit ={
    implicit def option2Iterable[A](xo: Option[A]): Iterable[A] = xo.toList
  }

  /**
    * Predef 中定义了大多数的隐式定义。其中的一些隐式定义包含了 @inline 标注
    * 这一标注鼓励编译器努力尝试将函数调用 内联 （ inline ），以减少栈帧开销。
    *
    * 与之对应的是 @noinline 标注，该标注阻止编译器将方法调用内联化，即便是条件允许的情况。
    *
    * 一些方法能将某一类型转化为另一类型，例如：将某一类型封装成新的类型后，新的类型会提供新的方法：
    */
  def content4: Unit ={
    """
    @inline implicit def augmentString(x: String): StringOps = new StringOps(x)
    @inline implicit def unaugmentString(x: StringOps): String = x.repr
    implicit def tuple2ToZippedOps[T1, T2](x: (T1, T2))
    = new runtime.Tuple2Zipped.Ops(x)
    implicit def tuple3ToZippedOps[T1, T2, T3](x: (T1, T2, T3))
    = new runtime.Tuple3Zipped.Ops(x)
    implicit def genericArrayOps[T](xs: Array[T]): ArrayOps[T] = ...
    implicit def booleanArrayOps(xs: Array[Boolean]): ArrayOps[Boolean] =
    = new ArrayOps.ofBoolean(xs)
    ... //  与其他 AnyVal 类型相似的方法
    implicit def refArrayOps[T <: AnyRef](xs: Array[T]): ArrayOps[T]
    = new ArrayOps.ofRef[T](xs)
    @inline implicit def byteWrapper(x: Byte) = new runtime.RichByte(x)
    ... //  与其他 AnyVal 类型相似的方法
    implicit def genericWrapArray[T](xs: Array[T]): WrappedArray[T] = ...
    implicit def wrapRefArray[T <: AnyRef](xs: Array[T]): WrappedArray[T] =...
    implicit def wrapIntArray(xs: Array[Int]): WrappedArray[Int] = ...
    ... //  与其他 AnyVal 类型相似的方法
    implicit def wrapString(s: String): WrappedString = ...
    implicit def unwrapString(ws: WrappedString): String = ...
    """
  }

  /**
    *  runtime.Tuple2Zipped.Ops 方法:
    * 大多数集合都提供了 zip 方法，该方法可以将两个集合连接起来。
    * 两个集合的元素组成一个新的元素的过程就好像合上拉链一样。
    */
  def content5: Unit ={
    val zipped = List(1,2,3) zip List(4,5,6)
    println(zipped)   // List((1,4), (2,5), (3,6))

    // 我们可以对合并后的集合执行成对的操作
    val products = zipped map{case(x,y) => x*y}
    println(products) // List(4, 10, 18)
  }

  /**
    * Tuple2Zipper.Ops 和 Tuple3Zipper.Ops 提供了 invert 方法
    * 该方法会将包含两个元素或三个元素的集合转换成包含二元元组或三元元组的集合。
    * 换言之，它们会压缩原本就定义在元组中的容器。
    */
  def content6: Unit ={
    val pair = (List(1,2,3),List(4,5,6))
    println(pair)   // (List(1, 2, 3),List(4, 5, 6))

    val unpair = pair.invert
    println(unpair) // List((1,4), (2,5), (3,6))

    val pair2 = (List(1,2,3),List("one","two","three"))
    println(pair2)  // (List(1, 2, 3),List(one, two, three))

    val unpair2 = pair2.invert
    println(unpair2)// List((1,one), (2,two), (3,three))
  }

  /**
    * 将 java.util.Random 对象转换成 scala.util.Random 对象
    */
  def content7: Unit ={
    implicit def javaRandomToRandom(r: java.util.Random): Random = new Random(r)
  }

  /**
    * DecorateAsJava中定义了一些 Scala  容器，这些容器类是 Java  容器的 装饰类 （ decoration ）。
    *
    * 下面代码中出现的 ju 和 jl 则分别对应了DecorateAsJava 实际源码中的 java.lang 及 java.util 。
    * 各种名为 AsJava* 的类型则是提供了这些转换操作的辅助类型：
    */
  def content8: Unit ={
    """
    implicit def asJavaIteratorConverter[A](i : Iterator[A]):
    AsJava[ju.Iterator[A]] = ...
    implicit def asJavaEnumerationConverter[A](i : Iterator[A]):
    AsJavaEnumeration[A] = ...
    implicit def asJavaIterableConverter[A](i : Iterable[A]):
    AsJava[jl.Iterable[A]] = ...
    implicit def asJavaCollectionConverter[A](i : Iterable[A]):
    AsJavaCollection[A] = ...
    implicit def bufferAsJavaListConverter[A](b : mutable.Buffer[A]):
    AsJava[ju.List[A]] = ...
    implicit def mutableSeqAsJavaListConverter[A](b : mutable.Seq[A]):
    AsJava[ju.List[A]] = ...
    implicit def seqAsJavaListConverter[A](b : Seq[A]):
    AsJava[ju.List[A]] = ...
    implicit def mutableSetAsJavaSetConverter[A](s : mutable.Set[A]):
    AsJava[ju.Set[A]] = ...
    implicit def setAsJavaSetConverter[A](s : Set[A]):
    AsJava[ju.Set[A]] = ...
    implicit def mutableMapAsJavaMapConverter[A, B](m : mutable.Map[A, B]):
    AsJava[ju.Map[A, B]] = ...
    implicit def asJavaDictionaryConverter[A, B](m : mutable.Map[A, B]):
    AsJavaDictionary[A, B] = ...
    implicit def mapAsJavaMapConverter[A, B](m : Map[A, B]):
    AsJava[ju.Map[A, B]] = ...
    implicit def mapAsJavaConcurrentMapConverter[A, B](m: concurrent.Map[A, B]):
    AsJava[juc.ConcurrentMap[A, B]] = ...

    // 可以使用 DecorateAsScala 中定义的隐式方法，将 Java  容器装饰成 Scala  容器：
    implicit def asScalaIteratorConverter[A](i : ju.Iterator[A]):
    AsScala[Iterator[A]] = ...
    implicit def enumerationAsScalaIteratorConverter[A](i : ju.Enumeration[A]):
    AsScala[Iterator[A]] = ...
    implicit def iterableAsScalaIterableConverter[A](i : jl.Iterable[A]):
    AsScala[Iterable[A]] = ...
    implicit def collectionAsScalaIterableConverter[A](i : ju.Collection[A]):
    AsScala[Iterable[A]] = ...
    implicit def asScalaBufferConverter[A](l : ju.List[A]):
    AsScala[mutable.Buffer[A]] = ...
    implicit def asScalaSetConverter[A](s : ju.Set[A]):
    AsScala[mutable.Set[A]] = ...
    implicit def mapAsScalaMapConverter[A, B](m : ju.Map[A, B]):
    AsScala[mutable.Map[A, B]] = ...
    implicit def mapAsScalaConcurrentMapConverter[A, B](m: juc.ConcurrentMap[A, B]):
    AsScala[concurrent.Map[A, B]] = ...
    implicit def dictionaryAsScalaMapConverter[A, B](p: ju.Dictionary[A, B]):
    AsScala[mutable.Map[A, B]] = ...
    implicit def propertiesAsScalaMapConverter(p: ju.Properties):
    AsScala[mutable.Map[String, String]] = ...
    """
  }
}
