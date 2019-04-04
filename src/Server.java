import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {
    private static String[] names = {"Wily", "Felix", "Carlsbad", "Hobob"};
    private static String[] adjs = {"the gentle", "the un-gentle", "the overwrought", "the rude"};
    private static final int PORT = 9090;
    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(PORT);
        System.out.println("[SERVER] Waiting for client connection...");
        Socket client = listener.accept();
        System.out.println("[SERVER] Connected to client!");
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

        String request = in.readLine();

        try {
            while (true) {
                if (request.contains("name")) {
                    out.println(getRandomName());
                } else {
                    out.println("Type 'tell me a name' to get a random name");
                }
            }
        } finally {
            out.close();
            in.close();
        }
    }

    private static String getRandomName() {
        return names[(int)(Math.random() * 4)] + " " + adjs[(int)(Math.random() * 4)];
    }
}
