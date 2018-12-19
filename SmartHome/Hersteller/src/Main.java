
public class Main {
    public static void main(String[] args) {
        Subscriber Hersteller = new Subscriber(args[0],args[1],Integer.parseInt(args[2]),args[3]);
        Hersteller.run();
    }
}