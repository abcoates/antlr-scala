import com.contakt.scala.antlr.grammar.{ArithmeticLexer, ArithmeticParser}
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream, TokenStream}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner
import org.contakt.scala.antlr.arithmetic._
import org.junit.Assert

@RunWith(classOf[JUnitRunner])
class ArithmeticBuilderSpec extends AnyFlatSpec with should.Matchers {

  "An arithmetic builder" should "be able to build a case class expresion" in {
    val lexer = new ArithmeticLexer(CharStreams.fromString("1*2+3*4+(5+6)*(7+8)"))
    val tokens: TokenStream = new CommonTokenStream(lexer)
    val parser = new ArithmeticParser(tokens)
    val expected = Some(
      Addition(
        Addition(
          Multiplication(Number(1), Number(2)),
          Multiplication(Number(3), Number(4))
        ),
        Multiplication(
          Addition(Number(5), Number(6)),
          Addition(Number(7), Number(8))
        )
      )
    )
    Assert.assertEquals(ArithmeticBuilder(parser).expression, expected)
  }

}
