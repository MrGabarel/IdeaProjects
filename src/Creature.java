public abstract class Creature {
    protected String currentRoom;
    protected Player player;
    protected Graph graph;
    protected int roomSightRange = 5;

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
    protected String getDirectionToTargetRoom(int n, String initialRoom, String targetRoom){
        String output = "";
        if (graph.getNode(initialRoom).getNeighbor(targetRoom) != null){
            return targetRoom;
        } else if (n == 1){
            return null;
        } else {
            for (String room : graph.getNode(initialRoom).getNeighbors()) {
                String direction = getDirectionToTargetRoom(n - 1, room, targetRoom);
                if (n == roomSightRange) output = output + " " + room;
                else if (direction != null) return room;
            }
        }
        if (n == roomSightRange) return output;
        else return null;
    }

    protected abstract String move();
}
