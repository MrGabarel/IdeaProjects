import java.util.ArrayList;

public class Chicken extends Creature{
    public Chicken(String currentRoom, Player player, Graph graph) {
        super(currentRoom, player, graph);
    }

    @Override
    protected String move() {
        ArrayList<String> adjacentRooms = graph.getNode(currentRoom).getNeighbors();
        if (adjacentRooms.size() > 0) {
            int random = (int) (adjacentRooms.size() * Math.random());
            return adjacentRooms.get(random);
        }
        return null;
    }
}
