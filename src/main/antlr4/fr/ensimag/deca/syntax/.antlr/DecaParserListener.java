// Generated from /home/aitouaka/ensimag/GL/gl31/src/main/antlr4/fr/ensimag/deca/syntax/DecaParser.g4 by ANTLR 4.13.1

    import java.util.*;
    import fr.ensimag.deca.tree.*;
    import fr.ensimag.deca.tools.*;
    import java.io.PrintStream;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DecaParser}.
 */
public interface DecaParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DecaParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(DecaParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(DecaParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#main}.
	 * @param ctx the parse tree
	 */
	void enterMain(DecaParser.MainContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#main}.
	 * @param ctx the parse tree
	 */
	void exitMain(DecaParser.MainContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(DecaParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(DecaParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#list_decl}.
	 * @param ctx the parse tree
	 */
	void enterList_decl(DecaParser.List_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#list_decl}.
	 * @param ctx the parse tree
	 */
	void exitList_decl(DecaParser.List_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#decl_var_set}.
	 * @param ctx the parse tree
	 */
	void enterDecl_var_set(DecaParser.Decl_var_setContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#decl_var_set}.
	 * @param ctx the parse tree
	 */
	void exitDecl_var_set(DecaParser.Decl_var_setContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#list_decl_var}.
	 * @param ctx the parse tree
	 */
	void enterList_decl_var(DecaParser.List_decl_varContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#list_decl_var}.
	 * @param ctx the parse tree
	 */
	void exitList_decl_var(DecaParser.List_decl_varContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#decl_var}.
	 * @param ctx the parse tree
	 */
	void enterDecl_var(DecaParser.Decl_varContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#decl_var}.
	 * @param ctx the parse tree
	 */
	void exitDecl_var(DecaParser.Decl_varContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#list_inst}.
	 * @param ctx the parse tree
	 */
	void enterList_inst(DecaParser.List_instContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#list_inst}.
	 * @param ctx the parse tree
	 */
	void exitList_inst(DecaParser.List_instContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#inst}.
	 * @param ctx the parse tree
	 */
	void enterInst(DecaParser.InstContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#inst}.
	 * @param ctx the parse tree
	 */
	void exitInst(DecaParser.InstContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#if_then_else}.
	 * @param ctx the parse tree
	 */
	void enterIf_then_else(DecaParser.If_then_elseContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#if_then_else}.
	 * @param ctx the parse tree
	 */
	void exitIf_then_else(DecaParser.If_then_elseContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#list_expr}.
	 * @param ctx the parse tree
	 */
	void enterList_expr(DecaParser.List_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#list_expr}.
	 * @param ctx the parse tree
	 */
	void exitList_expr(DecaParser.List_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(DecaParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(DecaParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#assign_expr}.
	 * @param ctx the parse tree
	 */
	void enterAssign_expr(DecaParser.Assign_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#assign_expr}.
	 * @param ctx the parse tree
	 */
	void exitAssign_expr(DecaParser.Assign_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#or_expr}.
	 * @param ctx the parse tree
	 */
	void enterOr_expr(DecaParser.Or_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#or_expr}.
	 * @param ctx the parse tree
	 */
	void exitOr_expr(DecaParser.Or_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#and_expr}.
	 * @param ctx the parse tree
	 */
	void enterAnd_expr(DecaParser.And_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#and_expr}.
	 * @param ctx the parse tree
	 */
	void exitAnd_expr(DecaParser.And_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#eq_neq_expr}.
	 * @param ctx the parse tree
	 */
	void enterEq_neq_expr(DecaParser.Eq_neq_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#eq_neq_expr}.
	 * @param ctx the parse tree
	 */
	void exitEq_neq_expr(DecaParser.Eq_neq_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#inequality_expr}.
	 * @param ctx the parse tree
	 */
	void enterInequality_expr(DecaParser.Inequality_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#inequality_expr}.
	 * @param ctx the parse tree
	 */
	void exitInequality_expr(DecaParser.Inequality_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#sum_expr}.
	 * @param ctx the parse tree
	 */
	void enterSum_expr(DecaParser.Sum_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#sum_expr}.
	 * @param ctx the parse tree
	 */
	void exitSum_expr(DecaParser.Sum_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#mult_expr}.
	 * @param ctx the parse tree
	 */
	void enterMult_expr(DecaParser.Mult_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#mult_expr}.
	 * @param ctx the parse tree
	 */
	void exitMult_expr(DecaParser.Mult_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#unary_expr}.
	 * @param ctx the parse tree
	 */
	void enterUnary_expr(DecaParser.Unary_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#unary_expr}.
	 * @param ctx the parse tree
	 */
	void exitUnary_expr(DecaParser.Unary_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#select_expr}.
	 * @param ctx the parse tree
	 */
	void enterSelect_expr(DecaParser.Select_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#select_expr}.
	 * @param ctx the parse tree
	 */
	void exitSelect_expr(DecaParser.Select_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#primary_expr}.
	 * @param ctx the parse tree
	 */
	void enterPrimary_expr(DecaParser.Primary_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#primary_expr}.
	 * @param ctx the parse tree
	 */
	void exitPrimary_expr(DecaParser.Primary_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(DecaParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(DecaParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(DecaParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(DecaParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#ident}.
	 * @param ctx the parse tree
	 */
	void enterIdent(DecaParser.IdentContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#ident}.
	 * @param ctx the parse tree
	 */
	void exitIdent(DecaParser.IdentContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#list_classes}.
	 * @param ctx the parse tree
	 */
	void enterList_classes(DecaParser.List_classesContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#list_classes}.
	 * @param ctx the parse tree
	 */
	void exitList_classes(DecaParser.List_classesContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#class_decl}.
	 * @param ctx the parse tree
	 */
	void enterClass_decl(DecaParser.Class_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#class_decl}.
	 * @param ctx the parse tree
	 */
	void exitClass_decl(DecaParser.Class_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#class_extension}.
	 * @param ctx the parse tree
	 */
	void enterClass_extension(DecaParser.Class_extensionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#class_extension}.
	 * @param ctx the parse tree
	 */
	void exitClass_extension(DecaParser.Class_extensionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#class_body}.
	 * @param ctx the parse tree
	 */
	void enterClass_body(DecaParser.Class_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#class_body}.
	 * @param ctx the parse tree
	 */
	void exitClass_body(DecaParser.Class_bodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#decl_field_set}.
	 * @param ctx the parse tree
	 */
	void enterDecl_field_set(DecaParser.Decl_field_setContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#decl_field_set}.
	 * @param ctx the parse tree
	 */
	void exitDecl_field_set(DecaParser.Decl_field_setContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#visibility}.
	 * @param ctx the parse tree
	 */
	void enterVisibility(DecaParser.VisibilityContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#visibility}.
	 * @param ctx the parse tree
	 */
	void exitVisibility(DecaParser.VisibilityContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#list_decl_field}.
	 * @param ctx the parse tree
	 */
	void enterList_decl_field(DecaParser.List_decl_fieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#list_decl_field}.
	 * @param ctx the parse tree
	 */
	void exitList_decl_field(DecaParser.List_decl_fieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#decl_field}.
	 * @param ctx the parse tree
	 */
	void enterDecl_field(DecaParser.Decl_fieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#decl_field}.
	 * @param ctx the parse tree
	 */
	void exitDecl_field(DecaParser.Decl_fieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#decl_method}.
	 * @param ctx the parse tree
	 */
	void enterDecl_method(DecaParser.Decl_methodContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#decl_method}.
	 * @param ctx the parse tree
	 */
	void exitDecl_method(DecaParser.Decl_methodContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#list_params}.
	 * @param ctx the parse tree
	 */
	void enterList_params(DecaParser.List_paramsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#list_params}.
	 * @param ctx the parse tree
	 */
	void exitList_params(DecaParser.List_paramsContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#multi_line_string}.
	 * @param ctx the parse tree
	 */
	void enterMulti_line_string(DecaParser.Multi_line_stringContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#multi_line_string}.
	 * @param ctx the parse tree
	 */
	void exitMulti_line_string(DecaParser.Multi_line_stringContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(DecaParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(DecaParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link DecaParser#array_element_access}.
	 * @param ctx the parse tree
	 */
	void enterArray_element_access(DecaParser.Array_element_accessContext ctx);
	/**
	 * Exit a parse tree produced by {@link DecaParser#array_element_access}.
	 * @param ctx the parse tree
	 */
	void exitArray_element_access(DecaParser.Array_element_accessContext ctx);
}