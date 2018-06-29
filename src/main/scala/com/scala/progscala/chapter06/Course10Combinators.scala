package com.scala.progscala.chapter06

/**
  * 组合器：软件最佳组件抽象
  *
  *
  */
object Course10Combinators {

  /**
    * 定义一个 Employee 类来放置雇员所需的字段类型
    * @param name
    * @param title
    * @param annualSalary
    * @param taxRate
    * @param insurancePremiumsPerWeek
    */
  case class Employee(name: String,title:String, annualSalary:Double, taxRate:Double, insurancePremiumsPerWeek:Double)

  val employee = List(
    Employee("Buck Trends", "CEO", 200000, 0.25, 100.0),
    Employee("Cindy Banks", "CFO", 170000, 0.22, 120.0),
    Employee("Joe Coder", "Developer", 130000, 0.20, 120.0)
  )

  def main(args: Array[String]): Unit = {

    //  计算每周工资单
    val netPay = employee map{e=>
      val net = (1.0- e.taxRate) * (e.annualSalary /52.0) - e.insurancePremiumsPerWeek
      (e,net)
    }

    // “ 打印 ” 工资单
    println("** Paychecks:")
    netPay foreach {
      case (e, net) => println(f" ${e.name+':'}%-16s ${net}%10.2f")
    }

    //  生成报表 :
    val report = (netPay foldLeft (0.0, 0.0, 0.0)) {
      case ((totalSalary, totalNet, totalInsurance), (e, net)) =>
        (totalSalary + e.annualSalary/52.0,
          totalNet + net,
          totalInsurance + e.insurancePremiumsPerWeek)
    }
    println("\n** Report:")
    println(f" Total Salary: ${report._1}%10.2f")
    println(f" Total Net: ${report._2}%10.2f")
    println(f" Total Insurance: ${report._3}%10.2f")
  }
}
