package org.openhab.binding.vera.internal;

import java.net.URL;
import java.net.URLClassLoader;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.openhab.binding.vera.luup.Device;
import org.openhab.binding.vera.luup.Room;
import org.openhab.binding.vera.luup.Scene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;

@RunWith(MockitoJUnitRunner.class)
public class VeraClientTest {
    private static final ObjectMapper OBJECT_MAPPER = VeraClient$.MODULE$.OBJECT_MAPPER();

    private static final Logger logger = LoggerFactory.getLogger(VeraClient.class);

    private static final String VERA_HOST = "192.168.0.130";

    private VeraClient client = new VeraClient(VERA_HOST);

    @Test
    @Ignore
    public void dumpClasspath() {
        ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader)cl).getURLs();

        for(URL url: urls){
            System.out.println(url.getFile());
        }

    }

    @Test
    public void getFullStatus() throws Exception {
        System.err.println(dumpObject(client.getFullStatus()));
    }

    @Test
    public void getConfig() throws Exception {
        System.err.println(dumpObject(client.getConfig()));
    }

    @Test
    @Ignore
    public void getStatus() throws Exception {
        System.err.println(dumpObject(client.getStatus()));
    }

    @Test
    @Ignore
    public void getDevices() {
        System.err.println(dumpObject(client.getDevices()));
    }

    @Test
    @Ignore
    public void startClient() throws InterruptedException {
        client.start();
        System.err.println(dumpObject(client.getStatus()));
        while (true) {
            Thread.sleep(60 * 10);
        }
    }

    @Test
    @Ignore
    public void trackStatus() throws InterruptedException {
        client.registerListener(new VeraStateListener() {
            @Override
            public void deviceUpdated(Device device) {
                logger.debug("{} changed", device);
            }

            @Override
            public void sceneUpdated(Scene scene) {
                logger.debug("{} changed", scene);
            }

            @Override
            public void roomUpdated(Room room) {
                logger.debug("{} changed", room);
            }
        });
        client.start();

        while (true) {
            Thread.sleep(60 * 10);
        }
    }

    private String dumpObject(Object object) {
        try {
            return OBJECT_MAPPER.enable(INDENT_OUTPUT) //
                    .writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert object to json", e);
        }
    }
}
