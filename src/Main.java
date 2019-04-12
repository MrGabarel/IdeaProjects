import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static Graph g = new Graph();
    private static Player p = new Player("Guy", "A level 5 wizard", g);
    private static HashMap<String, Command> commands = new HashMap<>();

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
            String word1;
            String word2;
            if (response.contains(" ")) {
                word1 = response.substring(0, response.indexOf(" "));
                word2 = response.substring(response.indexOf(" ") + 1);
            }
            else {
                word1 = response;
                word2 = "";
            }
            Command command = commands.get(word1);
            if (command != null){
                command.init(word2);
                command.execute();
            } else {
                System.out.println("The command \"" + response + "\" doesn't exist. Try one of these instead:");
                displayCommands();
            }
        } while (!response.equals("quit"));
    }

    private static void displayCommands() {
        System.out.println("\"go <roomName>\" moves you to the room you entered");
        System.out.println("\"look\" shows you a list of all the rooms you can go to");
        System.out.println("\"take <itemName>\" makes the player pick up an item in the room");
        System.out.println("\"items\" gives you a list of all items in the room");
        System.out.println("\"creatures\" gives you a list of all creatures in the room");
        System.out.println("\"drop <itemName>\" makes the player drop an item in the inventory");
        System.out.println("\"inventory\" displays the player's inventory");
        System.out.println("\"add <roomName>\" adds room by the name roomName with a connection to your current room");
        System.out.println("\"quit\" quits the game");

    }

    private static String generateWorld() {
        g.addNode("hall", "a long dank hallway");
        //This creates a list of rooms with randomly created names and descriptions
        for (int i = 0; i < 30; i++) {
            g.addNode(getSaltString(), getSaltString());
        }
        for (int i = 0; i <40; i++) {
            addUndirectedEdgeRandom();
        }
        commands.put("go", new Go(g, p));
        commands.put("look", new Look(g, p));
        commands.put("take", new Take(g, p));
        commands.put("items", new Items(g, p));
        commands.put("creatures", new Creatures(g, p));
        commands.put("drop", new Drop(g, p));
        commands.put("inventory", new Inventory(g, p));
        commands.put("add", new Add(g, p));
        commands.put("quit", new Quit(g, p));
        //TODO fix wumpus bug only finding one path leading to player
        for (int i = 0; i < 450; i++) {
            String room = g.getRandomRoom();
            g.addCreature(new Chicken(room, p, g), room);
            if (i%3 == 0) g.addCreature(new Wumpus(room, p, g), room);
            if (i%5 == 0) g.addCreature(new PopStar(room, p, g), room);
        }
        return "hall";
    }
    private static String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
    private static void addUndirectedEdgeRandom(){
        String room1 = g.getRandomRoom();
        String room2 = g.getRandomRoom();
        if (!room1.equals(room2)){
            g.addUndirectedEdge(room1, room2);
        }
    }
}