### 第 04 章：模式匹配 ###
在 Scala  的模式匹配中，可以使用类型、通配符、序列、正则表达式，甚至可以深入获取对象的状态。  
这种对象状态的获取遵循一定的协议，也就是对象内部状态的可见性由该类型的实现来控制，这使得我们能够轻易获取暴露的状态并应用于变量中。  
对象状态的获取往往被称为 “ 提取 ” 或 “ 解构 ” 。  
#### 本章目录 ####
1.	[简单匹配](Course01SimpleMatch.scala)   
2.	[match 中的值、变量和类型](Course02Matches.scala)   
3.	[序列的匹配](Course03MatchSequences.scala)   
4.	[元组的匹配](Course04MatchTuples.scala)   
5.	[case 中的 guard 语句](Course05GuardsInCase.scala)   
6.	[case 类的匹配](Course06CaseClasses.scala)   
6.1	unapply 方法   
6.2	unapplySeq 方法   
7.	[可变参数列表的匹配](Course07MatchVariableArgument.scala)   
8.	[正则表达式的匹配](Course08MatchRegEx.scala)   
9.	[再谈 case 语句的变量绑定](Course09BindingVariables.scala)   
10.	[再谈类型匹配](Course10TypeMatching.scala)   
11.	[封闭继承层级与全覆盖匹配](Course11ExhaustiveMatches.scala)   
12.	[模式匹配的其他用法](Course12MatchOtherUses.scala)   
13.	[总结关于模式匹配的评价](Course13RemarksMatch.scala)              
    
#### 本章回顾 ####    
-   模式匹配是很多函数式语言之所以强大的标志。
-   模式匹配是一个可以灵活又简洁地从数据结构中抽取数据的工具。
   