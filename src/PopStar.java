import java.util.ArrayList;

public class PopStar extends Creature {
    public PopStar(String room, Player p, Graph g) {
        super(room, p, g);
    }

    @Override
    protected String move() {
        if (player.getCurrentRoom().equals(currentRoom)){
            return null;
        }
        ArrayList<String> adjacentRooms = graph.getNode(currentRoom).getNeighbors();
        if (graph.getNode(player.getCurrentRoom()).getNeighbor(currentRoom) != null) {
            return player.getCurrentRoom();
        } else {
            if (adjacentRooms.size() > 0) {
                int random = (int) (adjacentRooms.size() * Math.random());
                return adjacentRooms.get(random);
            }
        }
        return null;
    }
}
