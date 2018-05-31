package com.scala.progscala.chapter05

/**
  * 隐式参数
  *
  * 使用了 implicit 关键字标记那些用户无需显式提供的方法参数。
  * 调用方法时，如果未输入隐式参数且代码所处作用域中存在类型兼容值时，类型兼容值会从作用域中调出并被使用，反之，系统将会抛出编译器错误。
  */
object Course01ImplicitArguments {

  def main(args: Array[String]): Unit = {
    example1
    example2
    example3
  }

  /**
    * 定义了一个用于计算销售税的方法，而税率被设置为隐式参数。
    * 调用该方法时，系统会将代码所在局部作用域中的某一隐式值传入此方法：
    */
  def example1: Unit ={
    def calcTax(amount:Float)(implicit rate:Float) :Float = amount * rate

    implicit val currentTaxRate = 0.08F

    val tax = calcTax(50000F)
    println(tax)  // 4000.0
  }

  /**
    * 例如某些应用需要知道当前事务发生的具体地点，以便增收地方税。
    * 而为了促进购物消费，某些辖区也可能会将年假的最后几天设定为 “ 免税期 ” 。
    *
    * 隐式对象本身不具有任何参数，除非该参数同样被标示为隐式参数。
    */
  def example2: Unit ={
    def calcTax(amount:Float)(implicit rate:Float) :Float = amount * rate

    object SimpleStateSalesTax{
      implicit val rate:Float = 0.05F
    }

    case class ComplicatedSalesTaxData(baseRate:Float, isTaxHoliday:Boolean, storeId:Int)

    object ComplicatedSalesTax{
      private def extraTaxRateForStore(id:Int) : Float ={
        0.0F  //  可以通过 id 推断出商铺所在地，之后再计算附加税 ……
      }

      implicit def rate(implicit cstd:ComplicatedSalesTaxData):Float=
        if (cstd.isTaxHoliday) 0.0F
        else cstd.baseRate + extraTaxRateForStore(cstd.storeId)
    }

    {
      import SimpleStateSalesTax.rate

      val amount = 100F
      println(s"Tax on $amount = ${calcTax(amount)}") // Tax on 100.0 = 5.0
    }
    {
      import ComplicatedSalesTax.rate
      implicit val myStore = ComplicatedSalesTaxData(0.06F, false, 1010)

      val amount = 100F
      println(s"Tax on $amount = ${calcTax(amount)}") // Tax on 100.0 = 6.0
    }

  }

  /**
    * 调用 implicitly 方法
    * Predef 对象中定义了一个名为 implicity 的方法。
    * 如果将 implicity 方法与 附加类型签名 相结合，便能以一种有用且快捷的方式定义一个接收参数化类型隐式参数的函数。
    *
    * 我们使用了这种方法对 List 的 sortBy 方法进行封装。
    *
    * 类型参数 B :Ordering 被称为 上下文定界，它暗指第二个参数列表（也就是那个隐式参数列表）将接受 Ordering[B] 实例。
    */
  def example3: Unit ={
    case class MyList[A](list: List[A]){
      def sortBy1[B](f:A => B)(implicit ord:Ordering[B]):List[A] = list.sortBy(f)(ord)
      def sortBy2[B:Ordering](f:A => B):List[A] = list.sortBy(f)(implicitly[Ordering[B]])
    }

    val list = MyList(List(1,3,5,2,4))
    // 正序
    println(list sortBy1(i => i))
    println(list sortBy2(i => i))
    // 倒序
    println(list sortBy1(i => -i))
    println(list sortBy2(i => -i))
  }
}
