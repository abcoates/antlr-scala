package org.contakt.scala.antlr

import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.{ErrorNode, ParseTreeListener, TerminalNode}
import scala.collection.mutable.{ListBuffer, Stack}
import org.contakt.util.DebugStack

class AntlrScalaListener extends ParseTreeListener {
  val stack = Stack[ParsedItem]()
  var index = 0
  var depth = 0

  override def enterEveryRule(ctx: ParserRuleContext): Unit = {
    stack.push(MutableParsedNode(index, depth, ListBuffer[ParsedItem]()))
    index += 1
    depth += 1
  }

  override def exitEveryRule(ctx: ParserRuleContext): Unit = {
    depth -= 1
    if (stack.size > 1) {
      if (stack.top.isEmpty) {
        stack.pop()
      } else {
        val top = stack.pop()
        stack.top match {
          case mpn: MutableParsedNode => mpn.parseItems.addOne(top)
          case _ => assert(false, "expected to find MutableParsedNode on top of stack")
        }
      }
    }
    if (ctx.getParent == null) { // we are back at the top-level parse expression
      assert(depth == 0, "depth != 0 as expected")
      val result = ParsedItem.toParsedNode(stack.top)
      println(s"${result.toShortString()}")
    }
  }

  override def visitTerminal(node: TerminalNode): Unit = {
    stack.top match {
      case mpn: MutableParsedNode =>
        mpn.parseItems.addOne(ParsedValue(index, depth, node.getText))
        index += 1
      case _ => assert(false, "expected to find MutableParsedNode on top of stack")
    }
  }

  override def visitErrorNode(node: ErrorNode): Unit = {}
}
