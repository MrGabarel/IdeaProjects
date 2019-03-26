import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Graph g = new Graph();
        g.addNode("hall", "a long dank hallway");
        g.addNode("closet", "a dark, dark closet");
        g.addNode("dungeon", "a scary dungeon");

        g.addDirectedEdge("hall", "dungeon");
        g.addUndirectedEdge("hall", "closet");
        String current = "hall";
        String response;
        Scanner s = new Scanner(System.in);
        System.out.println("Commands you can use:");
        displayCommands();
        do {
            System.out.println("You are in the " + current);
            System.out.println("What do you want to do? >");
            response = s.nextLine();
            if (response.length() > 2 && response.substring(0,3).equals("go ") && Graph.getNode(response.substring(3)) != null) {
                if (g.getNodes().get(current).getNeighbor(response.substring(3)) != null)
                    current = response.substring(3);
                else
                    System.out.println("can't go there");
            }
            else if (response.equals("look")) {
                System.out.println(g.getNodes().get(current).getNeighborsNameAndDescriptions());
            }
            else if (response.length() > 8 && response.substring(0,8).equals("add room ")) {
                g.addNode(response.substring(9), "");
                g.addDirectedEdge(current, response.substring(9));
            }
            else if (response.equals("quit"))
                System.out.println("The game had ended");
            else {
                System.out.println("The command \"" + response + "\" doesn't exist. Try one of these instead:");
                displayCommands();
            }
        } while (!response.equals("quit"));
    }
    private static void displayCommands(){
        System.out.println("\"go <roomName>\" moves you to the room you entered");
        System.out.println("\"look\" shows you a list of all the rooms you can go to");
        System.out.println("\"add room <roomName>\" adds room by the name roomName with a connection to your current room");
        System.out.println("\"quit\" quits the game");
    }
}