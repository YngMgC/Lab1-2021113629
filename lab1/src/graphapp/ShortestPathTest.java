package graphapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;

public class ShortestPathTest {
    private TextToGraph textGraph;

    @BeforeEach
    void setUp() throws IOException {
        // 初始化WordGraph实例并构建图
        textGraph = new TextToGraph();
        // 假设buildGraph方法接受一个字符串并构建词图
        textGraph.TextchangetoGraph("explore strange new worlds to seek out new life and new mind and");
        // Graphviz.showDirectedGraph(wordGraph);
        // wordGraph.buildGraph("");
    }

    // 测试用例1: 两个词都存在于词图中
    @Test
    void testShortestPath_BothWordsExist() {
        String result = textGraph.calcShortestPath("explore", "new");
        System.out.println(result);
        assertEquals("Shortest path from \"explore\" to \"new\": explore -> strange -> new (Total weight: 2)", result);
    }

    // 测试用例2: 词1存在于词图中，词2不存在
    @Test
    void testShortestPath_Word1Exists_Word2NotExist() {
        String result = textGraph.calcShortestPath("out", "unknown");
        System.out.println(result);
        assertEquals("There is no \"unknown\" in the graph!", result);
    }

    // 测试用例3: 词1不存在词图中，词2存在
    @Test
    void testShortestPath_Word1NotExist_Word2Exists() {
        String result = textGraph.calcShortestPath("unknown", "mind");
        System.out.println(result);
        assertEquals("There is no \"unknown\" in the graph!", result);
    }

    // 测试用例4: 边界值 - 词图为空
    @Test
    void testShortestPath_NoneWordExists() {
        String result = textGraph.calcShortestPath("unknown1", "unknown2");
        System.out.println(result);
        assertEquals("There are no \"unknown1\" and \"unknown2\" in the graph!", result);
    }

    // 测试用例5: 边界值 - 词1和词2是同一个词
    @Test
    void testShortestPath_SameWord(){
        String result = textGraph.calcShortestPath("new", "new");
        System.out.println(result);
        assertEquals("Shortest path from \"new\" to \"new\": new (Total weight: 0)", result);
    }
    @Test
        // 测试用例6: 边界值 - 词1与词2无连接
    void testShortestPath_NoPath(){
        String result = textGraph.calcShortestPath("out", "explore");
        System.out.println(result);
        assertEquals("From \"out\" to \"explore\" there is no path !", result);
    }
    // 测试用例8: 边界值 - 词1和词2通过多个词间接相连
    @Test
    void testShortestPath_MultiPaths(){
        String result = textGraph.calcShortestPath("new", "and");
        System.out.println(result);
        assertEquals("Shortest path from \"new\" to \"and\": new -> mind -> and (Total weight: 2)", result);
    }
}
