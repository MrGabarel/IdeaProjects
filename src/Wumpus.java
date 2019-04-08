import java.util.ArrayList;

public class Wumpus extends Creature{
    public Wumpus(String currentRoom, Player player, Graph graph) {
        super(currentRoom, player, graph);
    }

    @Override
    protected String move() {
        ArrayList<String> adjacentRooms = new ArrayList<>(graph.getNode(currentRoom).getNeighbors());
        String theWay = this.getRoomSightRange(roomSightRange, currentRoom, player.getCurrentRoom());
        if (theWay != null){
            adjacentRooms.remove(theWay);
            if (adjacentRooms.size() > 0) {
                int random = (int) (adjacentRooms.size() * Math.random());
                return adjacentRooms.get(random);
            }
        }
        return null;
    }
}
