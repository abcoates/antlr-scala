package org.contakt.util

import scala.collection.mutable.{ListBuffer, Stack}

class DebugStack[X,T] extends Stack[T] {
  override def push(x: T): this.type = {
    x match {
      case lb:ListBuffer[_] if lb.size == 0 => // ignore
      case _ => println(s"push: $x")
    }
    super.push(x)
  }

  override def pop(): T = {
    val result = super.pop()
    result match {
      case lb:ListBuffer[_] if lb.size == 0 => // ignore
      case _ => println(s"pop: $result => $this")
    }
    result
  }

  def addTop(x: X): this.type = {
    if (size > 0) {
      top match {
        case lb: ListBuffer[X] =>
          println(s"addTop: $x => $top")
          lb.addOne(x)
        case _ => assert(false, "can't add to top if not ListBuffer")
      }
    } else {
      assert(false, "can't add to top of empty stack")
    }
    this
  }
}

object DebugStack {
  def apply[X,T]() = new DebugStack[X,T]()
}
