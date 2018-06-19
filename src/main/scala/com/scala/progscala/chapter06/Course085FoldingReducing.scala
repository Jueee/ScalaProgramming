package com.scala.progscala.chapter06

/**
  * 折叠与归约
  *
  * 折叠和归约 都是将一个集合 “ 缩小 ” 成一个更小的集合或一个值的操作。
  *
  * 折叠从一个初始的 “ 种子 ” 值开始，然后以该值作为上下文，处理集合中的每个元素。
  * 不同的是，归约不需要调用者提供一个初始值。它将集合的其中一个元素当做初始值，通常这个值是集合的第一个元素或最后一个元素
  *
  *
  * def fold[A1 >: A](z: A1)(op: (A1, A1) & A1): A1
  * 遍历集合，使用指定的二元操作符 op 对集合元素做折叠。对元素执行操作时，其顺序是未指定的，因此结果是不能确定的。
  * 不过，对于多数顺序固定的集合如 List ， fold 的作用与 foldLeft 相同。
  *
  * def foldLeft[B](z: B)(op: (B, A) & B): B
  * 对初始值和集合中的所有元素做操作，顺序从左到右。
  *
  * def foldRight[B](z: B)(op: (A, B) & B): B
  * 对初始值和集合中的所有元素做操作，顺序从右到左。
  *
  * def /:[B](z: B)(op: (B, A) & B): B = foldLeft(z)(op)
  * foldLeft 的别名。调用形式例如： (0 /: List(1,2,3))(_ + _) 。不过，大部分人认为用操作符 /: 来表示 foldLeft 太隐晦不
  * 易记忆，所以，写代码时不要忘记与代码阅读者进行交流。
  *
  * def :\[B](z: B)(op: (A, B) & B): B = foldRight(z)(op)
  * foldRight 的别名。调用形式例如： (0 /: List(1,2,3))(_ + _) 。不过，大部分人认为用操作符 /: 来表示 foldLeft 太隐晦不
  * 易记忆。
  *
  * def reduce[A1 >: A](op: (A1, A1) & A1): A1
  * 遍历集合，使用指定的二元操作符 op 对集合元素做归约。对元素执行操作时，其顺序是未指定的，因此结果是不能确定的。不过，对于多
  * 数顺序固定的集合如 List ， reduce 的作用与 reduceLeft 相同。如果集合为空，则抛出异常。
  *
  * def reduceLeft[A1 >: A](op: (A1, A1) & A1): A1
  * 对初始值和集合中的所有元素做操作，顺序从左到右。如果集合为空，则抛出异常。
  *
  * def reduceRight[A1 >: A](op: (A1, A1) & A1): A1
  * 对初始值和集合中的所有元素做操作，顺序从右到左。如果集合为空，则抛出异常。
  *
  * def optionReduce[A1 >: A](op: (A1, A1) & A1): Option[A1]
  * 类似 reduce ，但当集合为空时，返回 None ；集合不空时，返回 Some(…) 。
  *
  * def reduceLeftOption[B >: A](op: (B, A) & B): Option[B]
  * 类似 reduceLeft ，但当集合为空时，返回 None ；集合不空时，返回 Some(…) 。
  *
  * def reduceRightOption[B >: A](op: (B, A) & B): Option[B]
  * 类似 reduceRight ，但当集合为空时，返回 None ；集合不空时，返回 Some(…) 。
  *
  * def aggregate[B](z: B)(seqop: (B, A) & B, combop: (B, B) & B): B
  * 对后面的元素进行操作，并聚合结果。该函数比 fold 和 reduce 形式更加通用，具有相似的语法，但并不要求结果的类型是元素的公共
  * 父类型。函数将集合分为不同的分片，顺序遍历各个分片，用 seqop 更新计算结果，最后对各个分片的计算结果调用 combop 。该操作可
  * 能操作任意个分片，因此 combop 可能被调用任意次。
  *
  * def scan[B >: A](z: B)(op: (B, B) & B): TraversableOnce[B]
  * 扫描、计算集合元素的一个前缀。中间的元素 z 可能会被多次操作。（在本节末尾，会给出一个示例。）
  *
  * def scanLeft[B >: A](z: B)(op: (B, B) & B): TraversableOnce[B]
  * 从左到右遍历集合，对元素执行 op 操作，得到一个包含一系列累计值的集合。
  *
  * def scanRight[B >: A](z: B)(op: (B, B) & B): TraversableOnce[B]
  * 从右到左遍历集合，对元素执行 op 操作，得到一个包含一系列累计值的集合。
  *
  * def product: A
  * 计算集合元素的累乘值。只要集合元素可以隐式转换为 Numeric[A] （ http://www.scala-lang.org/api/current/scala/math/Numeric.html  ）（例
  * 如： Int 、 Long 、 Float 、 Double 和 BigInt ），就可以返回元素的累乘值。实际上，函数的完整签名为 def product[B >: A]
  * (implicit num: Numeric[B]): B ，参见 5.2.3  节，了解关于使用隐式转换来约束方法使用范围的细节。
  *
  * def mkString: String
  * 将集合中的所有元素序列化到字符串中。该方法是 fold 的一个自定义实现，用于方便地从集合生成字符串。在集合的元素之间没有分隔
  * 符。
  *
  * def mkString(sep: String): String
  * 从集合生成字符串，分隔符为字符串参数 sep 。
  *
  * def mkString(start: String, sep: String, end: String): String
  * 从集合生成字符串，分隔符为字符串参数 sep ，前缀为 start ，后缀为 end 。
  */
