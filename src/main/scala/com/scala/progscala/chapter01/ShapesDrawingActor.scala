package com.scala.progscala.chapter01

/**
  *  定义了大多数 actor 之间进行通信的消息
  */
object Messages {
  object Exit;    // 不包含任何状态，起到了标志的作用
  object Finished // 不包含任何状态，起到了标志的作用
  case class Response(message: String)  // 构造字符串消息，并将消息返回给发送者
}

import akka.actor.Actor

/**
  *   定义 actor 类，用于绘制图形
  */
class ShapesDrawingActor extends Actor{
  import Messages._

  /**
    * 处理接收到的消息
    * Actor.sender 函数返回了 actor 发送消息接收方的对象引用，而 ! 方法则用于发送异步消息。
    * @return
    */
  override def receive: Receive = {
    // 偏函数
    // receive 方法会尝试将接收到的各条消息与这三个模式匹配表达式进行匹配，并执行最先被匹配上的表达式。
    case s:Shape =>     // 信息是 Shape 的一个实例
      s.draw(str => println(s"ShapesDrawingActor:$str"))    // 此匿名函数仅打印了生成的字符串
        sender ! Response(s"ShapesDrawingActor:$s drawn")   // 向 “ 发信方 ” 回复了一个消息
    case Exit =>        // Exit 消息用于标识已经完成
      println(s"ShapesDrawingActor:exiting...")             // 打印了一条表示正在退出的消息
      sender ! Finished                                     // 向 “ 发信方 ” 发送了一条结束信息
    case unexpected =>  // “默认”子句，可以匹配任何输入
      val response = Response(s"ERROR:Unknown message:$unexpected")
      println(s"ShapesDrawingActor:$response")              // 根据错误信息生成 Response 对象，并打印错误信息
      sender ! response                                     // 向 “ 发信方 ” 回复了这条信息
  }
}

