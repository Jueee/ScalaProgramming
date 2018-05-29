package com.scala.progscala.chapter03

import org.scalatest.{FunSpec, ShouldMatchers}

/**
  * 领域特定语言
  *
  * 领域特定语言，也称为 DSL ，指的是为某一专门问题域编写的语言，引入 DSL  是为了方便用简洁直观的方式表达该领域的概念。
  * 例如， SQL  便可以被视为一门 DSL ，因为它是一门专门用于解释关系模型的编程语言。
  *
  * 不过通常 DSL  只用于即席查询语言，它们要么被嵌入到某一宿主语言内，要么会专门有一个定制的解析器负责解析。
  * 嵌入意味着你需要在宿主语言中通过一种方言来实现 DSL 。
  * 嵌入式 DSL  通常也被称为内部 DSL ，而需要特制解析器的 DSL  则被称为外部 DSL 。
  */
class Course04DomainSpecific(actors: List[String]){
  def findNerds(): List[String] ={
    actors
  }
}

/**
  * 下面的示例应用 ScalaTest 类库（http://www.scalatest.org/），向我们展现了一种被称为 行为驱动开发（Behavior-Driven Development）的测试编写方式。
  */
/**
  * 测试类：利用Scala编写DSL
  */
class Course04DomainSpecificTest extends FunSpec with ShouldMatchers{

  describe("nerd finder"){
    it("identify nerds from a List"){
      var actors = List("Rick Moranis","James Deam","Woody Allen")
      var finder = new Course04DomainSpecific(actors)
      println(finder.findNerds())
      finder.findNerds shouldEqual List("Rick Moranis","James Deam","Woody Allen")
    }
  }
}
