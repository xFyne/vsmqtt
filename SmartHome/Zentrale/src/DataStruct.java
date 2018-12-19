import java.net.InetAddress;
import java.sql.Timestamp;

public class DataStruct {
    private int value;      //Wert des Sensors
    private String type;    //Typ des Sensors (F, L, D, )
    private InetAddress ip;         //Ip des Sensors
    private int port;       //Port des Sensors
    //private Timestamp time; //Wann wurden die Daten an den Server übertragen
    private double speed;      //übertragungsdauer

    public DataStruct(){


    }

    public DataStruct(int value, String type, InetAddress ip, int port, double speed){
        this.value = value;
        this.type = type;
        this.ip = ip;
        this.port = port;
        //this.time = time;
        this.speed = speed;

    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public InetAddress getIp() {
        return ip;
    }

    public void setIp(InetAddress ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
/*
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
    */
}
