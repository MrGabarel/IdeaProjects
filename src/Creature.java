public abstract class Creature {
    protected String currentRoom;
    protected Player player;
    protected Graph graph;
    protected int roomSightRange = 2;

    public Creature(String currentRoom, Player player, Graph graph) {
        this.currentRoom = currentRoom;
        this.player = player;
        this.graph = graph;
    }

    public String getCurrentRoom() {
        return currentRoom;
    }
    public void act(){
        updateLocation(move());
    }



    protected void updateLocation(String newLocation){
        if (newLocation != null) {
            graph.getNode(currentRoom).removeCreature(this);
            currentRoom = newLocation;
            graph.getNode(newLocation).addCreature(this);
        }
    }

    //If a room is within N rooms of the creature's current room return the name of the room that leads to it
    protected String getRoomSightRange(int n, String initialRoom, String targetRoom){
        if (n == 1){
            if (graph.getNode(initialRoom).getNeighbor(targetRoom) != null) {
                return initialRoom;
            } else {
                return null;
            }
        } else {
            for (String room : graph.getNode(initialRoom).getNeighbors()) {
                if (room.equals(targetRoom))return room;
                String result = getRoomSightRange(n - 1, room, targetRoom);
                if (result != null) return result;
            }
        }
        return null;
    }

    protected abstract String move();
}
