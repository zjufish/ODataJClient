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
package com.msopentech.odatajclient.engine.data.metadata.edm.codegeneration;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for TPublicOrInternalAccess.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TPublicOrInternalAccess">
 *   &lt;restriction base="{http://schemas.microsoft.com/ado/2006/04/codegeneration}TAccess">
 *     &lt;enumeration value="Public"/>
 *     &lt;enumeration value="Internal"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "TPublicOrInternalAccess", namespace = "http://schemas.microsoft.com/ado/2006/04/codegeneration")
@XmlEnum(TAccess.class)
public enum TPublicOrInternalAccess {

    @XmlEnumValue("Public")
    PUBLIC(TAccess.PUBLIC),
    @XmlEnumValue("Internal")
    INTERNAL(TAccess.INTERNAL);

    private final TAccess value;

    TPublicOrInternalAccess(TAccess v) {
        value = v;
    }

    public TAccess value() {
        return value;
    }

    public static TPublicOrInternalAccess fromValue(TAccess v) {
        for (TPublicOrInternalAccess c : TPublicOrInternalAccess.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }
}
