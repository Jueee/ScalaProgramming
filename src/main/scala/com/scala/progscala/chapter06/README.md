### 第 06 章：Scala  函数式编程 ###
函数式编程主要可以为当前面临的三大挑战提供解决方案：  
1.  是并发的普遍需求。  
有了并发，我们可以对应用进行水平扩展，并提供其对抗服务器故障的能力。  
所以，如今并发编程已经是每个开发者的必备技能了。  
2.  是编写数据导向（如 “ 大数据 ” ）程序的要求。  
当然，从某种意义上说，每个程序都与数据密切相关，但如今大数据的发展趋势，使得有效处理海量数据的技术被提高到了更重要的位置。  
3.  是编写无 bug 的程序的要求。  
这个挑战与编程本身一样古老，但函数式编程从数学的角度为我们提供了新的工具，使我们向无 bug  的程序又迈进了一步。

状态不可变这一特点解决了并发编程中最大的难题，即对共享的可变状态的访问问题。  
因此，编写状态不可变的代码就称为编写健壮的并发程序的必备品，而拥抱函数式编程就是写出状态不可变代码的最好途径。  
状态不可变，以及严密的函数式编程思想有其数学理论为基础，还能减少程序中的逻辑错误。  
#### 本章目录 ####
1.	[什么是函数式编程](Course01What.md)   
1.1	数学中的函数   
1.2	不可变变量   
2.	[Scala 中的函数式编程](Course02inScala.md)   
2.1	匿名函数、 Lambda 与闭包   
2.2	内部与外部的纯粹性   
3.	[递归](Course03Recursion.scala)   
4.	[尾部调用和尾部调用优化](Course04TailCalls.scala)    
5.	[偏应用函数与偏函数](Course05PartialFunctions.scala)   
6.	[Curry 化与函数的其他转换](Course06Currying.scala)   
7.	[函数式编程的数据结构](Course07DataStructures.scala)   
7.1	序列   
7.2	映射表   
7.3	集合   
8.	遍历、映射、过滤、折叠与归约   
8.1	[遍历](Course081Traversal.scala)   
8.2	[映射](Course082Mapping.scala)   
8.3	[扁平映射](Course083FlatMapping.scala)   
8.4	[过滤](Course084Filtering.scala)   
8.5	[折叠与归约](Course085FoldingReducing.scala)   
9.	[向左遍历与向右遍历](Course09Traversals.scala)   
10.	[组合器：软件最佳组件抽象](Course10Combinators.scala)   
11.	[关于复制](Course11Copies.scala)              
    
#### 本章回顾 ####    
-   讨论了函数式编程的基本概念，并指出它们对解决现代软件开发问题的重要性。
-   学习了基本集合类型及其高阶函数和组合器如何产生简洁、强大的模块化代码。
   
#### 拓展阅读 ####    
-   Chris Okasaki  的 Purely Functional Data Structures。
-   Richard Bird  的 Pearls of Functional Algorithm Design。
-   Fethi Rabhi  和 Guy Lapalme  的 Algorithms: A Functional Programming Approach。