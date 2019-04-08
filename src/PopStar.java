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
        String theWay = this.getRoomSightRange(roomSightRange, currentRoom, player.getCurrentRoom());
        if (theWay != null) {
            return theWay;
        } else {
            if (adjacentRooms.size() > 0) {
                int random = (int) (adjacentRooms.size() * Math.random());
                return adjacentRooms.get(random);
            }
        }
        return null;
    }
}
