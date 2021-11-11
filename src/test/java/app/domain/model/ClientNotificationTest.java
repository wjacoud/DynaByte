package app.domain.model;

import app.domain.model.client.ClientNotification;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientNotificationTest {

    @Test
    public void sendMessage() {
        assertTrue(ClientNotification.sendMessage("", "hello", "01111102"));
    }
}