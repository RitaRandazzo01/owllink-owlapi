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

package org.semanticweb.owlapi.owllink.parser;

import org.coode.owlapi.owlxmlparser.OWLXMLParserException;
import org.coode.owlapi.owlxmlparser.OWLXMLParserHandler;
import org.semanticweb.owlapi.model.OWLObject;
import org.semanticweb.owlapi.owllink.builtin.response.HierarchyPair;
import org.semanticweb.owlapi.owllink.builtin.response.HierarchyPairImpl;
import org.semanticweb.owlapi.owllink.builtin.response.SubEntitySynsets;
import org.semanticweb.owlapi.reasoner.Node;

/**
 * Author: Olaf Noppens
 * Date: 02.11.2009
 * @param <O> object type
 */
public abstract class AbstractOWLlinkPairElementHandler<O extends OWLObject> extends AbstractOWLlinkElementHandler<HierarchyPair<O>> {
    protected Node<O> superSynset;
    protected SubEntitySynsets<O> subSetOfSynset;

    /** @param handler handler */
    public AbstractOWLlinkPairElementHandler(OWLXMLParserHandler handler) {
        super(handler);
    }

    @Override
    public void startElement(String name) throws OWLXMLParserException {
        super.startElement(name);
        this.subSetOfSynset = null;
        this.subSetOfSynset = null;
    }

    @Override
    public HierarchyPair<O> getOWLLinkObject() {
        return new HierarchyPairImpl<>(this.superSynset, this.subSetOfSynset);
    }
}
