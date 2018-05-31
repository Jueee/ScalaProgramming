package com.scala.progscala.chapter04



/**
  * 可变参数列表的匹配
  *
  * 匹配可变参数的语法形式 : name @ _* 。
  */
/**
  * 带可变参数列表的 case  类，用于处理 SQL  语句中的各个值。
  * 代码中还包括了另外一些定义，用于处理 WHERE x OP y 这样的 SQL  语句， OP 是 SQL  的比较操作符。
  */
object Course07MatchVariableArgument {

  // 用于表示比较 SQL  操作符，每个操作符有一个 “ 名字 ” ，是一个字符串。
  object Op extends Enumeration{
    type Op = Value

    val EQ = Value("=")
    val NE = Value("!=")
    val LTGT = Value("<>")
    val LT = Value("<")
    val LE = Value("<=")
    val GT = Value(">")
    val GE = Value(">=")
  }
  import Op._
  //  表示 SQL 的 "WHERE x op value" 语句，其中 +op+ 为一个比较操作符 : =, !=, <>, <, <=, >, or >= 。
  case class WhereOp[T](columnName:String, op: Op, value:T)

  //  表示 SQL 的 "WHERE x IN (a, b, c, ...)"  语句。
  case class WhereIn[T](columnName:String, val1: T, values:T*)

  def main(args: Array[String]): Unit = {
    val wheres = Seq(
      WhereIn("state","IL","CA","VA"),
      WhereOp("state",EQ,"IL"),
      WhereOp("name", EQ, "Buck Trends"),
      WhereOp("age", GT, 29)
    )

    for (where <- wheres){
      where match {
        case WhereIn(col,val1, vals @ _*) =>
          val valStr = (val1 +: vals).mkString(", ")
          println(s"WHERE $col IN ($valStr)")
        case WhereOp(col,op,value) => println(s"WHERE $col $op $value")
        case _ => println(s"ERROR:UNknown expression:$where")
      }
    }
  }
}
