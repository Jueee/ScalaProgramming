package com.scala.progscala.chapter03

/**
  * Trait：Scala语言的接口和“混入”
  *
  * Java  提供了接口，你可以在接口中声明方法，但却不能定义方法。至少在 Java 8  诞生之前是这样的。
  * 同样，你也可以在接口中声明和定义静态变量或者嵌套类型。
  *
  * Scala  使用 trait  来替代接口。在第 9  章我们会详细地讲述 trait ，目前你可以将其视为允许将声明方法实现的接口。
  * 使用 trait  时，你可以声明示例字段并选择是否定义这些字段，你也可以参照之前的枚举示例，在 trait  中声明或定义类型。
  * trait  提供的这些扩展被证实确实能够打破 Java  对象模型的一些局限。
  * Java  对象模型只允许在类中定义方法和字段，而 trait  则允许真正意义上的组合行为（ “ 混入 ” 模式）。
  */
object Course14Trait {

  /**
    * 应用服务
    * @param name
    */
  class ServiceImportante(name:String){
    def work(i:Int):Int={
      println(s"ServiceImportante:Doing important work!$i")
      i + 1
    }
  }

  trait Logging{
    def info    (message:String):Unit
    def warning (message:String):Unit
    def error   (message:String):Unit
  }

  trait StdoutLogging extends Logging{
    override def info   (message: String): Unit = println(s"INFO    :$message")
    override def warning(message: String): Unit = println(s"WARNING :$message")
    override def error  (message: String): Unit = println(s"ERROR   :$message")
  }

  def main(args: Array[String]): Unit = {
    val servicel = new ServiceImportante("uno")
    (1 to 3).foreach(i => println(s"Result:${servicel.work(i)}"))

    // 声明了一个 “ 混入 ” 了日志功能的服务：
    val service2 = new ServiceImportante("dos") with StdoutLogging{   // 为了能混入 trait ，我们需要使用 with 关键字。
      override def work(i: Int): Int = {  // 假如你希望覆盖父类中某一具体方法，那么 Scala  要求必须输入 override 关键字。
        info(s"Starting work: i = $i")
        val result = super.work(i)    // 通过 super.work 调用父类的 work 方法
        info(s"Ending work:i = $i, result = $result")
        result
      }
    }
    // 现在服务开启或结束工作时都会输出日志信息。
    (1 to 3).foreach(i => println(s"Result:${service2.work(i)}"))
  }

  /**
    * 假如我们希望能在 ServiceImportante 的多个实例中混入 StdoutLogging 特征，我们可以声明一个新类。
    * 在创建实例时， new LoggedServiceImportante("tres") 能够实现你想要的功能。
    */
  class LoggedServiceImportante(name:String) extends ServiceImportante(name) with StdoutLogging{
    // ...
  }
}
