package com.scala.progscala.chapter01

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

class ShapesDrawingDriver(drawerActor: ActorRef) extends Actor {

  import Messages._

  override def receive: Receive = {
    // 当 receive 方法接收到 Start 消息时，它将向 ShapesDrawingActor 发送五个异步消息
    case Start =>
      drawerActor ! Circle(Point(0.0, 0.0), 1.0)
      drawerActor ! Rectangle(Point(0.0, 0.0), 2, 5)
      drawerActor ! 3.1415926 // 将被视为错误信息
      drawerActor ! Triangle(Point(0.0, 0.0), Point(2.0, 0.0), Point(1.0, 2.0))
      drawerActor ! Exit
    // 关闭 actor  系统
    case Finished =>
      println(s"ShapesDrawingDriver:cleaning up...")
      context.system.shutdown()
    // 简单地打印出其他错误的回复信息
    case response: Response =>
      println("ShapesDrawingDriver:Response=" + response)
    // 用于处理预料之外的消息
    case unexpected =>
      println("ShapesDrawingDriver:ERROR:Received an unexpected message=" + unexpected)
  }
}

// 仅用于本文件的消息（私有消息），该消息用于启动。
private object Start

/**
  * “ 驱动 ”actor
  */
object ShapesDrawingDriver {
  def main(args: Array[String]): Unit = {
    // 把 ShapesDrawingActor 对象传递给了 ShapesDrawingDriver
    val system = ActorSystem("DrawingActorSystem", ConfigFactory.load())
    val drawer = system.actorOf(Props(new ShapesDrawingActor), "drawingActor")
    val driver = system.actorOf(Props(new ShapesDrawingDriver(drawer)), "drawingService")
    // 向驱动对象发送 Start 命令，启动应用！
    driver ! Start
  }
}
