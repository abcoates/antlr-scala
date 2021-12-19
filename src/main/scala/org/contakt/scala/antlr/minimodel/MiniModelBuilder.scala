// Hand-coded data model to match 'MiniModel.g4'

package org.contakt.scala.antlr.minimodel

import com.contakt.scala.antlr.grammar.MiniModelParser
import scala.jdk.CollectionConverters._

case class PropertyDeclaration(propertyName: String, propertyType: String, multiplicity: Option[String])

sealed trait Declaration

case class ClassDeclaration(className: String, properties: List[PropertyDeclaration]) extends Declaration

case class Model(declarations: List[Declaration])

class MiniModelBuilder(parser: MiniModelParser) {

  def buildModel(propertyDeclaration: MiniModelParser.Property_declarationContext): PropertyDeclaration = {
    PropertyDeclaration(
      propertyDeclaration.name().getText(),
      propertyDeclaration.`type`().getText(),
      propertyDeclaration.multiplicity() match {
        case null => None
        case mult => Some(mult.NUMBER().asScala.mkString(".."))
      }
    )
  }

  def buildModel(classDeclaration: MiniModelParser.Class_declarationContext): ClassDeclaration = {
    ClassDeclaration(
      classDeclaration.name().getText(),
      classDeclaration.property_declaration().asScala.map(buildModel).toList
    )
  }

  def buildModel(declaration: MiniModelParser.DeclarationContext): Declaration = {
    buildModel(declaration.class_declaration())
  }

  def buildModel(model: MiniModelParser.ModelContext): Model = {
    Model(
      model.declaration().asScala.map(buildModel).toList.asInstanceOf[List[Declaration]]
    )
  }

  def buildModel(parser: MiniModelParser): Model = {
    buildModel(parser.model())
  }

  lazy val model: Model = buildModel(parser)

}

object MiniModelBuilder {
  def apply(parser: MiniModelParser): MiniModelBuilder = { new MiniModelBuilder(parser) }
}
