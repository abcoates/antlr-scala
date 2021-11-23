package org.contakt.scala.antlr

import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.{ErrorNode, ParseTreeListener, TerminalNode}
import scala.collection.mutable.{ListBuffer, Stack}
import org.contakt.util.DebugStack

class AntlrScalaListener extends ParseTreeListener {

  val stack: Stack[ListBuffer[AnyRef]] = Stack[ListBuffer[AnyRef]]()

  override def enterEveryRule(ctx: ParserRuleContext): Unit = {
    stack.push(ListBuffer[AnyRef]())
  }

  override def exitEveryRule(ctx: ParserRuleContext): Unit = {
    if (stack.size > 1) {
      if (stack.top.isEmpty) {
        stack.pop()
      } else {
        val buffer = stack.pop()
        if (buffer.size > 1) {
          stack.top.addOne(buffer.toList)
        } else {
          stack.top.addOne(buffer.head)
        }
      }
    }
    if (ctx.getParent == null) { // we are back at the top-level parse expression
      val result = stack.top match {
        case lb: ListBuffer[_] => lb.toList
        case other => other
      }
      println(s"$result")
    }
  }

  override def visitTerminal(node: TerminalNode): Unit = {
    stack.top.addOne(node.getText)
  }

  override def visitErrorNode(node: ErrorNode): Unit = {}

}
