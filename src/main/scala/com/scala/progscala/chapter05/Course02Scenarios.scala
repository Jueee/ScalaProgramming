package com.scala.progscala.chapter05

import scala.collection.TraversableOnce
import scala.collection.generic.CanBuildFrom
import scala.concurrent.Future

/**
  * 隐式参数适用的场景
  *
  * 应谨慎明智地使用隐式对象。滥用隐式对象会让读者无法理解代码的用意。
  *
  * 隐式参数为这些习语带来两类好处：
  * 第一类好处是能够消除样板代码，例如隐式对象提供了上下文信息以省略明确指定这些信息的代码；
  * 第二类好处是通过引入约束来减少 bug  数量以及使用参数化类型对某些方法允许的输入参数类型进行限定。
  *
  * 隐式参数所遵循的规则
    (1)  只有最后一个参数列表中允许出现隐式参数，这也适用于只有一个参数列表的情况。
    (2)  implicit 关键字必须出现在参数列表的最左边，而且只能出现一次。列表中出现在 implicit  关键字之后的参数都不是 “ 非隐式 ” 的。
    (3)  假如参数列表以 implicit 关键字开头，那么所有的参数都是隐式的。
  *
  */
object Course02Scenarios {

  def main(args: Array[String]): Unit = {
    println("-----2.1 　执行上下文-----")
    content1 // 2.1 　执行上下文
    println("-----2.2 　功能控制-----")
    content2 // 2.2 　功能控制
    println("-----2.3 　限定可用实例-----")
    content3 // 2.3 　限定可用实例
    println("-----2.4 　隐式证据-----")
    content4 // 2.4 　隐式证据
    println("-----2.5 　绕开类型擦除带来的限制-----")
    content5 // 2.5 　绕开类型擦除带来的限制
    println("-----2.6 　改善报错信息-----")
    content6 // 2.6 　改善报错信息
    println("-----2.7 　虚类型-----")
    content7 // 2.7 　虚类型
    println("-----2.8 　隐式参数遵循的规则-----")
    content8 // 2.8 　隐式参数遵循的规则
  }

  /**
    * 2.1 　执行上下文（参照第二章 Course05MethodDeclarations 类的content3方法）
    * 示例中的 apply 方法的第二个参数列表被设为隐式参数，该参数用于将ExecutionContext 对象传递给 Future.apply 方法
    * 需要导入可供编译器使用的全局默认值：import scala.concurrent.ExecutionContext.Implicits.global
    */
  def content1: Unit = {
    import scala.concurrent.ExecutionContext.Implicits.global
    def sleep(millis: Long): Unit = {
      Thread.sleep(millis)
    }
    //  繁忙的处理工作
    def doWork(index: Int) = {
      sleep((math.random * 1000).toLong)
      index
    }
    // 并发发出5个任务，并在任务结束时处理任务返回的结果：
    (1 to 5) foreach { index =>
      val future = Future {   // Future.apply 返回一个新的 Future 对象
        doWork(index)
      }
      future onSuccess {    // 当 future 成功执行完毕后，该回调会被执行
        case answer: Int => println(s"Success! returned:$answer")
      }
      future onFailure {    // 用 onFailure 注册了一个回调函数来处理错误
        case th: Throwable => println(s"FAILURE! returned:$th")
      }
    }
    sleep(1000) //  等待足够长的时间，以确保工作线程结束。
    println("Finish!")
  }

  /**
    * 2.2 　功能控制
    * 举个例子，通过引入授权令牌，我们可以控制某些特定的 API  操作只能供某些用户调用，我们也可以使用授权令牌决定数据可见性，而隐式用户会话参数也许就包含了这类令牌信息。
    * 假设你想要创建用户界面的菜单，其中某些菜单项只对已登录用户可见，而其他菜单项则仅对未登录用户可见。
    */
  def content2: Unit = {
    """
    def createMenu(implicit session: Session):Menu={
      val defaultItems = List(helpItem,searchItem)
      val accountItems =
        if (session.loggedin()) List(viewAccountItem,editAccountItem)
        else List(logintem)
      Menu(defaultItems ++ accountItems)
    }
    """
  }

  /**
    * 2.3 　限定可用实例
    * 我们希望对具有参数化类型方法中的类型参数进行限定，使该参数只接受某些类型的输入。
    *  +A 意味着 TraversableLike[A] 类型是 A 类型的 协变 （ covariant ）；
    *  假如 B 类型是 A 类型的子类型，那么 TraversableLike[B] 类型也是 TraversableLike[A] 类型的子类型。
    */
  def content3: Unit = {
    trait TraversableLike[+A,+Repr] {
      def map[B,That](f:A => B)(
        implicit bf:CanBuildFrom[Repr,B,That]):That={None.get}
    }
  }

