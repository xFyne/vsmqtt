import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Publisher {
    private String broker;
    private String topic;
    public Publisher(String brokerProtocol, String brokerAdress, int brokerPort, String topic) {
        broker = brokerProtocol + "://" + brokerAdress + ":" + brokerPort;
        this.topic = topic;
    }
    public void publish(String Message) {

        // Create some MQTT connection options.
        MqttConnectOptions mqttConnectOpts = new MqttConnectOptions();
        mqttConnectOpts.setCleanSession(true);

        try {

            MqttClient client = new MqttClient(broker, MqttClient.generateClientId());

            // Connect to the MQTT broker using the connection options.
            client.connect(mqttConnectOpts);

            // Create the message and set a quality-of-service parameter.
            MqttMessage message = new MqttMessage(Message.getBytes());

            // Publish the message.
            client.publish(topic, message);

            // Disconnect from the MQTT broker.
            client.disconnect();



        } catch (MqttException e) {
            System.out.println("An error occurred: " + e.toString() );
        }
    }

}