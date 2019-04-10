public class Take extends Command {
    String item;
    @Override
    protected void init(String input) {
        item = input;
    }

    @Override
    public boolean execute() {
        return (graph.getNode(player.getCurrentRoom()).removeItem(item) != null);
    }

    public Take(Graph g, Player p) {
        super(g,p);
    }
}
