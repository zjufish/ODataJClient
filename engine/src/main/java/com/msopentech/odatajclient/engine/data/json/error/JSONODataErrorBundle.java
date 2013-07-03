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
package com.msopentech.odatajclient.engine.data.json.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msopentech.odatajclient.engine.data.json.AbstractJSONObject;

public class JSONODataErrorBundle extends AbstractJSONObject {

    private static final long serialVersionUID = -4784910226259754450L;

    @JsonProperty("odata.error")
    private JSONODataError error;

    public JSONODataError getError() {
        return error;
    }

    public void setError(final JSONODataError error) {
        this.error = error;
    }
}
