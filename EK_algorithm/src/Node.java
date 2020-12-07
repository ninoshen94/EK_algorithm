import java.util.ArrayList;
import java.util.List;

class Node {
    public List<Edge> edges;
    public String name;

    public Node(String name) {
        this.edges = new ArrayList<>();
        this.name = name;
    }
}