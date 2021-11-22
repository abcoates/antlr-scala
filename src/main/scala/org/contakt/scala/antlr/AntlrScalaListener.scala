package org.contakt.scala.antlr

import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.TerminalNode
import com.contakt.scala.antlr.grammar.ArithmeticBaseListener
import scala.collection.mutable.{ListBuffer,Stack}

class AntlrScalaListener extends ArithmeticBaseListener {

  val stack = Stack[ListBuffer[AnyRef]]()

  override def enterEveryRule(ctx: ParserRuleContext): Unit = {
    super.enterEveryRule(ctx)
    stack.push(ListBuffer[AnyRef]())
  }

  override def exitEveryRule(ctx: ParserRuleContext): Unit = {
    super.exitEveryRule(ctx)
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
      val result: Option[AnyRef] =
          if (stack.top.isEmpty) {
            None
          } else {
            Some(stack.top.toList.head)
          }
      println(s"$result")
    }
  }

  override def visitTerminal(node: TerminalNode): Unit = {
    super.visitTerminal(node)
    stack.top.addOne(node.getText)
  }

}

object AntlrScalaListener {
  def resultToString(result: AnyRef): String = {
    result match {
      case None => "None"
      case Some(x) => s"Some(${resultToString(x.asInstanceOf[AnyRef])})"
      case "" => "\"\""
      case str:String => s"\"$str\""
      case head::tail => s"[${resultToString(head.asInstanceOf[AnyRef])},${resultToString(tail)}]"
      case nil => "[]"
      case _ => s"{${result.toString}}"
    }
  }
}