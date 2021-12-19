import com.contakt.scala.antlr.grammar.{MiniModelLexer, MiniModelParser}
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream, TokenStream}
import org.contakt.scala.antlr.minimodel._
import org.junit.Assert
import org.junit.runner.RunWith
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MiniModelBuilderSpec extends AnyFlatSpec with should.Matchers {

val animalModel = """
    |model{
    |  class Animalia {
    |    property zebra: Animal[2..5];
    |    property lion: Animal[1];
    |  }
    |}
    |""".stripMargin

  "A mini model builder" should "be able to build a mini model expression" in {
    val lexer = new MiniModelLexer(CharStreams.fromString(animalModel))
    val tokens: TokenStream = new CommonTokenStream(lexer)
    val parser = new MiniModelParser(tokens)
    val expected =
      Model(
        List(
          ClassDeclaration(
            "Animalia",
            List(
              PropertyDeclaration("zebra", "Animal", Some("2..5")),
              PropertyDeclaration("lion", "Animal", Some("1"))
            )
          )
        )
      )
    Assert.assertEquals(MiniModelBuilder(parser).model, expected)
  }

}