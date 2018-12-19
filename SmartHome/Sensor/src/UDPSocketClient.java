import java.net.InetAddress;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * class UDPSocketClient
 */
class UDPSocketClient {
    private InetAddress adresse;
    private int port;
    private DatagramSocket udpSocket;
    private byte[] buf = new byte[256];


    /**
     * Constructor for UDPSocketClient
     * @param ip Adress of the UDPSocketServer
     * @param port port to which the UDPSocketServer listens
     * @throws IOException
     */
    UDPSocketClient(InetAddress ip, int port) throws IOException
    {
            this.adresse = ip;
            this.port = port;
            this.udpSocket = new DatagramSocket();
    }

    /**
     * Sends a message to the UDPSocketServer
     * @param msg the message to be send
     */
    void sendMsg(String msg)
    {
        this.buf = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(this.buf, this.buf.length, this.adresse, this.port);

        System.out.println(this.port + " " + this.adresse);
        try
        {
            final long timeStart = System.currentTimeMillis();
            this.udpSocket.send(packet);
            final long timeEnd = System.currentTimeMillis();
            final double speed = timeEnd - timeStart;
            System.out.println("Packet mit der Nachricht " + msg + " wurde in " + speed + "ms verschickt\n");
        }
        catch(IOException e)
        {
            System.out.println("Packet mit der Nachricht " + msg + " konnte nicht gesendet werden\n");
        }
    }
}
