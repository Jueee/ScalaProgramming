### 什么是函数式编程 ###
函数式编程的理论基础是数学中关于函数和值的规则。这对软件编程中的函数有着深远的影响。

### 数学中的函数 ###
在数学里，函数没有副作用。  

例如，对于函数 y =sin(x)。
无论 sin(x) 做了多少计算，它的所有结果都被函数返回并赋值给了 y  。  
在 sin(x )  内部，没有任何全局状态被修改。  
这样，我就称该函数是无副作用函数，即纯函数。
#### 纯函数 ####
纯函数极大地简化了函数的分析、测试和调试。  
你可以不考虑调用该函数的上下文信息，否则的话，就要受该上下文中调用的其他函数的影响了。  
由于可以忽略上下文信息，引用是透明的，这带来了两个结果：  
-   可以在任何地方调用函数，并确信其行为与上下文无关，每次的行为都能够确保相同。  
由于没有任何全局对象被修改，对函数的并发调用也是安全可靠的，不需要任何线程安全的编写技巧。
-   可以用表达式所计算得出的值替换表达式本身。
#### 函数式编程中的函数 ####
在函数式编程中，函数是第一等级的值，就像数据变量的值一样。
-   可以从函数中组合形成新函数（如 tan(x)=sin(x)/cos(x) ）
-   可以将函数赋值给变量
-   可以将函数作为参数传递给其他函数
-   可以将函数作为其他函数的返回值
#### 高阶函数 ####
当一个函数采用其他函数作为变量或返回值时，它被称为**高阶函数**。  
在数学中，微积分中有两个高阶函数的例子 —— 微分与积分。  
我们将一个表达式作为函数传给 “ 微分函数 ” ，然后微分函数返回了一个新函数，即原函数的导数。
### 不可变变量 ###
在函数式编程中，变量是不可变的。  
在表达式 y =sin(x)  中，一旦 x  确定， y  也就确定了。类此地，值也是不可变的。  
我们在这里用 “ 值 ” 一词作为不可变变量的同义词。  
#### 并发程序 ####
值不可变性对编写可扩展的并发程序有巨大的好处。  
多线程程序的大部分难点在于对公共的可变状态进行访问时的同步问题。  
如果去掉了公共状态的可变性，这个问题将不复存在。  
引用透明的函数和值不可变性的结合，使得函数式编程成为编写并发软件应用的更好选择。  
对并发程序进行扩展的需求的增长，提高了业界对函数式编程的关注。