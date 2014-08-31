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

import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles Vera controller bindings to dispatch commands to and updates from the Vera device.
 *
 * @author Matt Tucker
 * @since 1.5.1
 */
public class VeraBinding extends AnnotationBasedBinding<VeraBindingConfig, VeraBinding, VeraBindingProviderImpl>
        implements ManagedService {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(VeraBinding.class);

    public VeraBinding() {
        super(VeraBinding.class, VeraBindingProviderImpl.class);
    }

    /**
     * @{inheritDoc
     */
    @Override
    public void updated(Dictionary<String, ?> config) throws ConfigurationException {
        if (config != null) {
            // read config parameters here ...
        }
    }
}
