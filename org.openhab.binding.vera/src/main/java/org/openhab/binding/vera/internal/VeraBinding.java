/**
 * Copyright (c) 2010-2014, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.vera.internal;

import java.util.Dictionary;

import net.whistlingfish.openhab.binding.AnnotationBasedBinding;
import net.whistlingfish.openhab.config.ConfigMapper;

import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Handles Vera controller bindings to dispatch commands to and updates from the Vera device.
 *
 * @author Matt Tucker
 * @since 1.5.1
 */
public class VeraBinding extends AnnotationBasedBinding<VeraBindingConfig, VeraBinding, VeraBindingProviderImpl>
        implements ManagedService {
    private VeraClient veraClient;

    @JsonProperty
    private String host;

    private Integer port = VeraClient$.MODULE$.DEFAULT_PORT();

    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(VeraBinding.class);

    public VeraBinding() {
        super(VeraBinding.class, VeraBindingProviderImpl.class);
    }

    /**
     * @{inheritDoc
     */
    public void updated(Dictionary<String, ?> config) throws ConfigurationException {
        if (config != null) {
            new ConfigMapper(config).bindTo(this);
            if (host != null) {
                veraClient = new VeraClient(host, port);
                veraClient.start();
                setProperlyConfigured(true);
            }
        }
    }

    public void registerListener(VeraStateListener veraStateListener) {
        veraClient.registerListener(veraStateListener);
    }

    public void triggerAction(String deviceName, String serviceId, String deviceProperty, String newValue) {
        int deviceId = veraClient.getDevice(deviceName).getId();
        veraClient.triggerAction(deviceId, serviceId, deviceProperty, newValue);
    }
}
