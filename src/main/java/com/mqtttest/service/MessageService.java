package com.mqtttest.service;
import com.mqtttest.config.MqttConfig;
import com.mqtttest.dto.response.MessageResponseDto;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MqttConfig mqttConfig;
    private final MQTTService1 mqttService1;

    public MessageService(MqttConfig mqttConfig, MQTTService1 mqttService1) {
        this.mqttConfig = mqttConfig;
        this.mqttService1 = mqttService1;
    }

    public MessageResponseDto getMessage() throws MqttException {

        MqttClient mqttClient = new MqttClient(mqttConfig.getMQTT_SERVER_URL(), "lidarClientId");

        mqttClient.setCallback(mqttService1);

        mqttClient.connect();
        mqttClient.subscribe("lidar");

        MessageResponseDto messageResponseDto = new MessageResponseDto(
                mqttService1.getAngle(),
                mqttService1.getDistance()
        );

        return messageResponseDto;

    }

}
