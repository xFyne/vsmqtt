import java.io.IOException;

/**
 * Zentrale Class
 */
public class Zentrale{
    private int udpPort;
    private int httpPort;
    private UDPSocketServer udpSocketServer;
    private HttpServer httpServer;
    private Publisher ZentralePublisher;

    /**
     * Constructor for Zentrale
     * @param udpPort udpPort to pass on to the UDPSocketServer
     */
    public Zentrale(int udpPort, int httpPort,String brokerProtocol, String brokerAdress, int brokerPort, String topic)
    {
        this.udpPort = udpPort;
        this.httpPort = httpPort;
        this.udpSocketServer = null;
        ZentralePublisher = new Publisher(brokerProtocol, brokerAdress, brokerPort, topic);
    }

    /**
     * Starter for the UDPSocketServer
     */
    public void startUDPSocketserver() throws IOException {
        try {
            this.udpSocketServer = new UDPSocketServer(this.udpPort, this.ZentralePublisher);
        }
        catch (IOException e)
        {
            System.out.println("UDPServer konnte nicht gestartet werden");
            System.exit(1);
        }
        this.udpSocketServer.start();
    }

    public void startHttpServer() throws IOException {
        try {
            this.httpServer = new HttpServer(httpPort);
        }
        catch (IOException e)
        {
            System.out.println("HttpServer konnte nicht gestartet werden");
            System.exit(1);
        }
        this.httpServer.start();
    }
}
