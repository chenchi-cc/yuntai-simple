package com.darrenchan.government.service;

import com.darrenchan.government.bean.SourceTarget;
import com.darrenchan.government.sqlparser.SQLBaseVisitor;
import com.darrenchan.government.sqlparser.SQLLexer;
import com.darrenchan.government.sqlparser.SQLParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.ArrayList;
import java.util.List;

public class TableLineage extends SQLBaseVisitor<Object> {
    private final List<String> inputTables = new ArrayList<>(); // 输入表列表
    private String outputTable; // 输出表

    @Override
    public Object visitInsertStatement(SQLParser.InsertStatementContext ctx) {
        //提取输出表的表名
        outputTable = ctx.T_QIDENTIFIER().getText();
        return super.visitInsertStatement(ctx);
    }

    @Override
    public Object visitFromClause(SQLParser.FromClauseContext ctx) {
        //提取输入表的表名
        inputTables.add(ctx.T_QIDENTIFIER().getText());
        return super.visitFromClause(ctx);
    }

    @Override
    public Object visitJoinClause(SQLParser.JoinClauseContext ctx) {
        //提取输入表的表名
        inputTables.add(ctx.T_QIDENTIFIER().getText());
        return super.visitJoinClause(ctx);
    }

    //所有源表到目标表的边的集合
    public static List<SourceTarget> lineage(String sql) {
        var stream = CharStreams.fromString(sql);
        var lexer = new SQLLexer(stream);  // 词法分析器
        var tokens = new CommonTokenStream(lexer);  // 词法分析器
        var parser = new SQLParser(tokens);  // 语法分析器
        var ast = parser.statement();
        var tableLineage = new TableLineage();
        tableLineage.visit(ast);

        var edges = new ArrayList<SourceTarget>();
        for (var source : tableLineage.inputTables) {
            edges.add(new SourceTarget(source, tableLineage.outputTable));
        }

        System.out.println(edges);
        return edges;
    }

    public static void main(String[] args) {
        String sql = "INSERT INTO gmall.tableOne SELECT id FROM gmall.tableTwo";
        lineage(sql);
    }
}
