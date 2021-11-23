package org.contakt.scala.antlr

import scala.collection.immutable.List
import scala.collection.mutable.ListBuffer

sealed trait ParsedItem {
  def toShortString(): String
  def isEmpty(): Boolean
  def size(): Int
}

case class ParsedValue(val index: Int, val depth: Int, val token: String) extends ParsedItem {
  override def toShortString(): String = token
  override def isEmpty(): Boolean = false
  override def size(): Int = 1
}
case class ParsedNode(val index: Int, val depth: Int, val parseItems: List[ParsedItem]) extends ParsedItem {
  override def toShortString(): String = {
    val itemsString: String = parseItems.map(item => item.toShortString()).mkString(", ")
    s"[$index@$depth|$itemsString]"
  }
  override def isEmpty(): Boolean = parseItems.isEmpty
  override def size(): Int = parseItems.size
}

case class MutableParsedNode(val index: Int, val depth: Int, val parseItems: ListBuffer[ParsedItem]) extends ParsedItem {
  override def toShortString(): String = {
    val itemsString: String = parseItems.toList.map(item => item.toShortString()).mkString(", ")
    s"[$index@$depth|$itemsString]"
  }
  override def isEmpty(): Boolean = parseItems.isEmpty
  override def size(): Int = parseItems.size
}

object ParsedItem {
  def toParsedNode(x: ParsedItem): ParsedItem = {
    x match {
      case x: MutableParsedNode => ParsedNode(x.index, x.depth, x.parseItems.toList.map(toParsedNode))
      case _ => x
    }
  }
}
