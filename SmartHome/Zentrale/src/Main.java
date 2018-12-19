import java.io.IOException;

public class Main {
    /**
     * Main Method and Entry-Point.
     * @param args Command-Line arguments( The port for the UDPSocketServer in this case)
     */
    public static void main(String[] args) throws IOException {
        Zentrale zentrale = new Zentrale(Integer.parseInt(args[0]), Integer.parseInt(args[1]),args[2],args[3],Integer.parseInt(args[4]),args[5]);
        zentrale.startUDPSocketserver();
        zentrale.startHttpServer();
    }
}
