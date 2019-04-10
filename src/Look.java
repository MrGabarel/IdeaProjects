public class Look extends Command {
    @Override
    protected void init(String input) {
        return;
    }

    @Override
    public boolean execute() {
        System.out.println(graph.getNode(player.getCurrentRoom()).getNeighborsNameAndDescriptions());
        return (graph.getNode(player.getCurrentRoom()).getNeighborsNameAndDescriptions().equals(""));
    }

    public Look(Graph g, Player p) {
        super(g, p);
    }
}
