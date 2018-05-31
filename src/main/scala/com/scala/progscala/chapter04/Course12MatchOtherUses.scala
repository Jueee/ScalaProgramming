package com.scala.progscala.chapter04

/**
  * 模式匹配的其他用法
  *
  *
  */
object Course12MatchOtherUses {

  def main(args: Array[String]): Unit = {
    example1
    example2
    example3
    example4
    example5
    example6
    example7
  }

  case class Address(street: String, city: String, country: String)
  case class Person(name: String, age: Int, address: Address)
  case class Person2(name: String, age: Int)

  /**
    * 在定义变量时也可以运用模式匹配
    * 只用了一个步骤，我们就将 Person 中所有需要的属性抽取了出来，同时略过了不需要的属性。
    */
  def example1: Unit ={
    val Person(name, age, Address(_, state, _)) = Person("Dean", 29, Address("1 Scala Way", "CA", "USA"))
    println(s"$name,$age,$state")
  }

  /**
    * 在定义变量时也可以运用模式匹配，定义List
    */
  def example2: Unit ={
    val head +: tail = List(1,2,3)
    println(head)
    println(tail)

    val head1 +: head2 +: tail_2 = Vector(1,2,3)
    println(head1)  // 1
    println(head2)  // 2
    println(tail_2) // Vector(3)

    val Seq(a,b,c) = List(1,2,3)
    println(s"$a,$b,$c")
  }

  /**
    * 在 if 表达式中我们也可以用模式匹配
    * 【注】在这里无法使用 _ 占位符
    */
  def example3: Unit ={
    val p = Person("Dean", 29, Address("1 Scala Way", "CA", "USA"))
    if (p == Person("Dean", 29, Address("1 Scala Way", "CA", "USA"))) println("yes")
    else println("no")

    if (p == Person("Dean", 29, Address("1 Scala Way", "CA", "USSA"))) println("yes")
    else println("no")
  }

  /**
    * 假设我们有一个函数，参数为整数序列，将所有整数的和与整数的个数放在元组中返回
    */
  def example4: Unit ={
    def sum_count(ints:Seq[Int]) = (ints.sum, ints.size)

    val (sum,count) = sum_count(List(1,2,3,4,5))
    println(sum)  // 15
    println(count)// 5
  }

  /**
    * 在 for 表达式中使用模式匹配
    */
  def example5: Unit ={
    val dogBreeds = Seq(Some("Doberman"), None, Some("Yorkshire Terrier"),
      Some("Dachshund"), None, Some("Scottish Terrier"),
      None, Some("Great Dane"), Some("Portuguese Water Dog"))
    println("pass:")
    for {
      Some(breed) <- dogBreeds
      uncasedBreed = breed.toUpperCase
    } println(uncasedBreed)
  }

  /**
    * 模式匹配与 case  语句的另一个便利用法是，它们可以使带复杂参数的函数字面量更易于使用
    */
  def example6: Unit ={
    val as = Seq(
      Address("1 Scala Lane", "Anytown", "USA"),
      Address("2 Clojure Lane", "Othertown", "USA"))
    val ps = Seq(
      Person2("Buck Trends", 29),
      Person2("Clo Jure", 28))

    // 压缩序列的类型为 Seq[(Person,Address)]
    val pas = ps zip as

    //  不太美观的方法：
    val result1 = pas map{ tup =>
      val Person2(name,age) = tup._1
      val Address(street,city,country) = tup._2
      s"$name (age:$age) lived at $street, $city, in $country"
    }
    println(result1)

    // 不错的方法：
    // 【注】我们传递给 map 的参数必须是一个类型为 (Person,Address) => String 的函数。
    val result2 = pas map {
      case (Person2(name,age),Address(street,city,country)) =>
        s"$name (age:$age) lived at $street, $city, in $country"
    }
    println(result2)
  }

  /**
    * 可以在正则表达式中用模式匹配去解构字符串。
    * 用于 SQL  解析的简单程序
    * 【注】由于用了变量插值，因此在正则表达式字符串中，必须增加反斜杠进行转义，如用 \\s 代替 \s 。
    */
  def example7: Unit ={
    val cols = """\*|[\w, ]+""" //  用于提取列
    val table = """\w+""" //  用于提取表
    val tail = """.*""" //  用于其他语句
    val selectRE = s"""SELECT\\s*(DISTINCT)?\\s+($cols)\\s*FROM\\s+($table)\\s*($tail)?;""".r

    val selectRE(distinct1, cols1, table1, otherClauses1) = "SELECT DISTINCT * FROM atable;"
    println(distinct1, cols1, table1, otherClauses1)  // (DISTINCT,*,atable,)

    val selectRE(distinct2, cols2, table2, otherClauses2) = "SELECT col1, col2 FROM atable;"
    println(distinct2, cols2, table2, otherClauses2)  // (null,col1, col2 ,atable,)

    val selectRE(distinct3, cols3, table3, otherClauses3) = "SELECT DISTINCT col1, col2 FROM atable;"
    println(distinct3, cols3, table3, otherClauses3)  // (DISTINCT,col1, col2 ,atable,)

    val selectRE(distinct4, cols4, table4, otherClauses4) = "SELECT DISTINCT col1, col2 FROM atable WHERE col1 = 'foo';"
    println(distinct4, cols4, table4, otherClauses4)  // (DISTINCT,col1, col2 ,atable,WHERE col1 = 'foo')
  }
}
