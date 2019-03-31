import java.util.HashMap;

public class Graph {
    public static HashMap<String, Node> nodes;

    public Graph() {
        nodes = new HashMap<>();
    }

    public static HashMap<String, Node> getNodes() {
        return nodes;
    }

    public void addNode(String name, String description) {
        nodes.put(name, new Node(description));
    }

    public void addDirectedEdge(String name1, String name2) {
        nodes.get(name1).addNeighbor(name2);
    }

    public void addUndirectedEdge(String name1, String name2) {
        addDirectedEdge(name1, name2);
        addDirectedEdge(name2, name1);
    }

    public static Node getNode(String name) {
        return nodes.get(name);
    }

    public class Node {
        private HashMap<String, Node> neighbors;
        private HashMap<String, Item> items;
        private String description;

        Node(String description) {
            neighbors = new HashMap<>();
            items = new HashMap<>();
            this.description = description;
        }

        private void addNeighbor(String name) {
            neighbors.put(name, nodes.get(name));
        }

        public void addItem(String name, String description) {
            items.put(name, new Item(description));
        }

        public void addItem(String name) {
            items.put(name, new Item(null));
        }

        public void addItem(String name, Item item) {
            items.put(name, item);
        }

        public boolean destroyItem(String name) {
            return (items.remove(name) != null);
        }

        public Item getItem(String name) {
            return items.get(name);
        }

        public Item removeItem(String name) {
            return items.remove(name);
        }

        public String getItemNames() {
            String output = "";
            for (String name : items.keySet())
                output = output + ", " + name + " - " + items.get(name).getDescription();
            if (output.length() > 0)
                return output.substring(2);
            return output;
        }

        public String getDescription() {
            return description;
        }

        public HashMap<String, Item> getItems() {
            return items;
        }

        public void displayInventory() {
            for (String name : items.keySet()) {
                System.out.println(name + ", ");
            }
            System.out.println("\b\b");
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getNeighborsNameAndDescriptions() {
            String output = "";
            for (String name : neighbors.keySet())
                output = output + ", " + name + " - " + neighbors.get(name).description;
            if (output.length() > 0)
                return output.substring(2);
            return output;
        }

        public Node getNeighbor(String nodeName) {
            return neighbors.get(nodeName);
        }
    }
}
