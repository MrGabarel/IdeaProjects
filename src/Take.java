public class Take extends Command {
    String item;
    @Override
    protected void init(String input) {
        item = input;
    }

    @Override
    public boolean execute() {
        if (player.getItems().get(item) != null){
            player.addItem(item, graph.getNode(player.getCurrentRoom()).removeItem(item));
            return true;
        } else {
            System.out.println("That item doesn't exist in the room");
            return false;
        }
    }

    public Take(Graph g, Player p) {
        super(g,p);
    }
}
