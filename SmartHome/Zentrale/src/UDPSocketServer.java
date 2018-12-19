import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * UDPSocketServer Class
 */
class UDPSocketServer extends Thread{
    private DatagramSocket udpSocket;
    private boolean running = true;
    private byte[] buf = new byte[256];
    private FileWriter fw;
    private BufferedWriter bw;
    private Publisher ZentralePublisher;
    /**
     * Constructor for UDPSocketServer
     * @param port the port on which the UDPSocketServer should listen
     */
    UDPSocketServer(int port, Publisher ZentralePublisher) throws IOException
    {
        File file = new File("../Zentrale/WebFiles/history.txt");
        this.udpSocket = new DatagramSocket(port);
        fw = new FileWriter(file, true);
        bw = new BufferedWriter(fw);
        this.ZentralePublisher = ZentralePublisher;

    }

    /**
     * Run method for the UDPSocketServer
     */
    public void run() {

        while(running)
        {
            DatagramPacket udpPacket = new DatagramPacket(this.buf, this.buf.length);
            try
            {
                final long timeStart = System.currentTimeMillis();
                this.udpSocket.receive(udpPacket);
                String entpackteNachricht = entpackePacket(udpPacket);
                final long timeEnd = System.currentTimeMillis();
                final double speed = timeEnd - timeStart;
                DataStruct receivedData = new DataStruct(getValue(entpackteNachricht), getName(entpackteNachricht), udpPacket.getAddress(), udpPacket.getPort(), speed);    //Erstelle Datenpaket
                DataSingleton.getInstance().setData(receivedData);                                                                                                                        //Füge Datenpacket zu singleton hinzu
                ZentralePublisher.publish(entpackteNachricht);
                // historyWriter(entpackteNachricht + "; Ip: " + udpPacket.getAddress() + "; Port: " + udpPacket.getPort() + "; Time: " + speed +  "ms");
                System.out.println(entpackteNachricht + " von Ip: " + udpPacket.getAddress() + " und von Port: " + udpPacket.getPort() + " in " + speed +  "ms empfangen");

            }
            catch(IOException e)
            {
                System.out.println("Konnte Packet nicht empfangen");
            }
        }
    }


    /**
     * Return the important information from the received package
     * @param udpPacket The received package
     * @return returns the received value and the sensor name
     */
    private String entpackePacket(DatagramPacket udpPacket)
    {

        String payload = new String(udpPacket.getData());
        payload = payload.substring(0,payload.indexOf(' ')+2);
        return payload;
    }

    /**
     * Wirtes the received information in a txt file
     * @param information the received information
     * */
    public void historyWriter(String information) throws IOException {

        bw.write(information);
        bw.newLine();
        bw.flush();
    }
    private String getName(String payload){
        String splitted[] =  payload.split(" ");
        return splitted[1];
    }

    private int getValue(String payload){
        String splitted[] =  payload.split(" ");
        return Integer.parseInt(splitted[0]);
    }
}
