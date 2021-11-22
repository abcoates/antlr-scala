import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream, TokenStream}
import org.antlr.v4.runtime.tree.ParseTreeWalker
import com.contakt.scala.antlr.grammar._
import org.contakt.scala.antlr.AntlrScalaListener

class AntlrScalaListenerSpec extends AnyFlatSpec with should.Matchers {

  "A Scala Antlr listener" should "walk an Antlr tree" in {
    val lexer = new ArithmeticLexer(CharStreams.fromString("1*2+3*4"))
    val tokens: TokenStream = new CommonTokenStream(lexer)
    val parser = new ArithmeticParser(tokens)
    val tree = parser.arithmetic() // parse an arithmetic expression
    val extractor = new AntlrScalaListener()
    ParseTreeWalker.DEFAULT.walk(extractor, tree) // initiate walk of tree with listener in use of default walker
  }

  it should "work for empty expressions" in {
    val lexer = new ArithmeticLexer(CharStreams.fromString(""))
    val tokens: TokenStream = new CommonTokenStream(lexer)
    val parser = new ArithmeticParser(tokens)
    val tree = parser.arithmetic() // parse an arithmetic expression
    val extractor = new AntlrScalaListener()
    ParseTreeWalker.DEFAULT.walk(extractor, tree) // initiate walk of tree with listener in use of default walker
  }

  it should "work for longer expressions" in {
    val lexer = new ArithmeticLexer(CharStreams.fromString("1*2+3*4+(5+6)*(7+8)"))
    val tokens: TokenStream = new CommonTokenStream(lexer)
    val parser = new ArithmeticParser(tokens)
    val tree = parser.arithmetic() // parse an arithmetic expression
    val extractor = new AntlrScalaListener()
    ParseTreeWalker.DEFAULT.walk(extractor, tree) // initiate walk of tree with listener in use of default walker
  }

}
