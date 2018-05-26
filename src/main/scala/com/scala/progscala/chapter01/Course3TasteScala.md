### 使用 Scala ###
Scala 遵循 Java、C#、C 等语言的注释规则：
-   //  comment 只能作用到本行行尾
-   /*  comment */  则可以跨行。

### HelloWorld 程序 ###
HelloWorld 程序 : [HelloClass.scala](HelloClass.scala)

启动 console 命令以开启 Scala 环境。然后使用 :load 命令加载（编译并运行）文件：
<pre>
E:\code\Java\workspace48\ScalaProgramming>scala
Welcome to Scala 2.11.8 (Java HotSpot(TM) Client VM, Java 1.8.0_111).
Type in expressions for evaluation. Or try :help.

scala> :load src/main/scala/com/scala/progscala/chapter01/HelloClass.scala
Loading src\main\scala\com\scala\progscala\chapter01\HelloClass.scala...
defined class HelloClass
defined object HelloTest</pre>

将脚本文件编译为 JVM 的字节码（一组 .class 文件）：
-   没有包路径的情况：
<pre>
$ scalac src/main/scala/com/scala/progscala/chapter01/HelloClass.scala

$ scala HelloTest
ArrayBuffer(HELLO, WORLD)</pre>
-   有包路径的情况：
<pre>
$ scalac src/main/scala/com/scala/progscala/chapter01/HelloClass2.scala

$ scala -cp . com.scala.progscala.chapter01.HelloTest2
ArrayBuffer(HELLO, WORLD, TEST)</pre>
【注】通过选项 -cp . 将当前目录添加到查询类路径（classpath）中。