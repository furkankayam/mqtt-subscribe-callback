package com.mqtttest.service;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class MQTTService1 implements MqttCallback {

    private double angle;
    private double distance;

    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        String payload = new String(message.getPayload());

        JSONObject jsonMessage = new JSONObject(payload);

        angle = jsonMessage.getDouble("angle");
        distance = jsonMessage.getDouble("distance");

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }

    //Getter-Setter
    public double getAngle() {
        return angle;
    }

    public double getDistance() {
        return distance;
    }

}