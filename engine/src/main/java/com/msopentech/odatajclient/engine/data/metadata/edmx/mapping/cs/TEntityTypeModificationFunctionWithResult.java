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
package com.msopentech.odatajclient.engine.data.metadata.edmx.mapping.cs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for TEntityTypeModificationFunctionWithResult complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="TEntityTypeModificationFunctionWithResult">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.microsoft.com/ado/2009/11/mapping/cs}TEntityTypeModificationFunction">
 *       &lt;group ref="{http://schemas.microsoft.com/ado/2009/11/mapping/cs}TResultBindingGroup"/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TEntityTypeModificationFunctionWithResult", propOrder = {
    "resultBinding"
})
public class TEntityTypeModificationFunctionWithResult
        extends TEntityTypeModificationFunction {

    @XmlElement(name = "ResultBinding")
    protected List<TResultBinding> resultBinding;

    /**
     * Gets the value of the resultBinding property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a
     * <CODE>set</CODE> method for the resultBinding property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResultBinding().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TResultBinding }
     *
     *
     */
    public List<TResultBinding> getResultBinding() {
        if (resultBinding == null) {
            resultBinding = new ArrayList<TResultBinding>();
        }
        return this.resultBinding;
    }
}
