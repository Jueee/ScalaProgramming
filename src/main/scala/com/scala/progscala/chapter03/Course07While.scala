package com.scala.progscala.chapter03

import java.util.Calendar

/**
  * 其他循环结构
  *
  * Scala  提供了许多其他的循环结构，由于 for 推导式是如此的灵活和强大，这些结果并未得到广泛的应用。
  */
object Course07While {

  def main(args: Array[String]): Unit = {
    content1  // 7.1 　 Scala 的 while  循环
    content2  // 7.2 　 Scala 中的 do-while  循环
  }

  /**
    * 7.1 　 Scala 的 while  循环
    * 只要判断条件成立， while 循环将一直运行对应代码块。
    */
  def content1: Unit ={
    println("-----7.1 　 Scala 的 while  循环-----")

    def isFridayThirteen(cal:Calendar):Boolean={
      val dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)
      val dayOfMonth = cal.get(Calendar.DAY_OF_MONTH)
      // Scala 将最后一个表达式的结果值作为该方法的返回结果
      (dayOfWeek == Calendar.FRIDAY) && (dayOfMonth == 13)
    }

    def nextDay(calendar: Calendar): Calendar ={
      calendar.add(Calendar.DAY_OF_MONTH,1)
      calendar
    }
    import java.text.SimpleDateFormat
    val sdf = new SimpleDateFormat("yyyy-MM-dd E")
    var day = Calendar.getInstance()
    while (!isFridayThirteen(day)){
      println(sdf.format(day.getTime) + " isn't Friday the 13th.Lame.")
      day = nextDay(day)
    }
    println(sdf.format(day.getTime) + " is Friday the 13th.Lame.")
  }

  /**
    * 7.2 　 Scala 中的 do-while  循环
    * 与 while 循环相似，只要条件表达式返回 true ， do-while 循环语句就会执行代码。
    * 也就是说，执行完代码块后， do-while 语句便会检查条件是否为真。
    */
  def content2: Unit ={
    println("-----7.2 　 Scala 中的 do-while  循环-----")
    var count = 0
    do {
      count += 1
      println(count)
    } while (count < 10)
  }
}
