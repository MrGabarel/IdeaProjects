public class Items extends Command {
    @Override
    protected void init(String input) {
        return;
    }

    @Override
    public boolean execute() {
        System.out.println(graph.getNode(player.getCurrentRoom()).getItemNames());
        return (!graph.getNode(player.getCurrentRoom()).getItemNames().equals(""));
    }

    public Items(Graph g, Player p) {
        super(g, p);
    }
}
