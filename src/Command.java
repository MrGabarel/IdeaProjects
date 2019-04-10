public abstract class Command {
    Graph graph;
    Player player;
    protected abstract void init(String input);
    public abstract boolean execute();

    public Command(Graph graph, Player player) {
        this.graph = graph;
        this.player = player;
    }
}
