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
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLPropertyExpression;
import org.semanticweb.owlapi.owllink.builtin.response.KBResponse;

/**
 * Author: Olaf Noppens
 * Date: 24.10.2009
 * @param <P> property type
 * @param <R> response type
 */
public abstract class AbstractKBRequestWithIndividualProperty<R extends KBResponse, P extends OWLPropertyExpression>
        extends AbstractKBRequest<R> {
    final OWLIndividual individual;
    final P property;

    /** @param kb knowledge base 
     * @param individual individual 
     * @param property property */
    public AbstractKBRequestWithIndividualProperty(IRI kb, OWLIndividual individual, P property) {
        super(kb);
        this.individual = individual;
        this.property = property;
    }

    /** @return property */
    public P getOWLProperty() {
        return this.property;
    }

    /** @return individual */
    public OWLIndividual getOWLIndividual() {
        return this.individual;
    }
}
