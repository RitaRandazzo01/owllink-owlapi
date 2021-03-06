/*
 * This file is part of the OWLlink API.
 *
 * The contents of this file are subject to the LGPL License, Version 3.0.
 *
 * Copyright (C) 2011, derivo GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 *
 *
 * Alternatively, the contents of this file may be used under the terms of the Apache License, Version 2.0
 * in which case, the provisions of the Apache License Version 2.0 are applicable instead of those above.
 *
 * Copyright 2011, derivo GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.semanticweb.owlapi.owllink.builtin.requests;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.owllink.builtin.response.KBResponse;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Author: Olaf Noppens
 * Date: 23.10.2009
 * @param <O> object type
 * @param <R> response type
 */
public abstract class AbstractKBRequestWithTwoOrMoreObjects<R extends KBResponse, O> extends AbstractKBRequest<R> implements Iterable<O> {
    protected java.util.Set<O> elements;

    /** @param kb knowledge base 
     * @param elements elements */
    public AbstractKBRequestWithTwoOrMoreObjects(IRI kb, Collection<O> elements) {
        super(kb);
        if (elements.size() < 2)
            throw new IllegalArgumentException("At least two elements must be in the argument but there are only " + elements.size());
        this.elements = Collections.unmodifiableSet(new HashSet<>(elements));
    }

    /** @param kb knowledge base 
     * @param elem elem */
    @SafeVarargs
    public AbstractKBRequestWithTwoOrMoreObjects(IRI kb, O... elem) {
        this(kb, Arrays.asList(elem));
    }

    /** @return elements */
    public java.util.Set<O> getElements() {
        return this.elements;
    }

    @Override
    public Iterator<O> iterator() {
        return this.elements.iterator();
    }
}
