import java.io.IOException;
import java.net.InetAddress;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Sensor class
 */
public class Sensor extends  TimerTask{
    private String nameDesSensors;
    private int messdaten;
    private UDPSocketClient udpScocketClient;

    /**
     * Standard constructor for Sensor
     */
    public Sensor()
    {

    }

    /**
     * Constructor for Sensor
     * @param name the name of the Sensor( T = Temperatur, F = Fenster, L = Luftfeuchtigkeit, D = Drehzahl
     * @param adresse the adress of the UDPSocketServer responsible for this Sensor
     * @param port the port of the UDPSocketServer
     */
    Sensor(String name, String adresse, int port) {

        try {
             this.udpScocketClient = new UDPSocketClient(InetAddress.getByName(adresse), port);
        } catch (IOException e) {
            System.out.println("Could not start UDP.SocketClient");
        }
        this.nameDesSensors = name;
        startClientTask();

    }

    /**
     * Starts a Timer which starts the method run every 5 seconds
     */
    private void startClientTask() {
        Timer timer = new Timer();
        timer.schedule(this, 0, 5000);
    }

    /**
     * Creates random generated numbers for each type of Sensor
     */
    private void DatenMessen()
    {
        switch (this.nameDesSensors)
        {
            case "T":
                this.messdaten = (int)(Math.random()*60-20);break;
            case "F":
                this.messdaten = (int)(Math.random()*2);break;
            case "L":
                this.messdaten = (int)(Math.random()*101);break;
            case "D":
                this.messdaten = (int)(Math.random()*101);break;
        }
    }

    /**
     * run method in which the messdaten gets send to the Zentrale
     */
    @Override
    public void run()
    {

            DatenMessen();
            System.out.println(this.messdaten);
            this.udpScocketClient.sendMsg(this.messdaten + " " + this.nameDesSensors);

    }
}
