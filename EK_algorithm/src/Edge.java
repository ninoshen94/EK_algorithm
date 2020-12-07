class Edge{
    public Node from;
    public Node to;
    public int capacity;

    public Edge(int capacity) {
        this.capacity = capacity;
    }

    public Edge(Node from, Node to, int capacity) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
    }
}