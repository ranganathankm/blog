package com.blogspot.javanbswing.sb.ws.user.without.stomp;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ranga
 */
@RestController
@RequestMapping("push")
public class PushController {
    @Autowired
    private BroadcastService broadcastService;
    
    @GetMapping("broadcast/{message}")
    public void pushMessage(@PathVariable String message ) {
        try {
            broadcastService.broadcastMessage(message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @GetMapping("targeted/{user}/{message}")
    public void targetedMessage(@PathVariable String user, @PathVariable String message) {
        try {
            broadcastService.targetedMessage(user, message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }    
}
