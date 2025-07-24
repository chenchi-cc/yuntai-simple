package com.darrenchan.government.service;

import com.darrenchan.government.bean.SourceTarget;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.GraphDatabase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//对neo4j的操作放在这里
public class TableLineageService {
    // 根据 Dolphin Scheduler 调度过的Hive任务，来计算Hive中表的血缘关系
    private static final String[] sqls = {
            "INSERT INTO gmall.tableOne SELECT id, name FROM gmall.tableTwo JOIN gmall.tableThree ON gmall.tableTwo.id = gmall.tableThree.id",
            "INSERT INTO gmall.tableTwo SELECT id, name FROM gmall.tableFour JOIN gmall.tableFive JOIN gmall.tableSix ON gmall.tableFour.id = gmall.tableFive.id AND gmall.tableFive.pid = gmall.tableSix.pid"
    };


    // 最终计算出来的血缘图中的所有边
    public static List<SourceTarget> generateEdges() {
        // 用来去重，已经添加的边不再添加
        var edgeSet = new HashSet<String>();
        var edges = new ArrayList<SourceTarget>();
        for (var sql : sqls) {
            //做表级血缘关系分析
            var list = TableLineage.lineage(sql);
            for (var st : list) {
                if (!edgeSet.contains(st.source() + " --> " + st.target())) {
                    edgeSet.add(st.source() + " --> " + st.target());
                    edges.add(st);
                }
            }
        }
        return edges;
    }

    // 将计算出来的血缘图的所有边保存到`neo4j`图数据库
    // 持久化
    public static void sinkToNeo4j(List<SourceTarget> edges) {
        // 创建一个集合类型，用来保存已经添加过的节点
        var nodes = new HashSet<String>();
        var driver = GraphDatabase.driver(
                "bolt://localhost:7687",
                AuthTokens.basic(
                        "neo4j",
                        "chen!!1992062812"
                )
        );
        // 创建一个会话（连接）
        var session = driver.session();
        session.executeWriteWithoutResult(tx -> {
            // 删除neo4j中所有的节点以及它们的边
            tx.run("MATCH (n) DETACH DELETE n");
            // 遍历所有的边，将节点和边添加到`neo4j`图数据库中
            for (var edge : edges) {
                // 将边的source节点添加到neo4j中
                if (!nodes.contains(edge.source())) {
                    nodes.add(edge.source());
                    tx.run("CREATE (n:Table {tableName: '" + edge.source() + "'})");
                }
                // 将边的target节点添加到neo4j中
                if (!nodes.contains(edge.target())) {
                    nodes.add(edge.target());
                    tx.run("CREATE (n:Table {tableName: '" + edge.target() + "'})");
                }
                // 将source --> target边添加到neo4j中
                tx.run("MATCH" +
                        "  (a:Table)," +
                        "  (b:Table)" +
                        "  WHERE a.tableName = '" + edge.source() + "' AND b.tableName = '" + edge.target() + "'" +
                        "  CREATE (a)-[r:OUTPUT]->(b)");
            }
        });

        session.close();
        driver.close();
    }

    public static void main(String[] args) {
        sinkToNeo4j(generateEdges());
    }
}
