package com.scala.progscala.chapter02

/**
  * 用文件和名空间组织代码
  *
  * Scala  沿用 Java  用包来表示命名空间的这一做法，但它却更具灵活性。
  * 文件名不必与类名一致，包结构不一定要与目录结构一致。
  */

// Scala  也支持使用嵌套块结构语法来定义包的作用域
package com{
  package scala{
    package progscala{
      // 每一层包单独使用一条 package 语句
      package chapter02{
        package pkg1{
          class Class1{
            def m = "m11"
          }
          class Class2{
            def m = "m22"
          }
        }
      }
      // 可以在一条语句中将多个包 “ 链接 ” 在一起
      package chapter02.test{
        class Class3{
          def m = "mtest3"
        }
      }
    }
  }
}
package com.scala.progscala.chapter02.test4{
  class Class4{
    def m = "mClass4"
  }
}

object Course11OrganizingCode{
  def main(args: Array[String]): Unit = {
    println((new com.scala.progscala.chapter02.pkg1.Class1).m)
    println((new com.scala.progscala.chapter02.pkg1.Class2).m)
    println((new com.scala.progscala.chapter02.test.Class3).m)
    println((new com.scala.progscala.chapter02.test4.Class4).m)
  }
}
