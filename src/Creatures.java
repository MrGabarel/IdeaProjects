public class Creatures extends Command {
    @Override
    protected void init(String input) {
        return;
    }

    @Override
    public boolean execute() {
        graph.getNode(player.getCurrentRoom()).displayCreatures();
        return true;
    }

    public Creatures(Graph g, Player p) {
        super(g, p);
    }
}
