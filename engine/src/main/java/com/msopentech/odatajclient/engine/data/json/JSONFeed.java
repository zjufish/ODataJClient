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
package com.msopentech.odatajclient.engine.data.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.msopentech.odatajclient.engine.data.EntryResource;
import com.msopentech.odatajclient.engine.data.FeedResource;
import com.msopentech.odatajclient.engine.data.LinkResource;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * List of entries, represented via JSON.
 *
 * @see JSONEntry
 */
public class JSONFeed extends AbstractJSONObject implements FeedResource {

    private static final long serialVersionUID = -3576372289800799417L;

    @JsonProperty(value = "odata.metadata", required = false)
    private URI metadata;

    @JsonProperty("value")
    private List<JSONEntry> entries;

    public JSONFeed() {
        entries = new ArrayList<JSONEntry>();
    }

    public URI getMetadata() {
        return metadata;
    }

    public void setMetadata(URI metadata) {
        this.metadata = metadata;
    }

    @Override
    public List<JSONEntry> getEntries() {
        return entries;
    }

    @JsonIgnore
    @Override
    public void setEntries(List<EntryResource> entries) {
        this.entries.clear();
        for (EntryResource entry : entries) {
            if (entry instanceof JSONEntry) {
                this.entries.add((JSONEntry) entry);
            }
        }
    }

    @Override
    public boolean setNext(LinkResource next) {
        return false;
    }

    @Override
    public LinkResource getNext() {
        return null;
    }
}
