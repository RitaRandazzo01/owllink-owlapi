/*
 * Copyright (C) 2010, Ulm University
 *
 * Modifications to the initial code base are copyright of their
 * respective authors, or their employers as appropriate.  Authorship
 * of the modifications may be determined from the ChangeLog placed at
 * the end of this file.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

package org.semanticweb.owlapi.owllink.builtin.requests;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLObject;
import org.semanticweb.owlapi.owllink.builtin.response.KBResponse;

/**
 * Author: Olaf Noppens
 * Date: 25.10.2009
 * @param <O> object type
 * @param <R> response type
 */
public abstract class AbstractKBRequestWithOneObjectAndNegativeAttribute<R extends KBResponse, O extends OWLObject> extends AbstractKBRequestWithOneObjectAndBooleanValue<R, O> {

    /** @param iri knowledge base 
     * @param object object 
     * @param bool bool */
    public AbstractKBRequestWithOneObjectAndNegativeAttribute(IRI iri, O object, boolean bool) {
        super(iri, object, bool);
    }

    /** @param iri knowledge base 
     * @param object object */
    public AbstractKBRequestWithOneObjectAndNegativeAttribute(IRI iri, O object) {
        super(iri, object);
    }

    /** @return true if negative */
    public final boolean isNegative() {
        return super.bool;
    }
}
