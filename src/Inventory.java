public class Inventory extends Command {
    @Override
    protected void init(String input) {
        return;
    }

    @Override
    public boolean execute() {
        player.displayInventory();
        return true;
    }

    public Inventory(Graph g, Player p) {
        super(g, p);
    }

}
