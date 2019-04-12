public class Quit extends Command {
    @Override
    protected void init(String input) {
    }

    @Override
    public boolean execute() {
        return false;
    }

    public Quit(Graph g, Player p) {
        super(g, p);
    }
}
