public class Drop extends Command {
    @Override
    protected void init(String input) {

    }

    @Override
    public boolean execute() {
        return false;
    }

    public Drop(Graph g, Player p) {
        super(g, p);
    }
}
