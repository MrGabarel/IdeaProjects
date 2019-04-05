import java.util.Scanner;

public class Main {
    private static Graph g = new Graph();
    private static Player p = new Player("Guy", "A level 5 wizard", g);

    public static void main(String[] args) {
        p.setCurrentRoom(generateWorld());
        String response;
        Scanner s = new Scanner(System.in);
        System.out.println("Commands you can use:");
        displayCommands();
        do {
            System.out.println("You are in the " + p.getCurrentRoom());
            System.out.println("What do you want to do? >");
            response = s.nextLine();
            if (isValidGo(response)) go(response);
            else if (response.equals("look")) {
                System.out.println(g.getNodes().get(p.getCurrentRoom()).getNeighborsNameAndDescriptions());
            } else if (response.length() > 8 && response.substring(0, 8).equals("add room ")) {
                g.addNode(response.substring(9), "");
                g.addDirectedEdge(p.getCurrentRoom(), response.substring(9));
            } else if (response.equals("items")) {
                System.out.println(g.getNode(p.getCurrentRoom()).getItemNames());
            } else if (response.equals("creatures")) {
                g.getNode(p.getCurrentRoom()).displayCreatures();
            } else if (response.equals("display inventory")) {
                p.displayInventory();
            } else if (response.length() > 5 && response.substring(0, 5).equals("take ")) {
                if (g.getNode(p.getCurrentRoom()).getItem(response.substring(5)) != null) {
                    p.addItem(response.substring(5), g.getNode(p.getCurrentRoom()).removeItem(response.substring(5)));
                } else {
                    System.out.println("That item doesn't exist, try \"items\" to get a list of the items in the room");
                }
            } else if (response.length() > 5 && response.substring(0, 5).equals("drop ")) {
                if (p.getItems().get(response.substring(5)) != null) {
                    g.getNode(p.getCurrentRoom()).addItem(response.substring(5), p.removeItem(response.substring(5)));

                } else {
                    System.out.println("That item doesn't exist, try \"display inventory\" to get a list of your items");
                }
            } else if (response.equals("quit"))
                System.out.println("The game had ended");
            else {
                System.out.println("The command \"" + response + "\" doesn't exist. Try one of these instead:");
                displayCommands();
            }
        } while (!response.equals("quit"));
    }

    private static void go(String response) {
        if (g.getNodes().get(p.getCurrentRoom()).getNeighbor(response.substring(3)) != null) {
            p.setCurrentRoom(response.substring(3));
            System.out.println("HI" + g);
            for (Creature c : g.getCreatures()) {
                c.act();
            }
        } else
            System.out.println("can't go there");
    }

    private static boolean isValidGo(String response) {
        return (response.length() > 2 && response.substring(0, 3).equals("go ") && g.getNode(response.substring(3)) != null);
    }

    private static void displayCommands() {
        System.out.println("\"go <roomName>\" moves you to the room you entered");
        System.out.println("\"look\" shows you a list of all the rooms you can go to");
        System.out.println("\"take <itemName>\" makes the player pick up an item in the room");
        System.out.println("\"items\" gives you a list of all items in the room");
        System.out.println("\"creatures\" gives you a list of all creatures in the room");
        System.out.println("\"drop <itemName>\" makes the player drop an item in the inventory");
        System.out.println("\"display inventory\" displays the player's inventory");
        System.out.println("\"add room <roomName>\" adds room by the name roomName with a connection to your current room");
        System.out.println("\"quit\" quits the game");

    }

    private static String generateWorld() {
        g.addNode("hall", "a long dank hallway");
        g.addNode("closet", "a dark, dark closet");
        g.addNode("dungeon", "a scary dungeon");
        g.addUndirectedEdge("hall", "dungeon");
        g.addUndirectedEdge("hall", "closet");
        g.getNode("hall").addItem("lobster");
        g.getNode("hall").addItem("key");
        g.getNode("closet").addItem("shirt");
        for (int i = 0; i < 450; i++) {
            String room = g.getRandomRoom();
            g.addCreature(new Chicken(room, p, g), room);
            if (i%3 == 0) g.addCreature(new Wumpus(room, p, g), room);
            if (i%5 == 0) g.addCreature(new PopStar(room, p, g), room);
        }
        return "hall";
    }
}