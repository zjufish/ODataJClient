/*
 * Copyright 2013 MS OpenTech.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.msopentech.odatajclient.engine.data;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

/**
 * Helper class for handling time (as duration) primitive values.
 *
 * @see com.msopentech.odatajclient.engine.data.metadata.edm.EdmSimpleType#TIME
 * @see Duration
 */
public final class ODataDuration {

    private final Duration duration;

    public ODataDuration(final String input) {
        try {
            final DatatypeFactory dtFactory = DatatypeFactory.newInstance();
            this.duration = dtFactory.newDuration(input);
        } catch (DatatypeConfigurationException e) {
            throw new IllegalArgumentException("Could not parse '" + input + "' as Duration", e);
        }
    }

    public ODataDuration(final Duration duration) {
        this.duration = duration;
    }

    public Duration getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return this.duration.toString();
    }
}
