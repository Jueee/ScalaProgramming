# ScalaProgramming #
《 Scala 程序设计（第 2 版）》学习笔记
### 版权所有 ###
Programming Scala, Second Edition by Dean Wampler and Alex Payne.   
Copyright 2015 Dean Wampler and Alex Payne, 978-1-491-94985-6.
[Git](https://github.com/deanwampler/prog-scala-2nd-ed-code-examples)  </br>
</br>
### 学习说明 ###
-   基于 Scala 2.11.X 版本。
-   原程序基于 sbt 打包，学习过程中替换为 maven 打包。

### 程序实现 ###

#### [第 01 章：零到六十： Scala 简介](src/main/scala/com/scala/progscala/chapter01/)

1. [为什么选择 Scala](src/main/scala/com/scala/progscala/chapter01/Course1WhyScala.md)  
   1.1	富有魅力的 Scala   
   1.2	关于 Java 8   
2. [安装 Scala](src/main/scala/com/scala/progscala/chapter01/Course2InstallingScala.md)   
   2.1	使用 SBT   
   2.2	执行 Scala 命令行工具   
   2.3	在 IDE 中运行 Scala REPL   
3. [使用 Scala](src/main/scala/com/scala/progscala/chapter01/Course3TasteScala.md)   
4. [并发](src/main/scala/com/scala/progscala/chapter01/Course4TasteConcurrency.md)      

#### [第 02 章：更简洁，更强大](src/main/scala/com/scala/progscala/chapter02/)

1. [分号](src/main/scala/com/scala/progscala/chapter02/Course01Semicolons.scala)   
2. [变量声明](src/main/scala/com/scala/progscala/chapter02/Course02VariableDeclarations.scala)   
3. [Range](src/main/scala/com/scala/progscala/chapter02/Course03Range.scala)   
4. [偏函数](src/main/scala/com/scala/progscala/chapter02/Course04PartialFunctions.scala)   
5. [方法声明](src/main/scala/com/scala/progscala/chapter02/Course05MethodDeclarations.scala)   
   5.1	方法默认值和命名参数列表   
   5.2	方法具有多个参数列表   
   5.3	Future 简介   
   5.4	嵌套方法的定义与递归   
6. [推断类型信息](src/main/scala/com/scala/progscala/chapter02/Course06InferringType.scala)   
7. [保留字](src/main/scala/com/scala/progscala/chapter02/Course07ReservedWords.scala)   
8. [字面量](src/main/scala/com/scala/progscala/chapter02/Course08LiteralValues.scala)   
   8.1	整数字面量   
   8.2	浮点数字面量   
   8.3	布尔型字面量   
   8.4	字符字面量   
   8.5	字符串字面量   
   8.6	符号字面量   
   8.7	函数字面量   
   8.8	元组字面量   
9. [Option 、 Some 和 None ：避免使用 null](src/main/scala/com/scala/progscala/chapter02/Course09Option.scala)   
10. [封闭类的继承](src/main/scala/com/scala/progscala/chapter02/Course10SealedClass.scala)   
11. [用文件和名空间组织代码](src/main/scala/com/scala/progscala/chapter02/Course11OrganizingCode.scala)   
12. [导入类型及其成员](src/main/scala/com/scala/progscala/chapter02/Course12ImportingTypes.scala)   
    12.1	导入是相对的   
    12.2	包对象   
13. [抽象类型与参数化类型](src/main/scala/com/scala/progscala/chapter02/Course13AbstractTypes.scala)     

#### [第 03 章：要点详解](src/main/scala/com/scala/progscala/chapter03/)

1. [操作符重载](src/main/scala/com/scala/progscala/chapter03/Course01OperatorOverloading.scala)       
2. [无参数方法](src/main/scala/com/scala/progscala/chapter03/Course02EmptyArgument.scala)   
3. [优先级规则](src/main/scala/com/scala/progscala/chapter03/Course03PrecedenceRules.scala)   
4. [领域特定语言](src/main/scala/com/scala/progscala/chapter03/Course04DomainSpecific.scala)   
5. [Scala 中的 if 语句](src/main/scala/com/scala/progscala/chapter03/Course05If.scala)   
6. [Scala 中的 for 推导式](src/main/scala/com/scala/progscala/chapter03/Course06For.scala)   
   6.1	for 循环   
   6.2	生成器表达式   
   6.3	保护式：筛选元素   
   6.4	Yielding   
   6.5	扩展作用域与值定义   
7. [其他循环结构](src/main/scala/com/scala/progscala/chapter03/Course07While.scala)   
   7.1	Scala 的 while 循环   
   7.2	Scala 中的 do-while 循环   
8. [条件操作符](src/main/scala/com/scala/progscala/chapter03/Course08ConditionalOperators.scala)   
9. [使用try、catch和final子句](src/main/scala/com/scala/progscala/chapter03/Course09TryCatch.scala)   
10. [名字调用和值调用](src/main/scala/com/scala/progscala/chapter03/Course10CallByName.scala)      
11. [惰性赋值](src/main/scala/com/scala/progscala/chapter03/Course11LazyVal.scala)   
12. [枚举](src/main/scala/com/scala/progscala/chapter03/Course12Enumerations.scala)   
13. [可插入字符串](src/main/scala/com/scala/progscala/chapter03/Course13InterpolatedStrings.scala)   
14. [Trait：Scala语言的接口和“混入”](src/main/scala/com/scala/progscala/chapter03/Course14Trait.scala)      

#### [第 04 章：模式匹配](src/main/scala/com/scala/progscala/chapter04/)

1. [简单匹配](src/main/scala/com/scala/progscala/chapter04/Course01SimpleMatch.scala)   
2. [match 中的值、变量和类型](src/main/scala/com/scala/progscala/chapter04/Course02Matches.scala)   
3. [序列的匹配](src/main/scala/com/scala/progscala/chapter04/Course03MatchSequences.scala)   
4. [元组的匹配](src/main/scala/com/scala/progscala/chapter04/Course04MatchTuples.scala)   
5. [case 中的 guard 语句](src/main/scala/com/scala/progscala/chapter04/Course05GuardsInCase.scala)   
6. [case 类的匹配](src/main/scala/com/scala/progscala/chapter04/Course06CaseClasses.scala)   
   6.1	unapply 方法   
   6.2	unapplySeq 方法   
7. [可变参数列表的匹配](src/main/scala/com/scala/progscala/chapter04/Course07MatchVariableArgument.scala)   
8. [正则表达式的匹配](src/main/scala/com/scala/progscala/chapter04/Course08MatchRegEx.scala)   
9. [再谈 case 语句的变量绑定](src/main/scala/com/scala/progscala/chapter04/Course09BindingVariables.scala)   
10. [再谈类型匹配](src/main/scala/com/scala/progscala/chapter04/Course10TypeMatching.scala)   
11. [封闭继承层级与全覆盖匹配](src/main/scala/com/scala/progscala/chapter04/Course11ExhaustiveMatches.scala)   
12. [模式匹配的其他用法](src/main/scala/com/scala/progscala/chapter04/Course12MatchOtherUses.scala)   
13. [总结关于模式匹配的评价](src/main/scala/com/scala/progscala/chapter04/Course13RemarksMatch.scala)          

#### [第 05 章：隐式详解](src/main/scala/com/scala/progscala/chapter05/)

1. [隐式参数](src/main/scala/com/scala/progscala/chapter05/Course01ImplicitArguments.scala)   
2. [隐式参数适用的场景](src/main/scala/com/scala/progscala/chapter05/Course02Scenarios.scala)   
   2.1	执行上下文   
   2.2	功能控制   
   2.3	限定可用实例    
   2.4	隐式证据   
   2.5	绕开类型擦除带来的限制   
   2.6	改善报错信息   
   2.7	虚类型   
   2.8	隐式参数遵循的规则   
3. [隐式转换](src/main/scala/com/scala/progscala/chapter05/Course03ImplicitConversions.scala)   
   3.1	构建独有的字符串插入器   
   3.2	表达式问题   
4. [类型类模式](src/main/scala/com/scala/progscala/chapter05/Course04TypeClass.scala)   
5. [隐式所导致的技术问题](src/main/scala/com/scala/progscala/chapter05/Course05TechnicalIssues.scala)   
6. [隐式解析规则](src/main/scala/com/scala/progscala/chapter05/Course06ResolutionRules.scala)   
7. [Scala  内置的各种隐式](src/main/scala/com/scala/progscala/chapter05/Course07BuiltInImplicits.scala)   
8. [合理使用隐式](src/main/scala/com/scala/progscala/chapter05/Course08WiseUseImplicits.scala)              

#### [第 06 章：Scala  函数式编程](src/main/scala/com/scala/progscala/chapter06/)

1. [什么是函数式编程](src/main/scala/com/scala/progscala/chapter06/Course01What.md)   
   1.1	数学中的函数   
   1.2	不可变变量   
2. [Scala 中的函数式编程](src/main/scala/com/scala/progscala/chapter06/Course02inScala.md)   
   2.1	匿名函数、 Lambda 与闭包   
   2.2	内部与外部的纯粹性   
3. [递归](src/main/scala/com/scala/progscala/chapter06/Course03Recursion.scala)   
4. [尾部调用和尾部调用优化](src/main/scala/com/scala/progscala/chapter06/Course04TailCalls.scala)    
5. [偏应用函数与偏函数](src/main/scala/com/scala/progscala/chapter06/Course05PartialFunctions.scala)   
6. [Curry 化与函数的其他转换](src/main/scala/com/scala/progscala/chapter06/Course06Currying.scala)   
7. [函数式编程的数据结构](src/main/scala/com/scala/progscala/chapter06/Course07DataStructures.scala)   
   7.1	序列   
   7.2	映射表   
   7.3	集合   
8. 遍历、映射、过滤、折叠与归约   
   8.1	[遍历](src/main/scala/com/scala/progscala/chapter06/Course081Traversal.scala)   
   8.2	[映射](src/main/scala/com/scala/progscala/chapter06/Course082Mapping.scala)   
   8.3	[扁平映射](src/main/scala/com/scala/progscala/chapter06/Course083FlatMapping.scala)   
   8.4	[过滤](src/main/scala/com/scala/progscala/chapter06/Course084Filtering.scala)   
   8.5	[折叠与归约](src/main/scala/com/scala/progscala/chapter06/Course085FoldingReducing.scala)   
9. [向左遍历与向右遍历](src/main/scala/com/scala/progscala/chapter06/Course09Traversals.scala)   
10. [组合器：软件最佳组件抽象](src/main/scala/com/scala/progscala/chapter06/Course10Combinators.scala)   
11. [关于复制](src/main/scala/com/scala/progscala/chapter06/Course11Copies.scala)              

#### [第 07 章：深入学习 for  推导式](src/main/scala/com/scala/progscala/chapter07/)

1. [内容回顾： for 推导式组成元素](src/main/scala/com/scala/progscala/chapter07/Course01ForElements.scala)   
2. for 推导式：内部机制   
3. for 推导式的转化规则   
4. Option 以及其他的一些容器类型   
   4.1	Option 容器   
   4.2	Either ： Option 类型的逻辑扩展   
   4.3	Try 类型   
   4.4	Scalaz 提供的 Validation 类   

#### [第 08 章： Scala  面向对象编程](src/main/scala/com/scala/progscala/chapter08/)

1. 类与对象初步   
2. 引用与值类型   
3. 价值类   
4. 父类   
5. Scala 的构造器   
6. 类的字段   
   6.1	统一访问原则   
   6.2	一元方法   
7. 验证输入   
8. 调用父类构造器（与良好的面向对象设计）     
9. 嵌套类型               