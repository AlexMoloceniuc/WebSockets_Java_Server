package ro.space.wserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private int unitCount = 0;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Scheduled(fixedRate = 5000)  // broadcasting every 5 seconds
    public void broadcastUnit() {
        // increment the unit number
        unitCount++;
        // Construct the JSON message
        String message = "{\"message\":\"Unit " + unitCount + "\"}";
        // Log to the console the Unit produced by the server
        System.out.println("Unit " + unitCount + " produced");
        // Send the message to destination X and make it available to WebSocket clients connected to it
        simpMessagingTemplate.convertAndSend("/topic/units", message);
    }

}
