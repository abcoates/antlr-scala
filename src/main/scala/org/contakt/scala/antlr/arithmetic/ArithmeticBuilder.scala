// Hand-coded data model to match 'Arithmetic.g4'
// Takes advantage of the fact that the grammar has already enforced any required operator precedence.

package org.contakt.scala.antlr.arithmetic

import com.contakt.scala.antlr.grammar.ArithmeticParser

sealed trait Expression {}

case class Number(number: Int) extends Expression

case class Addition(expr1: Expression, expr2: Expression) extends Expression

case class Subtraction(expr1: Expression, expr2: Expression) extends Expression

case class Multiplication(expr1: Expression, expr2: Expression) extends Expression

case class Division(expr1: Expression, expr2: Expression) extends Expression

case class UnknownOperator(op: String, expr1: Expression, expr2: Expression) extends Expression

case class Arithmetic(expr: Option[Expression])

class ArithmeticBuilder(parser: ArithmeticParser) {
  private def buildExpression(expr: ArithmeticParser.ExprContext): Expression = {
    expr match {
      case number: ArithmeticParser.NumberContext => Number(number.NUMBER().getSymbol.getText.toInt)
      case addsub: ArithmeticParser.AdditionOrSubtractionContext =>
        addsub.ADDSUB().getSymbol.getText match {
          case "+" =>
            Addition(buildExpression(addsub.expr(0)), buildExpression(addsub.expr(1)))
          case "-" =>
            Subtraction(buildExpression(addsub.expr(0)), buildExpression(addsub.expr(1)))
          case op =>
            UnknownOperator(op, buildExpression(addsub.expr(0)), buildExpression(addsub.expr(1)))
        }
      case muldiv: ArithmeticParser.MultiplicationOrDivisionContext =>
        muldiv.MULDIV().getSymbol.getText match {
          case "*" =>
            Multiplication(buildExpression(muldiv.expr(0)), buildExpression(muldiv.expr(1)))
          case "/" =>
            Division(buildExpression(muldiv.expr(0)), buildExpression(muldiv.expr(1)))
          case op =>
            UnknownOperator(op, buildExpression(muldiv.expr(0)), buildExpression(muldiv.expr(1)))
        }
      case bracketed: ArithmeticParser.BracketExpressionContext => buildExpression(bracketed.expr())
    }
  }

  private def buildExpression(parser: ArithmeticParser): Option[Expression] = {
    val expr = parser.expr()
    if (expr == null) {
      None
    } else {
      Some(buildExpression(expr))
    }
  }

  lazy val expression: Option[Expression] = buildExpression(parser)
}

object ArithmeticBuilder {
  def apply(parser: ArithmeticParser): ArithmeticBuilder = { new ArithmeticBuilder(parser) }
}
