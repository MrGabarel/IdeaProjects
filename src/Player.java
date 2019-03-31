import java.util.HashMap;

public class Player {

    private String name;
    private HashMap<String, Item> items;
    private String description;
    private String currentRoom;
    private Graph graph;

    public Player(String name, String description, Graph g) {
        this.name = name;
        this.description = description;
        items = new HashMap<String, Item>();
        graph = g;
    }

    public Item removeItem(String name){
        return items.remove(name);
    }
    public boolean destroyItem(String name){
        return (items.remove(name) != null);
    }
    public void displayInventory(){
        for (String name: items.keySet()) {
            System.out.println(name + ", ");
        }
        System.out.println("\b\b");
    }
    public boolean moveToRoom(String name){
        if (graph.getNode(currentRoom).getNeighbor(name) != null){
            currentRoom = name;
            return true;
        }
        return false;
    }
    public HashMap<String, Item> getItems() {
        return items;
    }

    public void setItems(HashMap<String, Item> items) {
        this.items = items;
    }

    public String getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void addItem(String name, Item item) {
        items.put(name, item);
    }
}
