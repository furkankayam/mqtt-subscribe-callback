package com.mqtttest.config;

import com.mqtttest.exception.MqttClientException;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import java.util.UUID;

@Configuration
@Scope("prototype")
@Lazy
public class MqttConfig {

    // RANDOM CLIENT ID
    private static final String MQTT_CLIENT_ID = UUID.randomUUID().toString();

    /***************************/

    @Value("${mqtt.client}")
    private String MQTT_SERVER_URL;

    @Bean
    public IMqttClient getInstance() {
        IMqttClient instance;
        try {
            instance = new MqttClient(MQTT_SERVER_URL, MQTT_CLIENT_ID);

            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            //options.setConnectionTimeout(10);
            options.setMaxInflight(3000);

            if (!instance.isConnected()) {
                instance.connect(options);
            }
        } catch (MqttException e) {
            return (IMqttClient) new MqttClientException("MqttException!");
        }

        return instance;
    }

    // Getter-Setter
    public String getMQTT_SERVER_URL() {
        return MQTT_SERVER_URL;
    }

    public void setMQTT_SERVER_URL(String MQTT_SERVER_URL) {
        this.MQTT_SERVER_URL = MQTT_SERVER_URL;
    }

}
