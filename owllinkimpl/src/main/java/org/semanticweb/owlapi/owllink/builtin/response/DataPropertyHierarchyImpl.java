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

package org.semanticweb.owlapi.owllink.builtin.response;

import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.reasoner.Node;

import java.util.Set;

/**
 * Author: Olaf Noppens
 * Date: 24.11.2009
 */
public class DataPropertyHierarchyImpl extends HierarchyImpl<OWLDataProperty> implements DataPropertyHierarchy {

    /**
     * @param hierarchyPairs hierarchyPairs 
     * @param unsatisfiables unsatisfiables 
     * @param warning warning 
     */
    public DataPropertyHierarchyImpl(Set<HierarchyPair<OWLDataProperty>> hierarchyPairs, Node<OWLDataProperty> unsatisfiables, String warning) {
        super(hierarchyPairs, unsatisfiables, warning);
    }

    /**
     * @param hierarchyPairs hierarchyPairs 
     * @param unsatisfiables unsatisfiables 
     */
    public DataPropertyHierarchyImpl(Set<HierarchyPair<OWLDataProperty>> hierarchyPairs, Node<OWLDataProperty> unsatisfiables) {
        super(hierarchyPairs, unsatisfiables);
    }


    @Override
    public <O> O accept(ResponseVisitor<O> visitor) {
        return visitor.visit(this);
    }
}
