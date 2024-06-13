package com.mqtttest.controller;

import com.mqtttest.dto.response.MessageResponseDto;
import com.mqtttest.service.MessageService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MqttController {

    private final MessageService messageService;

    public MqttController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/lidar")
    public ResponseEntity<MessageResponseDto> getMessage() throws MqttException, InterruptedException {
        return new ResponseEntity<>(messageService.getMessage(), HttpStatus.OK);
    }

}
