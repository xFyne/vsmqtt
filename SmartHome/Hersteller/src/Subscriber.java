import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Subscriber {
    private String broker;
    private String topic;
    public Subscriber(String brokerProtocol, String brokerAdress, int brokerPort, String topic) {
        broker =
                brokerProtocol + "://" +
                        brokerAdress + ":" +
                        brokerPort;
        this.topic = topic;
    }
    public void run() {
        //while(true) {
            try {
                MqttClient client = new MqttClient(broker, MqttClient.generateClientId());
                client.setCallback(new SimpleMqttCallback());

                // Connect to the MQTT broker.
                client.connect();
                //LOGGER.info("Connected to MQTT broker: " + client.getServerURI());

                // Subscribe to a topic.
                client.subscribe(topic);
                //LOGGER.info("Subscribed to topic: " + client.getTopic(cliParameters.getTopic()));
            } catch (MqttException e) {
                System.out.println("An error occurred: ");
            }
     //   }
    }
}