object Course085FoldingReducing {

  def main(args: Array[String]): Unit = {
    content1
    content2
    content3
    content4
    content5
  }

  def content1: Unit ={
    val list = List(1,2,3,4,5,6)
    println(list)

    println(list reduce (_ + _))  // 21

    // 用 10  作为初始值，对同一个列表的元素做累乘
    // 传给 fold 的函数需要两个参数，包括累计值和初始值。
    println(list.fold(10)(_ * _)) // 7200

    println((list fold 10)(_ * _))// 7200

    /**
      * 上述代码等效于：
      * 用偏应用的方法创建了 fold1 ，随后我们给出了剩下的参数列表 (_ * _) 以调用 fold1 。
      */
    val fold1 = (list fold 10)_
    println(fold1)
    println(fold1(_ * _))
  }

  /**
    * 如果我们对任何一个空的集合执行 fold 操作，它会返回初始种子的值。
    * 与此不同， reduce 无法对空集合进行操作，因为 reduce 没有值可以返回。如果那样，就会抛出异常
    */
  def content2: Unit ={
    println((List.empty[Int] fold 10)(_+_))   // 10
    //    println((List.empty[Int] ) reduce (_+_))  // Exception in thread "main" java.lang.UnsupportedOperationException: empty.reduceLeft

  }

  /**
    * 如果你不确定集合是否为空（例如：当集合是传入到你函数中的一个参数时），你可以用 optionReduce 代替 reduce ：
    */
  def content3: Unit ={
    println(List.empty[Int]  reduceOption (_+_))  // None
    println(List(1,2,3,4,5)  reduceOption (_+_))  // Some(15)

  }

  /**
    * 以下是一个折叠操作，事实上相当于映射操作
    * 使用 fold 的一个变体 foldRight 从右到左遍历集合，这样可以保证我们构造的新序列中元素的顺序是正确的。
    * 元素 6 首先被处理，并插入到空序列头部；随后元素 5  被处理，插入到该序列的头部，以次类推。
    */
  def content4: Unit ={
    val test = (List(1,2,3,4,5,6) foldRight List.empty[String]){
      (x,list) => ("[" + x + "]") :: list
    }
    println(test)   // List([1], [2], [3], [4], [5], [6])
  }

  /**
    * 输出初始值 10 ，接下来输出第一个元素与初始值的和 11 ，然后是第二个元素与前值的和， 11 + 2 = 13 ，以此类推。
    */
  def content5: Unit ={
    val list = List(1, 2, 3, 4, 5)
    println((list scan 10)(_+_))  // List(10, 11, 13, 16, 20, 25)
  }
}
