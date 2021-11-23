// Generated from C:/Users/tony/OneDrive/Projects/antlr-scala/src/main/antlr/com/contakt/scala/antlr/grammar\MiniModel.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MiniModelParser}.
 */
public interface MiniModelListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MiniModelParser#model}.
	 * @param ctx the parse tree
	 */
	void enterModel(MiniModelParser.ModelContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniModelParser#model}.
	 * @param ctx the parse tree
	 */
	void exitModel(MiniModelParser.ModelContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniModelParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(MiniModelParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniModelParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(MiniModelParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniModelParser#class_declaration}.
	 * @param ctx the parse tree
	 */
	void enterClass_declaration(MiniModelParser.Class_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniModelParser#class_declaration}.
	 * @param ctx the parse tree
	 */
	void exitClass_declaration(MiniModelParser.Class_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniModelParser#property_declaration}.
	 * @param ctx the parse tree
	 */
	void enterProperty_declaration(MiniModelParser.Property_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniModelParser#property_declaration}.
	 * @param ctx the parse tree
	 */
	void exitProperty_declaration(MiniModelParser.Property_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniModelParser#multiplicity}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicity(MiniModelParser.MultiplicityContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniModelParser#multiplicity}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicity(MiniModelParser.MultiplicityContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniModelParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(MiniModelParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniModelParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(MiniModelParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniModelParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(MiniModelParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniModelParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(MiniModelParser.TypeContext ctx);
}