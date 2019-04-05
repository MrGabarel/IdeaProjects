import java.util.ArrayList;

public class Wumpus extends Creature{
    public Wumpus(String currentRoom, Player player, Graph graph) {
        super(currentRoom, player, graph);
    }

    @Override
    protected String move() {
        if (graph.getNode(player.getCurrentRoom()).getNeighbor(currentRoom) != null){
            ArrayList<String> adjacentRooms = new ArrayList<>(graph.getNode(currentRoom).getNeighbors());
            adjacentRooms.remove(player.getCurrentRoom());
            if (adjacentRooms.size() > 0) {
                int random = (int) (adjacentRooms.size() * Math.random());
                return adjacentRooms.get(random);
            }
        }
        return null;
    }
}
