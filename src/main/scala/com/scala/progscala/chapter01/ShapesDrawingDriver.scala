package com.scala.progscala.chapter01

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

class ShapesDrawingDriver(drawerActor: ActorRef) extends Actor{
  import Messages._

  override def receive: Receive = {
    case Start =>
      drawerActor ! Circle(Point(0.0,0.0),1.0)
      drawerActor ! Rectangle(Point(0.0,0.0),2,5)
      drawerActor ! 3.1415926
      drawerActor ! Triangle(Point(0.0,0.0),Point(2.0,0.0),Point(1.0,2.0))
      drawerActor ! Exit
    case Finished =>
      println(s"ShapesDrawingDriver:cleaning up...")
      context.system.shutdown()
    case unexpected =>
      println("ShapesDrawingDriver:ERROR:Received an unexpected message="+unexpected)
  }
}

private object Start

object ShapesDrawingDriver {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("DrawingActorSystem", ConfigFactory.load())
    val drawer = system.actorOf(Props(new ShapesDrawingActor),"drawingActor")
    val driver = system.actorOf(Props(new ShapesDrawingDriver(drawer)),"drawingService")
    drawer ! Start
  }
}
