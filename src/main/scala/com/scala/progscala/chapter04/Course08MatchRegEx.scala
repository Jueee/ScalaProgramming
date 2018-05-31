package com.scala.progscala.chapter04

/***
  * 正则表达式的匹配
  *
  * 正则表达式可以很方便地从符合特定结构的字符串中提取数据。
  *
  * 1、调用 r 方法以创建正则表达式。
  * 2、用三重双引号来表示正则表达式字符串，否则，就不得不对正则表达式的反斜杠进行转义，例如用 \\s 表示 \s 。
  * 3、在三个双引号内的正则表达式中使用变量插值是无效的。
  * 4、依然需要对变量插值进行转义，例如，你应该用 s"""$first\\s+$second""".r ，而不是s"""$first\s+$second""".r 。而如果你没有使用变量插值，则不必转义。
  */
object Course08MatchRegEx {

  def main(args: Array[String]): Unit = {
    val BookExtractorRE = """Book: title=([^,]+),\s+author=(.+)""".r
    val MagazineExtractorRE = """Magazine: title=([^,]+),\s+issue=(.+)""".r

    val catalog = Seq(
      "Book: title=Programming Scala Second Edition, author=Dean Wampler",
      "Magazine: title=The New Yorker, issue=January 2014",
      "Unknown: text=Who put this here??"
    )

    for (item <- catalog){
      item match{
        case BookExtractorRE(title,author) => println(s"""Book $title, writthen by $author""")
        case MagazineExtractorRE(title,issue) => println(s"""Magazine $title, isssue $issue""")
        case entry => println(s"Unrecognized entry: $entry")
      }
    }
  }
}
