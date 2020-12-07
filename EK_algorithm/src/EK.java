import java.util.*;

public class EK {
    public EK() {
    }

    public static int getValueOfMaxFlowFunction(Node root){
        int maxFlow = 0;
        int delta = 1;
        while(true){
            Map<Node, Node> bfs = BFS(root);
            if(bfs == null){
                break;
            }
            Stack<Node> path = backTrack(bfs);
            delta = update(root, path);
            maxFlow += delta;
        }
        return maxFlow;
    }

    private static int update(Node root, Stack<Node> path){
        List<Node> record = new ArrayList<>();
        int delta = Integer.MAX_VALUE;
        Node previous = path.pop();
        record.add(previous);
        while(!path.isEmpty()){
            Node next = path.pop();
            record.add(next);
            for(Edge e : previous.edges){
                if(e.to == next){
                    delta = Math.min(delta, e.capacity);
                    break;
                }
            }
            previous = next;
        }

        for(int i = 0; i < record.size() - 1; i++){
            Node current = record.get(i);
            Node next = record.get(i + 1);
            for(int j = 0; j < current.edges.size(); j++){
                if(current.edges.get(j).to == next){
                    if(current.edges.get(j).capacity != delta){
                        current.edges.get(j).capacity -= delta;
                    } else {
                        current.edges.remove(j);
                    }
                    break;
                }
            }
            boolean hasEdge = false;
            for(int j = 0; j < next.edges.size(); j++){
                if(next.edges.get(j).to == current){
                    next.edges.get(j).capacity += delta;
                    hasEdge = true;
                    break;
                }
            }
            if(!hasEdge){
                next.edges.add(new Edge(next, current, delta));
            }
        }

        return delta;
    }

    private static Map<Node, Node> BFS(Node root){
        Queue<Node> nodes = new LinkedList<>();
        Map<Node, Node> seen = new HashMap<>();
        nodes.offer(root);
        seen.put(root, null);

        while(!nodes.isEmpty()){
            Node current = nodes.poll();
            if (current.name.equals("sink")){
            }
            for(Edge edge : current.edges) {
                Node next = edge.to;

                if (!seen.containsKey(next)) {
                    nodes.offer(next);
                    seen.put(next, current);
                    if(next.name.equals("sink")){
                        return seen;
                    }
                }
            }
        }

        return null;
    }

    private static Stack<Node> backTrack(Map<Node, Node> record){
        Stack<Node> toReturn = new Stack<>();
        Node end = null;
        for(Node e : record.keySet()){
            if(e.name.equals("sink")){
                end = e;
                break;
            }
        }
        toReturn.push(end);
        while(record.get(toReturn.peek()) != null){
            Node previous = record.get(toReturn.peek());
            toReturn.push(previous);
        }
        return toReturn;
    }
}
