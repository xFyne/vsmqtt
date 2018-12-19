

public class Main {
    /**
     * Main method and entry-point
     * @param args Command-Line Arguments (Sensortype,ZentraleAdress, ZentralePort)
     */
    public static void main(String[] args)
    {
        Sensor sensor = new Sensor(args[0], args[1],Integer.parseInt(args[2]));

    }
}
