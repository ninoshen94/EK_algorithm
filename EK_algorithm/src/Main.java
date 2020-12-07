import java.util.*;

class Main{
    public static void main(String[] args) {
        // Network matrix example.
        int[][] t = {{0,  10, 12, 12, 0,  0,  0,  0,  0},
                     {-10,0,  8,  0,  10, 0,  0,  0,  0},
                     {-12,-8, 0,  -8, -5, 6,  0,  0,  0},
                     {-12,0,  8,  0,  0,  10, 5,  0,  0},
                     {0,  -10,5,  0,  0,  0,  8,  7,  0},
                     {0,  0,  -6, -10,0,  0,  0,  13, 0},
                     {0,  0,  0,  -5, -8, 0,  0,  0,  19},
                     {0,  0,  0,  0,  -7, -13,0,  0,  12},
                     {0,  0,  0,  0,  0,  0,  -19,-12,0}};
        Digraph test;

        // Be sure to handle the problem when creating an instance of Digraph.
        try{
             test = new Digraph(t);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return;
        }

        // Get the graph by generateGraph() method.
        Node root = test.generateGraph();

        // Create a instance of EK class, which contains the core algorithm.
        EK ek = new EK();

        // Return the value of the max flow function.
        int count = ek.getValueOfMaxFlowFunction(root);
        System.out.println(count);

        // Output the max flow function is still under construction now.
    }
}