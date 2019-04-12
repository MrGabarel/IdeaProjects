public class Add extends Command {
    String roomName;
    @Override
    protected void init(String input) {
        roomName = input;
    }

    @Override
    public boolean execute() {
        graph.addNode(roomName, "");
        graph.addUndirectedEdge(roomName, player.getCurrentRoom());
        return true;
    }

    public Add(Graph g, Player p) {
        super(g, p);
    }
}
