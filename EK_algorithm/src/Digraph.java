import java.util.ArrayList;
import java.util.List;

public class Digraph {
    public int[][] matrix;
    public int edges;
    public int vertices;

    public Digraph(int[][] matrix) throws Exception{
        checkValidation(matrix);
        this.matrix = matrix;
        this.vertices = matrix.length;
        int count = 0;
        for(int[] e : matrix){
            for(Integer f : e){
                count += f == 0 ? 0 : 1;
            }
        }
        this.edges = count / 2;
    }

    private void checkValidation(int[][] matrix) throws Exception{
        if(matrix.length != matrix[0].length){
            throw new Exception("Invalid Matrix");
        }
        if(matrix.length <= 2){
            throw new Exception("Not a network: Less than 3 vertices");
        }
        int[] source = matrix[0];
        int[] sink = matrix[matrix.length - 1];
        for(Integer e : source){
            if(e < 0){
                throw new Exception("Not a network: Source vertex has in-degree");
            }
        }
        for(Integer e : sink){
            if(e > 0){
                throw new Exception("Not a network: Sink vertex has out-degree");
            }
        }
        for(int i = 0; i < matrix.length; i++){
            for(int j = i; j < matrix.length; j++){
                if(matrix[i][j] != -matrix[j][i]){
                    throw new Exception("Invalid Matrix: index " + String.valueOf(i) + " " + j + " error");
                }
            }
        }
    }

    public Node generateGraph(){
        Node root = new Node("source");
        Node sink = new Node("sink");
        List<Node> nodes = new ArrayList<>();
        nodes.add(root);
        for(int i = 1; i < matrix.length - 1; i++){
            nodes.add(new Node("v" + i));
        }
        nodes.add(sink);

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j++){
                if(i == j || matrix[i][j] <= 0){
                    continue;
                }
                if(matrix[i][j] > 0){
                    Edge temp = new Edge(nodes.get(i), nodes.get(j), matrix[i][j]);
                    nodes.get(i).edges.add(temp);
                }
            }
        }

        return root;
    }
}
