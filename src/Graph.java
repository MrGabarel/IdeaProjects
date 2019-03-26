import java.util.HashMap;

public class Graph {
    public static HashMap<String, Node> nodes;

    public Graph(){
        nodes = new HashMap<>();
    }

    public static HashMap<String, Node> getNodes() {
        return nodes;
    }

    public void addNode(String name, String description) {
        nodes.put(name, new Node(description));
    }
    public void addDirectedEdge(String name1, String name2){
        nodes.get(name1).addNeighbor(name2);
    }
    public void addUndirectedEdge(String name1, String name2){
        addDirectedEdge(name1, name2);
        addDirectedEdge(name2, name2);
    }
    public static Node getNode(String name){
        return nodes.get(name);
    }
    public class Node {
        private HashMap<String, Node> neighbors;
        private String description;
        Node(String description){
            neighbors = new HashMap<>();
            this.description = description;
        }
        private void addNeighbor(String name){
            neighbors.put(name, nodes.get(name));
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getNeighborsNameAndDescriptions(){
            String output = "";
            for (String name: neighbors.keySet())
                output = output + ", " + name + " - " + neighbors.get(name).description;
            if (output.length() > 0)
                return output.substring(2);
            return output;
        }
        public Node getNeighbor(String nodeName){
            return neighbors.get(nodeName);
        }
    }
}
