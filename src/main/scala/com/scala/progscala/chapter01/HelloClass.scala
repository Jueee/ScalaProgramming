package com.scala.progscala.chapter01

class HelloClass {
  def upper(strings:String*):Seq[String]={
    strings.map((s:String) => s.toUpperCase())
  }
}