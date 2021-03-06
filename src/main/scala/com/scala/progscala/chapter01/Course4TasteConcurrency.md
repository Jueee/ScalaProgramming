### 并发 ###
Scala  有许多诱人之处，能够使用 [Akka API](http://akka.io) 通过直观的 actor 模式构建健壮的并发应用便是其中之一。
  
在 actor  并发模型中， actor  是独立的软件实体，它们之间并不共享任何可变状态信息。   
actor  之间无须共享信息，通过交换消息的方式便可进行通信。   
#### Actor 示例 ####
我们会将表示几何图形的一组类的实例发送给一个 actor ，该 actor  再将这组实例绘制到显示器上。  
你可以想象这样一个场景： 渲染工厂 （ rendering farm ）在为动画生成场景。  
一旦场景渲染完毕，构成场景的几何图形便会被发送给某一 actor  进行展示。
##### 定义 Shape 类：[Shape.scala](Shape.scala) #####
-   类声明的前面输入了 case 关键字，因此每一个构造函数参数都自动转化为实例的某一只读（不可变）字段。  
-   由于 Point 类并没有类主体， case 关键字的另一个特征便是让编译器自动为我们生成许多方法。其中包括了类似于 Java 语言中 String、equals 和 hashCode 方法。  
-   对于 case 类，编译器同时会生成一个伴生对象（companion object），伴生对象是一个与 case 类同名的单例对象。  
-   可以自己定义伴生对象。任何时候只要对象名和类名相同并且定义在同一个文件中，这些对象就能称作伴生对象。  
-   apply 方法实际上是构建类对象的工厂方法，它的行为很简单；调用该方法就好像是不通过 new 关键字调用类的构造函数一样。
-   Unit 是一个实际存在的类型，它的表现却与 Java  中的 void 类型相似。  
-   假如某函数接受其他函数参数并返回函数，我们称之为高阶函数（higher-order function，HOF）。  

伴生对象其实与下列代码生成的对象无异：
<pre>def apply(x:Double=0.0, y:Double=0.0) = new Point(x,y)</pre>
##### 定义 ShapesDrawingActor 类：[ShapesDrawingActor.scala](ShapesDrawingActor.scala) #####
-   Scala  支持 嵌套导入 （ nesting import ），嵌套导入会限定这些值的作用域。
-   Akka  确保了消息处理的顺序与接收顺序相同，而对于那些正在被处理的消息， Akka  保证不会有其他线程抢占该消息。因此，
    使用 Akka  编写的消息处理代码天生具有线程安全的特性。
-   偏函数实际类型是 PartialFunction[Any,Unit] ，这说明偏函数接受单一的 Any 类型参数并返回 Unit 值。 Any 是 Scala
    类层次级别的根类，因此该函数可以接受任何参数。
-   偏函数中仅包含了一些 case 子句，这些子句会对传递给函数的消息执行模式匹配。
-   函数
    式编程中的模式匹配的重要性和复杂度都要远超过大多数命令式语言中对应的 swith/case 语句。
    
假如某一方法只接受单一参数，你可以省略掉对象后的点号和参数周边的括号。
下面两行代码是等价的：
<pre>sender ! Response(s"ShapesDrawingActor: $s drawn")
sender.!(Response(s"ShapesDrawingActor: $s drawn"))</pre>
##### 定义 ShapesDrawingDriver 类：[ShapesDrawingDriver.scala](ShapesDrawingDriver.scala) #####
-   由于所有的消息都是以异步的方式发送的，你可以看到驱动 actor  和绘图 actor  的消息交织在一起。
-   处理消息的顺序与发送消息的顺序相同。
-   运行多次应用程序，每次输出都会不同。
