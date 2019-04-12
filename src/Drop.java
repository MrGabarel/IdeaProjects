public class Drop extends Command {
    String item;
    @Override
    protected void init(String input) {
        item = input;
    }

    @Override
    public boolean execute() {
        if (player.getItems().get(item) != null){
            graph.getNode(player.getCurrentRoom()).addItem(item, player.removeItem(item));
            return true;
        } else {
            System.out.println("That item doesn't exist in your inventory");
            return false;
        }
    }

    public Drop(Graph g, Player p) {
        super(g, p);
    }
}
