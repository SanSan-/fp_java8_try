package services;

import dao.Node;
import dao.NodeTree;

import java.util.stream.Collectors;

public class NodeService {

    public Node copy(Node n) {
        return n == null ? null : new Node(n.getValue(), copy(n.getNext()));
    }

    public NodeTree copy(NodeTree n) {
        return n == null ? null : new NodeTree(n.getValue(), n.getNext() == null ? null : n.getNext().stream().map(this::copy).collect(Collectors.toList()));
    }

}
