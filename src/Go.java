public class Go extends Command{
    String room;
    public Go(Graph graph, Player player) {
        super(graph, player);
    }

    @Override
    protected void init(String input) {
        room = input;
    }

    @Override
    public boolean execute() {
        boolean success = player.moveToRoom(room);
        return success;
    }
}
