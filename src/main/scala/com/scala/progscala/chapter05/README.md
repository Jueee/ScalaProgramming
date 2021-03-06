### 第 05 章：隐式详解 ###
隐式 （ implicit ）是 Scala  的一个强大的特性，同时也是一个可能存在争议的特性。  
使用隐式能够减少代码，能够向已有类型中注入新的方法，也能够创建 领域特定语言 （ DSL ）。  
  
隐式之所以会产生争议，是因为除了通过 Predef 对象自动加载的那些隐式对象外，其他在源码中出现的隐式对象均不是本地对象。  
隐式对象一旦进入作用域，编译器便能执行该隐式对象以生成方法参数或将指定参数转化成预期类型。  
#### 本章目录 ####
1.	[隐式参数](Course01ImplicitArguments.scala)   
2.	[隐式参数适用的场景](Course02Scenarios.scala)   
2.1	执行上下文   
2.2	功能控制   
2.3	限定可用实例    
2.4	隐式证据   
2.5	绕开类型擦除带来的限制   
2.6	改善报错信息   
2.7	虚类型   
2.8	隐式参数遵循的规则   
3.	[隐式转换](Course03ImplicitConversions.scala)   
3.1	构建独有的字符串插入器   
3.2	表达式问题   
4.	[类型类模式](Course04TypeClass.scala)   
5.	[隐式所导致的技术问题](Course05TechnicalIssues.scala)   
6.	[隐式解析规则](Course06ResolutionRules.scala)   
7.	[Scala  内置的各种隐式](Course07BuiltInImplicits.scala)   
8.	[合理使用隐式](Course08WiseUseImplicits.scala)                
    
#### 本章回顾 ####    
-   深入学习了 Scala  语言中关于隐式的各种知识。
-   希望能在理解了隐式的功能和用途的同时，还能牢记它们的缺点。
   