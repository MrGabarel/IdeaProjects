import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Graph g = new Graph();
        String current = generateWorld(g);
        String response;
        Scanner s = new Scanner(System.in);
        System.out.println("Commands you can use:");
        displayCommands();
        do {
            System.out.println("You are in the " + current);
            System.out.println("What do you want to do? >");
            response = s.nextLine();
            if (isValidGo(g, response)) go(g, current, response);
            //TODO change all the other methods in a similar matter
            else if (response.equals("look")) {
                System.out.println(g.getNodes().get(current).getNeighborsNameAndDescriptions());
            }
            else if (response.length() > 8 && response.substring(0,8).equals("add room ")) {
                g.addNode(response.substring(9), "");
                g.addDirectedEdge(current, response.substring(9));
            }
            else if (response.equals("items")){
                System.out.println(g.getNode(current).getItemNames());
            }

            else if (response.equals("quit"))
                System.out.println("The game had ended");
            else {
                System.out.println("The command \"" + response + "\" doesn't exist. Try one of these instead:");
                displayCommands();
            }
        } while (!response.equals("quit"));
    }
    private static String go(Graph g, String current, String response){
        if (g.getNodes().get(current).getNeighbor(response.substring(3)) != null)
            return response.substring(3);
        else
            System.out.println("can't go there");
        return null;
    }
    private static boolean isValidGo(Graph g, String response){
        return (response.length() > 2 && response.substring(0,3).equals("go ") && g.getNode(response.substring(3)) != null);
    }
    private static void displayCommands(){
        System.out.println("\"go <roomName>\" moves you to the room you entered");
        System.out.println("\"look\" shows you a list of all the rooms you can go to");
        System.out.println("\"items\" gives you a list of all items in the room");
        System.out.println("\"add room <roomName>\" adds room by the name roomName with a connection to your current room");
        System.out.println("\"quit\" quits the game");
    }
    private static String generateWorld(Graph g){
        g.addNode("hall", "a long dank hallway");
        g.addNode("closet", "a dark, dark closet");
        g.addNode("dungeon", "a scary dungeon");
        g.addDirectedEdge("hall", "dungeon");
        g.addUndirectedEdge("hall", "closet");
        String current = "hall";
        return current;
    }
}