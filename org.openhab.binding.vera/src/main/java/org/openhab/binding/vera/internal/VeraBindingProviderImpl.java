/**
 * Copyright (c) 2010-2014, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.vera.internal;

import net.whistlingfish.openhab.binding.AnnotationBasedBindingProvider;

import org.openhab.binding.vera.VeraBindingProvider;

/**
 * This class is responsible for parsing the binding configuration.
 *
 * @author Matt Tucker
 * @since 1.5.1
 */
public class VeraBindingProviderImpl extends
        AnnotationBasedBindingProvider<VeraBindingConfig, VeraBinding, VeraBindingProviderImpl> implements
        VeraBindingProvider {

    @SuppressWarnings("unchecked")
    public VeraBindingProviderImpl() {
        super(VeraBindingConfig.class, //
                ReadDeviceStateBindingConfig.class, //
                WriteDeviceStateBindingConfig.class);
    }

    /**
     * {@inheritDoc}
     */
    public String getBindingType() {
        return "vera";
    }
}
