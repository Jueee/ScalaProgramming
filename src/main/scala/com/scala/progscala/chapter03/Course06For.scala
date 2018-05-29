package com.scala.progscala.chapter03

/**
  * Scala 中的 for 推导式
  *
  * Scala  并未提供 break 和 continue 语句，不过编写地道的 Scala  代码时，你几乎不需要使用这些语句。
  * 你可以使用条件表达式或者使用递归判断循环是否应该继续。
  */
object Course06For {
  def main(args: Array[String]): Unit = {
    content1 // 6.1 　 for  循环
    content2 // 6.2 　生成器表达式
    content3 // 6.3 　保护式：筛选元素
    content4 // 6.4 　 Yielding
    content5 // 6.5 　扩展作用域与值定义
  }

  /**
    * 6.1 　 for  循环
    * 这种形式不返回任何值，因此它只会执行一些会带来副作用的操作。
    */
  def content1: Unit = {
    println("-----6.1 　 for  循环-----")

    val dogBreeds = List("Doberman", "Yorkshire Terrier", "Dachshund",
      "Scottish Terrier", "Great Dane", "Portuguese Water Dog")
    for (breed <- dogBreeds)
      println(breed)
  }

  /**
    * 6.2 　生成器表达式
    * 像 breed <- dogBreeds 这样的表达式也被称为 生成器表达式
    * 该表达式会基于集合生成单独的数值。左箭头操作符 ( <- )  用于对像列表这样的集合进行遍历。
    */
  def content2: Unit = {
    println("-----6.2 　生成器表达式-----")
    for (i <- 1 to 10) println(i)
  }

  /**
    * 6.3 　保护式：筛选元素
    * 可以加入 if 表达式，来筛选出我们希望保留的元素。这些表达式也被称为 保护式 （ guard ）
    */
  def content3: Unit = {
    println("-----6.3 　保护式：筛选元素-----")
    val dogBreeds = List("Doberman", "Yorkshire Terrier", "Dachshund",
      "Scottish Terrier", "Great Dane", "Portuguese Water Dog")
    for (breed <- dogBreeds
         if breed.contains("Terrier")
    ) println(breed)
    // 可以在 for 循环中添加多个保护式：
    for (breed <- dogBreeds
         if breed.contains("Terrier")
         if !breed.startsWith("Yorkshire")
    ) println(breed)
    // 两个 if 语句被合并为一个语句
    for (breed <- dogBreeds
         if breed.contains("Terrier") && !breed.startsWith("Yorkshire")
    ) println(breed)
  }

  /**
    * 6.4 　 Yielding
    * 使用 yield 关键字便能在 for 表达式中生成新的集合。
    *
    * for 推导式有一个不成文的约定：当 for 推导式仅包含单一表达式时使用原括号，当其包含多个表达式时使用大括号。
    * 值得注意的是，使用原括号时，早前版本的 Scala  要求表达式之间必须使用分号。
    */
  def content4: Unit = {
    println("-----6.4 　 Yielding-----")
    val dogBreeds = List("Doberman", "Yorkshire Terrier", "Dachshund",
      "Scottish Terrier", "Great Dane", "Portuguese Water Dog")
    val filteredBreeds = for (breed <- dogBreeds
         if breed.contains("Terrier") && !breed.startsWith("Yorkshire")
    ) yield breed
    println(filteredBreeds)
  }

  /**
    * 6.5 　扩展作用域与值定义
    * 能够在 for 表达式中的最初部分定义值，并可以在后面的表达式中使用该值。
    *
    * 什么时候使用左箭头（ <- ），什么时候该使用等于号（ = ）呢？
    * 当你遍历某一集合或其他像 Option 这样的容器并试图提取值时，你应该使用箭头。
    * 当你执行并不需要迭代的赋值操作 时，你应使用等于号。
    * for 推导式的第一句表达式必须使用箭头符执行抽取 /  迭代操作。
    */
  def content5: Unit = {
    println("-----6.5 　扩展作用域与值定义-----")
    val dogBreeds = List("Doberman", "Yorkshire Terrier", "Dachshund",
      "Scottish Terrier", "Great Dane", "Portuguese Water Dog")
    for{
      breed <- dogBreeds
      upcasedBreed = breed.toUpperCase
    }println(upcasedBreed)

    val dogBreeds2 = List(Some("Dobreman"), None, Some("Yorkshire Terrier"),
      Some("Dachshund"), None, Some("Scottish Terrier"),
      None, Some("Great Dane"), Some("Portuguese Water Dog"))
    println("first pass:")
    // 每一个被提取的元素均为 Option 对象。而后续的代码行中将使用箭头符提取 option  中的值
    for {
      breedOption <- dogBreeds2
      breed <- breedOption
      upcasedBreed = breed.toUpperCase
    } println(upcasedBreed)

    println("second pass:")
    // 模式匹配，这使得代码更为清新
    for {
      Some(breed) <- dogBreeds2
      upcasedBreed = breed.toUpperCase
    } println(upcasedBreed)

  }
}
