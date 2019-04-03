public abstract class Creature {
    protected String currentRoom;
    protected Player player;
    protected Graph graph;

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
            Graph.getNode(currentRoom).removeCreature(this);
            currentRoom = newLocation;
            Graph.getNode(newLocation).addCreature(this);
        }
    }

    protected abstract String move();
}
