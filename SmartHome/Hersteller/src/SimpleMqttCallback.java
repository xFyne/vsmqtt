import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class SimpleMqttCallback implements MqttCallback {

    /** The logger. */
    // private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMqttCallback.class);

    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("Connection to MQTT broker lost!");
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println("Message received: "+ new String(mqttMessage.getPayload()) );
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken mqttDeliveryToken) {
        try {
            System.out.println("Delivery completed: "+ mqttDeliveryToken.getMessage() );
        } catch (MqttException e) {
            System.out.println("Failed to get delivery token message: " + e.getMessage());
        }
    }
}