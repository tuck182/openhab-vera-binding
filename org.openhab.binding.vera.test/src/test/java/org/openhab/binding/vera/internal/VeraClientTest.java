package org.openhab.binding.vera.internal;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;

import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;
import static org.openhab.binding.vera.internal.VeraClient.OBJECT_MAPPER;

@RunWith(MockitoJUnitRunner.class)
public class VeraClientTest {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(VeraClient.class);

    private static final String VERA_HOST = "192.168.0.130";

    private VeraClient client = new VeraClient(VERA_HOST);

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
    public void startClient() throws InterruptedException {
        client.start();
        System.err.println(dumpObject(client.getStatus()));
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
