// Generated from C:/Users/tony/OneDrive/Projects/antlr-scala/src/main/antlr/com/contakt/scala/antlr/grammar\MiniModel.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MiniModelParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MiniModelVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MiniModelParser#model}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModel(MiniModelParser.ModelContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniModelParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(MiniModelParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniModelParser#class_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_declaration(MiniModelParser.Class_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniModelParser#property_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperty_declaration(MiniModelParser.Property_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniModelParser#multiplicity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicity(MiniModelParser.MultiplicityContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniModelParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(MiniModelParser.NameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniModelParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(MiniModelParser.TypeContext ctx);
}