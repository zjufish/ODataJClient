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
package com.msopentech.odatajclient.proxy.api.impl;

import com.msopentech.odatajclient.engine.uri.ODataURIBuilder;
import com.msopentech.odatajclient.engine.uri.filter.ODataFilter;
import com.msopentech.odatajclient.proxy.api.AbstractEntityCollection;
import com.msopentech.odatajclient.proxy.api.query.NoResultException;
import com.msopentech.odatajclient.proxy.api.query.NonUniqueResultException;
import com.msopentech.odatajclient.proxy.api.query.Query;
import com.msopentech.odatajclient.proxy.api.query.Sort;
import com.msopentech.odatajclient.proxy.utils.ClassUtils;
import java.io.Serializable;
import java.net.URI;
import org.apache.commons.lang3.StringUtils;

public class QueryImpl<T extends Serializable, EC extends AbstractEntityCollection<T>> implements Query<T, EC> {

    private static final long serialVersionUID = -300830736753191114L;

    private final Class<T> typeRef;

    private final Class<EC> collTypeRef;

    private final EntitySetInvocationHandler handler;

    private final URI baseURI;

    private String filter;

    private String orderBy;

    private Integer maxResults;

    private Integer firstResult;

    QueryImpl(final Class<EC> collTypeRef, final URI baseURI, final EntitySetInvocationHandler handler) {
        this.typeRef = ClassUtils.extractTypeRef(collTypeRef);
        this.collTypeRef = collTypeRef;
        this.baseURI = baseURI;
        this.handler = handler;
    }

    @Override
    public Query<T, EC> setFilter(final String filter) {
        this.filter = filter;
        return this;
    }

    @Override
    public Query<T, EC> setFilter(final ODataFilter filter) {
        this.filter = filter.build();
        return this;
    }

    @Override
    public String getFilter() {
        return filter;
    }

    @Override
    public Query<T, EC> setOrderBy(final Sort... sort) {
        final StringBuilder builder = new StringBuilder();
        for (Sort sortClause : sort) {
            builder.append(sortClause.getKey()).append(' ').append(sortClause.getValue()).append(',');
        }
        builder.deleteCharAt(builder.length() - 1);

        this.orderBy = builder.toString();
        return this;
    }

    @Override
    public Query<T, EC> setOrderBy(final String orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    @Override
    public String getOrderBy() {
        return orderBy;
    }

    @Override
    public Query<T, EC> setMaxResults(final int maxResults) throws IllegalArgumentException {
        if (maxResults <= 0) {
            throw new IllegalArgumentException("maxResults must be positive");
        }

        this.maxResults = maxResults;
        return this;
    }

    @Override
    public int getMaxResults() {
        return maxResults;
    }

    @Override
    public Query<T, EC> setFirstResult(final int firstResult) throws IllegalArgumentException {
        if (firstResult <= 0) {
            throw new IllegalArgumentException("firstResult must be positive");
        }

        this.firstResult = firstResult;
        return this;
    }

    @Override
    public int getFirstResult() {
        return firstResult;
    }

    @Override
    public T getSingleResult() throws NoResultException, NonUniqueResultException {
        final EC result = getResult();
        if (result.isEmpty()) {
            throw new NoResultException();
        }
        if (result.size() > 1) {
            throw new NonUniqueResultException();
        }

        return result.iterator().next();
    }

    @Override
    @SuppressWarnings("unchecked")
    public EC getResult() {
        final ODataURIBuilder uriBuilder = new ODataURIBuilder(this.baseURI.toASCIIString()).
                appendStructuralSegment(ClassUtils.getNamespace(typeRef) + "." + ClassUtils.getEntityTypeName(typeRef));

        if (StringUtils.isNotBlank(filter)) {
            uriBuilder.filter(filter);
        }
        if (StringUtils.isNotBlank(orderBy)) {
            uriBuilder.orderBy(orderBy);
        }
        if (maxResults != null) {
            uriBuilder.top(maxResults);
        }
        if (firstResult != null) {
            uriBuilder.skip(firstResult);
        }

        return (EC) handler.fetchWholeEntitySet(uriBuilder.build(), typeRef, collTypeRef);
    }
}