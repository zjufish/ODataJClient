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

import com.msopentech.odatajclient.engine.data.metadata.edm.EdmSimpleType;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

/**
 * Helper class for handling datetime and datetime-offset primitive values.
 * 
 * @see com.msopentech.odatajclient.engine.data.metadata.edm.EdmSimpleType#DATE_TIME
 * @see com.msopentech.odatajclient.engine.data.metadata.edm.EdmSimpleType#DATE_TIME_OFFSET
 */
public final class ODataTimestamp {

    private final SimpleDateFormat sdf;

    private final Timestamp timestamp;

    private String timezone;

    public static ODataTimestamp getInstance(final EdmSimpleType type, final Timestamp timestamp) {
        return new ODataTimestamp(new SimpleDateFormat(type.pattern()),
                new Date(timestamp.getTime()), timestamp.getNanos());
    }

    public static ODataTimestamp parse(final String pattern, final String input) {
        final ODataTimestamp instance;

        final String[] dateParts = input.split("\\.");
        final SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            final Date date = sdf.parse(dateParts[0]);
            if (dateParts.length > 1) {
                int idx = dateParts[1].indexOf('+');
                if (idx == -1) {
                    idx = dateParts[1].indexOf('-');
                }
                if (idx == -1) {
                    instance = new ODataTimestamp(sdf, date, Integer.parseInt(dateParts[1]));
                } else {
                    instance = new ODataTimestamp(sdf, date,
                            Integer.parseInt(dateParts[1].substring(0, idx)), dateParts[1].substring(idx));
                }
            } else {
                instance = new ODataTimestamp(sdf, date);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot parse " + pattern, e);
        }

        return instance;
    }

    private ODataTimestamp(final SimpleDateFormat sdf, final Date date) {
        this.sdf = sdf;
        this.timestamp = new Timestamp(date.getTime());
    }

    private ODataTimestamp(final SimpleDateFormat sdf, final Date date, final int nanos) {
        this(sdf, date);
        this.timestamp.setNanos(nanos);
    }

    private ODataTimestamp(final SimpleDateFormat sdf, final Date date, final int nanos, final String timezone) {
        this(sdf, date, nanos);
        this.timezone = timezone;
    }

    @Override
    public String toString() {
        final StringBuilder formatted = new StringBuilder().append(sdf.format(timestamp));
        if (timestamp.getNanos() > 0) {
            formatted.append('.').append(String.valueOf(timestamp.getNanos()));
        }
        if (StringUtils.isNotBlank(timezone)) {
            formatted.append(timezone);
        }
        return formatted.toString();
    }
}