  /**
    * 2.4 　隐式证据
    */
  def content4: Unit = {
    """
      |  scala.collection.TraversableOnce 是这样定义 toMap 方法的：
      |  def toMap[T, U](implicit ev: A <:< (T, U)): immutable.Map[T, U] = {
      |    val b = immutable.Map.newBuilder[T, U]
      |    for (x <- self)
      |      b += x
      |
      |    b.result
      |  }
      |  隐式参数 ev 便是我们的 “ 证据 ” ，它代表了我们必须实施的约束。
      |  ev 运用了 Predef 中定义的名为 <:< 的类型，该名字取自于 <: 方法。
      |  <: 方法同样也被用于限定类型参数，例如： A <: B 。
    """
  }

  /**
    * 2.5 　绕开类型擦除带来的限制
    */
  def content5: Unit = {
    """因为这些方法在字节码中是一样的，编译器不允许同时出现这些方法定义:
    object C{
      def m(seq: Seq[Int]):Unit = println(s"Seq[Int]:$seq")
      def m(seq: Seq[String]):Unit = println(s"Seq[String]:$seq")
    }
    """
    // 可以通过添加隐式参数来消除这些方法的二义性：
    object M{
      implicit object IntMarker   // 用于解决由于类型擦除导致的方法二义性问题
      implicit object StringMarker
      def m(seq: Seq[Int])(implicit i:IntMarker.type ):Unit = println(s"Seq[Int]:$seq")
      def m(seq: Seq[String])(implicit s:StringMarker.type ):Unit = println(s"Seq[String]:$seq")
    }
    M.m(List(1,2,3))        // Seq[Int]:List(1, 2, 3)
    M.m(List("a","b","c"))  // Seq[String]:List(a, b, c)
  }

  /**
    * 2.6 　改善报错信息
    */
  def content6: Unit = {
  }

  /**
    * 2.7 　虚类型
    * 简化版的工资计算器的例子。
    * 根据美国税法，在计算工资税之前，保险基金以及某些退休存款（ 401k ）账户可以作为抵税项先从工资中扣除。
    * 因此，工资计算器必须首先执行 “ 扣税前 ” 的减项操作，然后进行扣税，最后计算器会扣除扣税后的其他减项并算出净收入。
    */
  def content7: Unit = {
    // 密封特征 （ sealed trait ）本身不包含任何数据，而且没有实现这些 trait  的类。
    // 由于这些 trait  被“密封”了，我们无法在其他文件中实现这些 trait ，因此，这些 trait  只能起到标志的作用。
    sealed trait PreTaxDeductions
    sealed trait PostTaxDeductions
    sealed trait Final

    case class Employee(name: String,
                        annualSalary:Float,
                        taxRate:Float,    //  为了简化起见， 所有的税种税率相同。
                        insurancePremiumsPerPayPeriod:Float,
                        _401kDeductionRate: Float,  //  税前扣除项，美国退休储蓄计划扣款。
                        postTaxDeductions: Float )

    case class Pay[Step](employee: Employee,netPay:Float)

    object Payroll{
      //  每两周发一次薪水。为了简化问题，我们认定每年正好有 52 周。
      def start(employee: Employee):Pay[PreTaxDeductions] =
        Pay[PreTaxDeductions](employee, employee.annualSalary / 26.0F)
      def minusInsurance(pay: Pay[PreTaxDeductions]):Pay[PreTaxDeductions]={
        val newNext = pay.netPay - pay.employee.insurancePremiumsPerPayPeriod
        pay copy(netPay = newNext)
      }
      def minus401k(pay: Pay[PreTaxDeductions]):Pay[PreTaxDeductions] = {
        val newNet = pay.netPay - pay.employee._401kDeductionRate * pay.netPay
        pay copy(netPay = newNet)
      }
      def minusTax(pay: Pay[PreTaxDeductions]):Pay[PreTaxDeductions] = {
        val newNet = pay.netPay - pay.employee.taxRate * pay.netPay
        pay copy(netPay = newNet)
      }
      def minusFinalDeductions(pay: Pay[PreTaxDeductions]):Pay[Final] = {
        val newNet = pay.netPay - pay.employee.postTaxDeductions
        pay copy(netPay = newNet)
      }
    }

    val e = Employee("Buck Trends", 100000.0F, 0.25F, 200F, 0.10F, 0.05F)
    val pay1 = Payroll start e
    // 401K 和保险扣除的顺序可以交换
    val pay2 = Payroll minus401k pay1
    val pay3 = Payroll minusInsurance pay2
    val pay4 = Payroll minusTax pay3
    val pay = Payroll minusFinalDeductions pay4
    val twoWeekGross = e.annualSalary / 26.0F
    val twoWeekNet = pay.netPay
    val percent = (twoWeekNet / twoWeekGross) * 100
    println(s"For ${e.name}, the gross vs. net pay every 2 weeks is:")
    println(f"$$${twoWeekGross}%.2f vs. $$${twoWeekNet}%.2f or ${percent}%.1f%%")
  }

  /**
    * 2.8 　隐式参数遵循的规则
    */
  def content8: Unit = {
    """ 错误示例
    class Bad1 {
      def m(i: Int,implicit s: String) = "boo"
    }
    """

  }
}
