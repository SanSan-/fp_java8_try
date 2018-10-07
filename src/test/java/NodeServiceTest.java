import dao.Node;
import dao.NodeTree;
import org.junit.Test;
import services.NodeService;

import java.util.Arrays;
import java.util.Collections;

import static junit.framework.Assert.assertEquals;

public class NodeServiceTest {

    private final NodeService nodeService = new NodeService();

    @Test
    public void copyNode() {
        Node excepted = new Node(18, new Node(23, new Node(54, new Node(1, null))));
        System.out.println("Node.excepted = " + excepted.toString());
        Node actual = nodeService.copy(excepted);
        System.out.println("Node.actual = " + actual.toString());
        assertEquals(actual, excepted);
    }

    @Test
    public void copyNodeTree() {
        NodeTree excepted = new NodeTree(1, Arrays.asList(
                new NodeTree(2, null),
                new NodeTree(3, Arrays.asList(
                        new NodeTree(5, null),
                        new NodeTree(6, null),
                        new NodeTree(7, null)
                )),
                new NodeTree(4, Collections.singletonList(
                        new NodeTree(8, null)
                ))));
        System.out.println("NodeTree.excepted = " + excepted);
        NodeTree actual = nodeService.copy(excepted);
        System.out.println("NodeTree.actual = " + actual);
        assertEquals(excepted, actual);
    }
}
