package gragh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CloneGraph {
    Map<Node,Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        return dfs(node);
    }

    private Node dfs(Node node) {
        if (map.containsKey(node))
            return map.get(node);
        else {
            Node copy = new Node(node.val, new ArrayList<>());
            map.put(node, copy);
            for (Node n : node.neighbors) {
                dfs(n);
                copy.neighbors.add(n);
            }
            return copy;
        }
    }
}
